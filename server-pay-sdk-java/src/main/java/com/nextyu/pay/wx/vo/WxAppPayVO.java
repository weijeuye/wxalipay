package com.nextyu.pay.wx.vo;

import java.io.Serializable;

/**
 * 移动端需要的支付信息
 * created on 2017-04-18 14:59
 *
 * @author nextyu
 */
public class WxAppPayVO implements Serializable {
    private String appId;
    private String nonceStr;
    private String packageName;
    private String partnerId;
    private String prepayId;
    private String timestamp;
    private String sign;

    public WxAppPayVO() {
    }

    public WxAppPayVO(String appId, String nonceStr, String packageName, String partnerId, String prepayId
            , String timestamp, String sign) {
        this.appId = appId;
        this.nonceStr = nonceStr;
        this.packageName = packageName;
        this.partnerId = partnerId;
        this.prepayId = prepayId;
        this.timestamp = timestamp;
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "WxAppPayVO{" +
                "appId='" + appId + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", packageName='" + packageName + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", prepayId='" + prepayId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
