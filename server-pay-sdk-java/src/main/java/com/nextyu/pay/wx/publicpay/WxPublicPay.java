package com.nextyu.pay.wx.publicpay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nextyu.pay.wx.dto.WxPublicPayDTO;
import com.nextyu.pay.wx.util.*;
import com.nextyu.pay.wx.vo.JsAPIConfigVO;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 微信公众号支付
 * created on 2017-04-18 14:33
 *
 * @author nextyu
 */
public class WxPublicPay {

    private static final Logger logger = LoggerFactory.getLogger(WxPublicPay.class);

    private WxPublicPay() {
    }

    /**
     * 获取微信用户openId
     *
     * @param appId
     * @param secret
     * @param code
     * @return
     */
    public static String getOpenId(String appId, String secret, String code) {
        String requestURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + secret + "&code="
                + code + "&grant_type=authorization_code";

        // 创建httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建httpGet
        HttpGet httpGet = new HttpGet(requestURL);
        // 执行get请求
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            // 获得相应实体
            HttpEntity entity = httpResponse.getEntity();

            String result = EntityUtils.toString(entity);
            JSONObject jsonObject = JSON.parseObject(result);
            String openId = (String) jsonObject.get("openid");
            return openId;
        } catch (Exception e) {
            logger.error("获取微信用户openId失败", e);
        }

        return null;

    }

    /**
     * 构建支付信息
     * 前端解析获取js需要传入的参数信息
     *
     * @param wxPublicPayDTO
     * @return 支付信息
     */
    public static JsAPIConfigVO buildPayInfo(WxPublicPayDTO wxPublicPayDTO) {
        String randomString = UUIDUtil.getRandomString();
        try {
            Map<String, String> map = unifiedOrder(wxPublicPayDTO, randomString);
            String prepayId = map.get("prepay_id");
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);

            StringBuilder sb = new StringBuilder();

            sb.append("appId=").append(wxPublicPayDTO.getAppId());
            sb.append("&nonceStr=").append(randomString);
            sb.append("&package=").append("prepay_id=").append(prepayId);
            sb.append("&signType=").append("MD5");
            sb.append("&timeStamp=").append(timestamp);
            sb.append("&key=").append(wxPublicPayDTO.getApiKey());


            String newSign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
            return new JsAPIConfigVO(wxPublicPayDTO.getAppId(), timestamp, randomString, "prepay_id=" + prepayId, "MD5", newSign);

        } catch (Exception e) {
            logger.error("WxPublicPay buildPayInfo error", e);
        }

        return null;
    }

    private static Map<String, String> unifiedOrder(WxPublicPayDTO wxPublicPayDTO, String randomString) throws Exception {
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", wxPublicPayDTO.getAppId());
        packageParams.put("mch_id", wxPublicPayDTO.getMchId());
        packageParams.put("nonce_str", randomString);
        packageParams.put("body", wxPublicPayDTO.getBody());
        packageParams.put("out_trade_no", wxPublicPayDTO.getOutTradeNo());
        packageParams.put("total_fee", wxPublicPayDTO.getTotalFee());
        packageParams.put("spbill_create_ip", "");
        packageParams.put("notify_url", wxPublicPayDTO.getNotifyUrl());
        packageParams.put("trade_type", "JSAPI");
        packageParams.put("attach", wxPublicPayDTO.getAttach());
        packageParams.put("openid", wxPublicPayDTO.getOpenId());

        String sign = WxPayCommonUtil.createSign("UTF-8", packageParams, wxPublicPayDTO.getApiKey());
        packageParams.put("sign", sign);

        String requestXML = WxPayCommonUtil.getRequestXml(packageParams);
        String resultXml = HttpUtil.postData(WxPayCommonConfigUtil.UNIFIED_ORDER_URL, requestXML);
        return XMLUtil.parseXML(requestXML);
    }

}
