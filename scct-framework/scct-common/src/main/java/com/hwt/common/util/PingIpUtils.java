package com.hwt.common.util;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;

@Slf4j
public class PingIpUtils {

    public  static boolean ping(String ipAddress) {
        boolean isReachable = false;
        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            isReachable = inetAddress.isReachable(10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isReachable;
    }
}
