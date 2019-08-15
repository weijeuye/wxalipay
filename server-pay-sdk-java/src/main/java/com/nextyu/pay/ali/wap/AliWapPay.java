package com.nextyu.pay.ali.wap;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.nextyu.pay.ali.dto.AliWapPayDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * created on 2017-04-06 13:40
 *
 * @author nextyu
 */
public class AliWapPay {

    private static Logger logger = LoggerFactory.getLogger(AliWapPay.class);

    private AliWapPay() {
    }

    /**
     * 建立请求，以表单HTML形式构造
     *
     * @param aliWapPayDTO
     * @return
     */
    public static String buildRequestHtml(AliWapPayDTO aliWapPayDTO) {
        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();

        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", aliWapPayDTO.getAppId()
                , aliWapPayDTO.getAppPrivateKey(), "json", "utf-8", aliWapPayDTO.getAliPayPublicKey(), "RSA"); //获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest.setReturnUrl(aliWapPayDTO.getReturnUrl());
        alipayRequest.setNotifyUrl(aliWapPayDTO.getNotifyUrl());//在公共参数中设置回跳和通知地址

        sParaTemp.put("out_trade_no", aliWapPayDTO.getOutTradeNo());
        sParaTemp.put("total_amount", aliWapPayDTO.getTotalAmount());
        sParaTemp.put("subject", aliWapPayDTO.getSubject());
        sParaTemp.put("body", aliWapPayDTO.getBody());
        sParaTemp.put("seller_id", aliWapPayDTO.getSellerIid());

        String encoded_passback_params = "";
        try {
            encoded_passback_params = URLEncoder.encode(aliWapPayDTO.getPassBackParams(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("encode passBackParams error", e);
        }
        sParaTemp.put("passback_params", encoded_passback_params);


        sParaTemp.put("product_code", "QUICK_WAP_PAY");

        alipayRequest.setBizContent(JSON.toJSONString(sParaTemp));//填充业务参数
        String form = null; //调用SDK生
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            logger.error("alipayClient pageExecute error", e);
        }
        return form;
    }
}
