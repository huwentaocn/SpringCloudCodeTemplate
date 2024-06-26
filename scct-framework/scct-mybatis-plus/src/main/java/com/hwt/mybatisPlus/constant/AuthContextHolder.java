package com.hwt.mybatisPlus.constant;

import lombok.Data;

/**
 * @Description 获取登录用户信息类
 * @Date 2023/8/31 10:39 星期四
 * @Author Hu Wentao
 */

@Data
public class AuthContextHolder {

    //用户id
    private static ThreadLocal<Long> userId = new ThreadLocal<Long>();

    //会员基本信息
    private static ThreadLocal<UserInfoVo> userInfoVo = new ThreadLocal<>();

    //userId操作的方法
    public static void setUserId(Long _userId) {
        userId.set(_userId);
    }

    public static Long getUserId() {
        return userId.get();
    }


    public static UserInfoVo getUserInfoVo() {
        return userInfoVo.get();
    }

    public static void setUserInfoVo(UserInfoVo _userLoginVo) {
        userInfoVo.set(_userLoginVo);
    }
}
