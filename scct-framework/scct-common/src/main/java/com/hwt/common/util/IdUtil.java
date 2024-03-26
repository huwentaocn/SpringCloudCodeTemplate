package com.hwt.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author : wx
 * date : 2023-10-08 16:25
 * description : id 工具类
 */
public class IdUtil {

    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        //去除"-"
        uuidStr = uuidStr.replace("-", "");
        return uuidStr;
    }

    public static String getRandom(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getNumberRandom(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取6-10 的随机位数数字
     *
     * @param length 想要生成的长度
     * @return result
     */
    public static String getRandom620(Integer length) {
        String result = "";
        Random rand = new Random();
        int n = 20;
        if (null != length && length > 0) {
            n = length;
        }
        int randInt = 0;
        for (int i = 0; i < n; i++) {
            randInt = rand.nextInt(10);
            result += randInt;
        }
        return result;
    }


    /**
     * 生成19位id标识
     * @return id
     */
    public static String getSnowIdStr(){
        // 1.snowType 类型(4位)
        String snowType = "3709" ;
        // 2.中间四位  日期
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String time = sdf.format(new Date());
        // 3.生成uuid的hashCode
        int hashCode  = UUID.randomUUID().toString().hashCode();
        // 4.处理负数
        if(hashCode < 0){
            hashCode = -hashCode;
        }
        // 算法处理 0-前面补0; 10-代表长度为10; d-代表参数为正数
        return snowType + time + String.format("%010d",hashCode);
    }

    /**
     * 生成19位id标识
     * @return id
     */
    public static Long getSnowId(){
        return Long.valueOf(getSnowIdStr());
    }
}
