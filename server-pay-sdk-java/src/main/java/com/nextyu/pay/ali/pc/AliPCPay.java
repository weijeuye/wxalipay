package com.nextyu.pay.ali.pc;

import com.nextyu.pay.ali.dto.AliPCPayDTO;
import com.nextyu.pay.ali.util.AlipaySubmit;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝电脑网站支付(扫码支付)
 * created on 2017-04-06 13:44
 *
 * @author nextyu
 */
public class AliPCPay {
    private AliPCPay() {
    }

    /**
     * 建立请求，以表单HTML形式构造
     *
     * @param aliPCPayDTO
     * @return
     */
    public static String buildRequestHtml(AliPCPayDTO aliPCPayDTO) {
        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
        //sParaTemp.put("qr_pay_mode", AlipayConfig.qr_pay_mode);
        sParaTemp.put("partner", aliPCPayDTO.getPartner());
        sParaTemp.put("seller_id", aliPCPayDTO.getSellerId());
        sParaTemp.put("_input_charset", "utf-8");
        sParaTemp.put("payment_type", "1");
        sParaTemp.put("notify_url", aliPCPayDTO.getNotifyUrl());
        sParaTemp.put("return_url", aliPCPayDTO.getReturnUrl());
        sParaTemp.put("out_trade_no", aliPCPayDTO.getOutTradeNo());
        sParaTemp.put("subject", aliPCPayDTO.getSubject());
        sParaTemp.put("body", aliPCPayDTO.getBody());
        sParaTemp.put("total_fee", aliPCPayDTO.getTotalAmount());
        sParaTemp.put("extra_common_param", aliPCPayDTO.getPassBackParams());
        sParaTemp.put("app_pay", "Y");
        //建立请求
        return AlipaySubmit.buildRequest(sParaTemp, "post", "确认");
    }
}
