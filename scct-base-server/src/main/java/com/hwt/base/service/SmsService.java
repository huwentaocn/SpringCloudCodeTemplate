package com.hwt.base.service;

import com.hwt.base.pojo.req.SendSmsReq;

/**
 * @Description 短信模块接口层
 * @Date 2023/8/26 13:57 星期六
 * @Author Hu Wentao
 */
public interface SmsService {
    void sendCommonMessage(SendSmsReq req);
}
