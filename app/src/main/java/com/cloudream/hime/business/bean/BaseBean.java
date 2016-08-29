package com.cloudream.hime.business.bean;

import java.util.ArrayList;

/**
 * Created by yuer on 2016/8/26.
 */
public class BaseBean<T>{
    String jsonrpc;
    ArrayList<T> params;

    public String getJsonrpc() {
        return jsonrpc = "2.0";
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public ArrayList<T> getParams() {
        return params;
    }

    public void setParams(ArrayList<T> params) {
        this.params = params;
    }
}

