package com.nextyu.pay.ali.dto;

import java.io.Serializable;

/**
 * 支付宝app支付
 * created on 2017-04-06 10:14
 *
 * @author nextyu
 */
public class AliAppPayDTO implements Serializable {
    /**
     * 支付宝分配给开发者的应用ID
     */
    private String appId;

    /**
     * 收款支付宝账号 = 合作身份者ID
     * 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
     */
    private String sellerId;

    /**
     * 商户私钥，pkcs8格式
     */
    private String appPrivateKey;
    /**
     * 订单总金额，单位为:元，精确到小数点后两位
     */
    private String totalAmount;
    /**
     * 商品的标题/交易标题/订单标题/订单关键字等
     */
    private String subject;
    /**
     * 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body
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
     * 公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。支付宝会在异步通知时将该参数原样返回。
     * 本参数必须进行UrlEncode之后才可以发送给支付宝
     */
    private String passBackParams;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getAppPrivateKey() {
        return appPrivateKey;
    }

    public void setAppPrivateKey(String appPrivateKey) {
        this.appPrivateKey = appPrivateKey;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public String getPassBackParams() {
        return passBackParams;
    }

    public void setPassBackParams(String passBackParams) {
        this.passBackParams = passBackParams;
    }
}
