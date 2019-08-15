package com.nextyu.pay.ali.util;

import com.alibaba.fastjson.JSON;
import com.nextyu.pay.ali.dto.AliAppPayDTO;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * created on 2017-04-06 13:34
 *
 * @author nextyu
 */
public class AppPayOrderInfoUtil {

    private static Logger logger = LoggerFactory.getLogger(AppPayOrderInfoUtil.class);

    private AppPayOrderInfoUtil() {
    }

    public static Map<String, String> buildOrderParamMap(AliAppPayDTO aliAppPayDTO) {
        Map<String, String> bizContentMap = new HashMap<String, String>();
        bizContentMap.put("timeout_express", "3d");
        bizContentMap.put("product_code", "QUICK_MSECURITY_PAY");
        bizContentMap.put("total_amount", aliAppPayDTO.getTotalAmount());
        bizContentMap.put("subject", aliAppPayDTO.getSubject());
        bizContentMap.put("body", aliAppPayDTO.getBody());
        bizContentMap.put("out_trade_no", aliAppPayDTO.getOutTradeNo());
        bizContentMap.put("seller_id", aliAppPayDTO.getSellerId());
        String encoded_passback_params = "";
        try {
            encoded_passback_params = URLEncoder.encode(aliAppPayDTO.getPassBackParams(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("encode passBackParams error", e);
        }
        bizContentMap.put("passback_params", encoded_passback_params);

        Map<String, String> keyValues = new HashMap<String, String>();

        keyValues.put("app_id", aliAppPayDTO.getAppId());

        keyValues.put("biz_content", JSON.toJSONString(bizContentMap));

        keyValues.put("charset", "utf-8");

        keyValues.put("method", "alipay.trade.app.pay");

        keyValues.put("sign_type", "RSA");

        keyValues.put("timestamp", new DateTime().toString("yyyy-MM-dd HH:mm:ss"));

        keyValues.put("version", "1.0");
        keyValues.put("notify_url", aliAppPayDTO.getNotifyUrl());

        return keyValues;
    }

    /**
     * 构造支付订单参数信息
     *
     * @param map 支付订单参数
     * @return
     */
    public static String buildOrderParam(Map<String, String> map) {
        List<String> keys = new ArrayList<String>(map.keySet());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            sb.append(buildKeyValue(key, value, true));
            sb.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        sb.append(buildKeyValue(tailKey, tailValue, true));

        return sb.toString();
    }

    /**
     * 拼接键值对
     *
     * @param key
     * @param value
     * @param isEncode
     * @return
     */
    private static String buildKeyValue(String key, String value, boolean isEncode) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append("=");
        if (isEncode) {
            try {
                sb.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                sb.append(value);
            }
        } else {
            sb.append(value);
        }
        return sb.toString();
    }

    /**
     * 对支付参数信息进行签名
     *
     * @param map 待签名授权信息
     * @return
     */
    public static String getSign(Map<String, String> map, String rsaKey) {
        List<String> keys = new ArrayList<String>(map.keySet());
        // key排序
        Collections.sort(keys);

        StringBuilder authInfo = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            authInfo.append(buildKeyValue(key, value, false));
            authInfo.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        authInfo.append(buildKeyValue(tailKey, tailValue, false));

        String oriSign = SignUtils.sign(authInfo.toString(), rsaKey);
        String encodedSign = "";

        try {
            encodedSign = URLEncoder.encode(oriSign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("encode sign error", e);
        }
        return "sign=" + encodedSign;
    }

}
