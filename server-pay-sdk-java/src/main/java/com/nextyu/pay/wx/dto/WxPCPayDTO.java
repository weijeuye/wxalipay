package com.nextyu.pay.wx.dto;

import java.io.Serializable;

/**
 * 微信电脑网站支付(扫码支付)
 * created on 2017-04-06 14:02
 *
 * @author nextyu
 */
public class WxPCPayDTO implements Serializable {

    /**
     * 应用ID,微信开放平台审核通过的应用APPID
     */
    private String appId;
    /**
     * 商户号,微信支付分配的商户号
     */
    private String mchId;

    private String apiKey;

    /**
     * 订单总金额，单位为分
     */
    private String totalFee;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 商户网站唯一订单号
     */
    private String outTradeNo;
    /**
     * 服务器异步通知页面路径
     */
    private String notifyUrl;

    /**
     * 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     */
    private String attach;

    public WxPCPayDTO() {
    }

    public WxPCPayDTO(String appId, String mchId, String apiKey, String totalFee, String body, String outTradeNo
            , String notifyUrl, String attach) {
        this.appId = appId;
        this.mchId = mchId;
        this.apiKey = apiKey;
        this.totalFee = totalFee;
        this.body = body;
        this.outTradeNo = outTradeNo;
        this.notifyUrl = notifyUrl;
        this.attach = attach;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
