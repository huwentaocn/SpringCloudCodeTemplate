package com.hwt.common.constant;

import lombok.Data;

/**
 * @Description 请求头常量
 * @Date 2023/8/31 14:02 星期四
 * @Author Hu Wentao
 */

@Data
public class HeaderConstant {

    public static final String AUTHORIZATION = "Authorization";

    public static final String TOKEN = "token";


    /**
     * 租户id
     */
    public static final String TENANT_ID = "tenant-id";

    /**
     * 加密数据
     */
    public static final String ENCRYPTED_DATA = "encrypted-data";
}
