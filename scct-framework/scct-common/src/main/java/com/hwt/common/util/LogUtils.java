package com.hwt.common.util;

/**
 * @author : wx
 * date : 2023-10-08 16:25
 * description : 处理并记录日志文件
 */
public class LogUtils
{
    public static String getBlock(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
