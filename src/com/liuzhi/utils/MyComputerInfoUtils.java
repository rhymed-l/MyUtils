package com.liuzhi.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class MyComputerInfoUtils
{
    private MyComputerInfoUtils(){}

    private static Properties props = System.getProperties();
    private static InetAddress addr;

    static {
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取操作系统名
     */
    public static String getSystemName()
    {
        return props.getProperty("os.name");
    }

    /**
     * 获取操作系统版本
     */
    public static String getgetSystemVersion()
    {
        return props.getProperty("os.version");
    }
    /**
     * 获取电脑名称
     */
    public static String getComputerName()
    {
        return addr.getHostName();
    }
    /**
     * 获取电脑IP地址
     */
    public static String getComputerIpAddress()
    {
        return addr.getHostAddress();
    }
}
