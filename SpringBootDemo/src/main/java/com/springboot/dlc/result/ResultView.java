package com.springboot.dlc.result;

import lombok.Data;
/**
 * @Author: liujiebang
 * @Description: restFul自定义响应类
 * @Date: 2018/7/2 16:54
 **/
@Data
public class ResultView {

    private  Integer code;

    private  String msg;

    private  Object data;

    private long time;

    public static ResultView ok() {
        return new ResultView();
    }

    public static ResultView ok(Object data) {
        return new ResultView(data);
    }

    public static ResultView error(ResultEnum resultEnum) {
        return new ResultView(resultEnum.getCode(), resultEnum.getMsg());
    }

    public static ResultView error(Integer code,String message) {
        return new ResultView(code,message);
    }

    public static ResultView error(String errMsg) {
        return new ResultView(errMsg);
    }
    private ResultView(){
        this.code=ResultEnum.CODE_1.getCode();
        this.msg=ResultEnum.CODE_1.getMsg();
    }

    private ResultView(Object data){
        this.data=data;
        this.code=ResultEnum.CODE_1.getCode();
        this.msg=ResultEnum.CODE_1.getMsg();
        this.time=System.currentTimeMillis();
    }

    private ResultView(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.time=System.currentTimeMillis();
    }

    private ResultView(String errMsg) {
        this.code=ResultEnum.CODE_2.getCode();
        this.msg=ResultEnum.CODE_2.getMsg()+errMsg;
        this.time=System.currentTimeMillis();
    }
}
