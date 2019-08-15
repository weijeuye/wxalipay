package com.nextyu.pay.ali.dto;

import java.io.Serializable;

/**
 * 支付宝电脑网站支付
 * created on 2017-04-06 10:35
 *
 * @author nextyu
 */
public class AliPCPayDTO implements Serializable {
    /**
     * 合作者身份ID,签约的支付宝账号对应的支付宝唯一用户号。
     * 以2088开头的16位纯数字组成。
     */
    private String partner;

    /**
     * 收款支付宝账号 = 合作身份者ID
     */
    private String sellerId;

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
     * 页面跳转同步通知页面路径
     */
    private String returnUrl;

    /**
     * 服务器异步通知页面路径
     */
    private String notifyUrl;

    /**
     * 公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。支付宝会在异步通知时将该参数原样返回。
     * 本参数必须进行UrlEncode之后才可以发送给支付宝
     */
    private String passBackParams;

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
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

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
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
