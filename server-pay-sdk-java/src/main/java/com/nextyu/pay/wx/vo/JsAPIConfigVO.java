package com.nextyu.pay.wx.vo;

import java.io.Serializable;

/**
 * 2017-02-21 下午1:57
 *
 * @author nextyu
 */
public class JsAPIConfigVO implements Serializable {

    /**
     * 公众号id
     */
    private String appId;
    /**
     * 时间戳
     */
    private String timeStamp;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 订单详情扩展字符串,统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=***
     */
    private String packageName;
    /**
     * 签名方式
     */
    private String signType;
    /**
     * 签名
     */
    private String paySign;


    public JsAPIConfigVO() {
    }

    public JsAPIConfigVO(String appId, String timeStamp, String nonceStr, String packageName, String signType, String paySign) {
        this.appId = appId;
        this.timeStamp = timeStamp;
        this.nonceStr = nonceStr;
        this.packageName = packageName;
        this.signType = signType;
        this.paySign = paySign;
    }

    @Override
    public String toString() {
        return "JsAPIConfigVO{" +
                "appId='" + appId + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", packageName='" + packageName + '\'' +
                ", signType='" + signType + '\'' +
                ", paySign='" + paySign + '\'' +
                '}';
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
}
