package com.hwt.security.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 用户对象
 * @Date 2024/3/30 13:48 星期六
 * @Author Hu Wentao
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 盐
     */
    private String salt;

    /**
     * token
     */
    private String token;

}

