package com.cloudream.hime.business.config;

import com.cloudream.hime.business.utils.MD5Utils;

/**
 * 常量类
 */
public class Constant {
    public static final String serverName ="hair.cloud.net";
    public static String serverIp ="http://test.cloudream.net/";
    public static final String ServerNameMd5= MD5Utils.getMD5(Constant.serverName);

}
