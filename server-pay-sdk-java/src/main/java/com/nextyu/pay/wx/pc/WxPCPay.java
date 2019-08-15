package com.nextyu.pay.wx.pc;

import com.nextyu.pay.wx.dto.WxPCPayDTO;
import com.nextyu.pay.wx.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 微信电脑网站支付(扫码支付)
 * created on 2017-04-18 14:29
 *
 * @author nextyu
 */
public class WxPCPay {
    private static final Logger logger = LoggerFactory.getLogger(WxPCPay.class);

    private WxPCPay() {
    }


    /**
     * 获取二维码链接
     *
     * @param wxPCPayDTO
     * @return
     */
    public static String getUrlCode(WxPCPayDTO wxPCPayDTO) {
        String randomString = UUIDUtil.getRandomString();
        try {
            Map<String, String> map = unifiedOrder(wxPCPayDTO, randomString);
            String urlCode = map.get("code_url");
            logger.debug("urlCode : {}", urlCode);
            return urlCode;
        } catch (Exception e) {
            logger.error("WxPCPay getUrlCode error", e);
        }
        return null;
    }

    /**
     * 查询订单的交易状态
     * <p>
     * SUCCESS—支付成功
     * REFUND—转入退款
     * NOTPAY—未支付
     * CLOSED—已关闭
     * REVOKED—已撤销（刷卡支付）
     * USERPAYING--用户支付中
     * PAYERROR--支付失败(其他原因，如银行返回失败)
     *
     * @param wxPCPayDTO
     * @return 交易状态，如果返回"SUCCESS"表示交易成功，用户已付款，就可以跳转到支付成功页面了
     */
    public static String getTradeState(WxPCPayDTO wxPCPayDTO) {
        String randomString = UUIDUtil.getRandomString();
        try {
            SortedMap<String, String> packageParams = new TreeMap<String, String>();
            packageParams.put("appid", wxPCPayDTO.getAppId());
            packageParams.put("mch_id", wxPCPayDTO.getMchId());
            packageParams.put("nonce_str", randomString);
            packageParams.put("out_trade_no", wxPCPayDTO.getOutTradeNo());
            String sign = WxPayCommonUtil.createSign("UTF-8", packageParams, wxPCPayDTO.getApiKey());
            packageParams.put("sign", sign);

            String requestXML = WxPayCommonUtil.getRequestXml(packageParams);
            String resultXml = HttpUtil.postData(WxPayCommonConfigUtil.QUERY_ORDER_URL, requestXML);
            Map<String, String> map = XMLUtil.parseXML(requestXML);
            String tradeState = map.get("trade_state");
            logger.debug("tradeState ---------  {}", tradeState);
            return tradeState;

        } catch (Exception e) {
            logger.error("WxPCPay getTradeState error", e);
        }
        return null;
    }

    private static Map<String, String> unifiedOrder(WxPCPayDTO wxPCPayDTO, String randomString) throws Exception {
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", wxPCPayDTO.getAppId());
        packageParams.put("mch_id", wxPCPayDTO.getMchId());
        packageParams.put("nonce_str", randomString);
        packageParams.put("body", wxPCPayDTO.getBody());
        packageParams.put("out_trade_no", wxPCPayDTO.getOutTradeNo());
        packageParams.put("total_fee", wxPCPayDTO.getTotalFee());
        packageParams.put("spbill_create_ip", "");
        packageParams.put("notify_url", wxPCPayDTO.getNotifyUrl());
        packageParams.put("trade_type", "NATIVE");
        packageParams.put("attach", wxPCPayDTO.getAttach());

        String sign = WxPayCommonUtil.createSign("UTF-8", packageParams, wxPCPayDTO.getApiKey());
        packageParams.put("sign", sign);

        String requestXML = WxPayCommonUtil.getRequestXml(packageParams);
        String resultXml = HttpUtil.postData(WxPayCommonConfigUtil.UNIFIED_ORDER_URL, requestXML);
        return XMLUtil.parseXML(requestXML);
    }

}
