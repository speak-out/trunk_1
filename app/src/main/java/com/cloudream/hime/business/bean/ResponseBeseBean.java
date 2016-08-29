package com.cloudream.hime.business.bean;

import java.io.Serializable;

/**
 * Created by yuer on 2016/8/26.
 */
public class ResponseBeseBean implements Serializable {
    /**
     *   "result": [
     {
     输出参数
     },
     ],
     "code": "0",
     "msg": "成功!"
     */
    private int code ;
    private String msg;
    private String result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
