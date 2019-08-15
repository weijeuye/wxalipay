package com.nextyu.pay.wx.app;

import com.nextyu.pay.wx.dto.WxAppPayDTO;
import com.nextyu.pay.wx.util.*;
import com.nextyu.pay.wx.vo.WxAppPayVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 微信app支付
 * created on 2017-04-06 14:55
 *
 * @author nextyu
 */
public class WxAppPay {

    private static Logger logger = LoggerFactory.getLogger(WxAppPay.class);

    private WxAppPay() {
    }

    /**
     * 构建支付信息
     * 移动端解析获取sdk需要传入的参数信息
     *
     * @param wxAppPayDTO
     * @return 支付信息
     */
    public static WxAppPayVO buildPayInfo(WxAppPayDTO wxAppPayDTO) {
        String randomString = UUIDUtil.getRandomString();
        try {
            Map<String, String> map = unifiedOrder(wxAppPayDTO, randomString);
            String prepayId = map.get("prepay_id");
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);

            StringBuilder sb = new StringBuilder();
            sb.append("appid=").append(wxAppPayDTO.getAppId());
            sb.append("&noncestr=").append(randomString);
            sb.append("&package=Sign=WXPay");
            sb.append("&partnerid=").append(wxAppPayDTO.getMchId());
            sb.append("&prepayid=").append(prepayId);
            sb.append("&timestamp=").append(timestamp);
            sb.append("&key=").append(wxAppPayDTO.getApiKey());

            String newSign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();

            Map<String, String> map2 = new HashMap<String, String>();
            map2.put("appid", wxAppPayDTO.getAppId());
            map2.put("noncestr", randomString);
            map2.put("package", "Sign=WXPay");
            map2.put("partnerid", wxAppPayDTO.getMchId());
            map2.put("prepayid", prepayId);
            map2.put("timestamp", timestamp);
            map2.put("sign", newSign);

            return new WxAppPayVO(wxAppPayDTO.getAppId(), randomString, "Sign=WXPay"
                    , wxAppPayDTO.getMchId(), prepayId, timestamp, newSign);
        } catch (Exception e) {
            logger.error("WxAppPay buildPayInfo error", e);
        }

        return null;
    }

    /**
     * 统一下单
     *
     * @param wxAppPayDTO
     * @param randomString
     * @return
     * @throws Exception
     */
    private static Map<String, String> unifiedOrder(WxAppPayDTO wxAppPayDTO, String randomString) throws Exception {
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", wxAppPayDTO.getAppId());
        packageParams.put("mch_id", wxAppPayDTO.getMchId());
        packageParams.put("nonce_str", randomString);
        packageParams.put("body", wxAppPayDTO.getBody());
        packageParams.put("out_trade_no", wxAppPayDTO.getOutTradeNo());
        packageParams.put("total_fee", wxAppPayDTO.getTotalFee());
        packageParams.put("spbill_create_ip", "");
        packageParams.put("notify_url", wxAppPayDTO.getNotifyUrl());
        packageParams.put("trade_type", "APP");
        packageParams.put("attach", wxAppPayDTO.getAttach());

        String sign = WxPayCommonUtil.createSign("UTF-8", packageParams, wxAppPayDTO.getApiKey());
        packageParams.put("sign", sign);

        String requestXML = WxPayCommonUtil.getRequestXml(packageParams);
        String resultXml = HttpUtil.postData(WxPayCommonConfigUtil.UNIFIED_ORDER_URL, requestXML);
        return XMLUtil.parseXML(requestXML);
    }


}
