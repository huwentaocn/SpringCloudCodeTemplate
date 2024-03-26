package com.hwt.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;


/**
 * @author : wx
 * date : 2023-10-08 16:25
 * description : 获取地址类
 */
public class AddressUtils {

    private static RestTemplate restTemplate;

    public AddressUtils() {
    }

    public AddressUtils(RestTemplate restTemplate) {
        AddressUtils.restTemplate = restTemplate;
    }

    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    // IP地址查询
    private static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    private static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        try {
            String url = IP_URL + "ip=" + ip + "&json=true";
            String rspStr = restTemplate.getForObject(url, String.class);
            if (!StringUtils.hasLength(rspStr)) {
                log.error("获取地理位置异常 {}", ip);
                return UNKNOWN;
            }
            //JSONObject obj = JSONObject.parseObject(rspStr);
           // String region = obj.getString("pro");
            //String city = obj.getString("city");
            return String.format("%s %s", "beijing", "daxing");
        } catch (Exception e) {
            log.error("获取地理位置异常 {}", ip);
        }
        return UNKNOWN;
    }
}
