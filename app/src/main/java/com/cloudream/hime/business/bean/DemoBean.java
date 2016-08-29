package com.cloudream.hime.business.bean;

import java.util.ArrayList;

/**
 * Created by yuer on 2016/8/26.
 */
public class DemoBean {
    /**
     * {"jsonrpc":"2.0","params":[{"type":3,"cityId":440300}]}
     */
    String jsonrpc;
    ArrayList<com.cloudream.hime.business.bean.Params> params;
    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public ArrayList<com.cloudream.hime.business.bean.Params> getParams() {
        return params;
    }

    public void setParams(ArrayList<com.cloudream.hime.business.bean.Params> params) {
        this.params = params;
    }
}

