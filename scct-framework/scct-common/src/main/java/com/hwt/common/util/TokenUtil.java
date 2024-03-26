package com.hwt.common.util;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.wx.common.constant.Constant;
import com.wx.common.constant.enums.PlatformType;
import com.wx.common.pojo.LoginUser;

/**
 * sa-token 工具
 *
 * @author lh
 */
public class TokenUtil {


    /**
     * 登录并设置sa-token属性
     *
     * @param userId     登录用户ID
     * @param tokenType  登录请求令牌类型
     * @param loginType  登录方式
     * @param timeout    有效时间
     */
    public static void login(Object userId,String tokenType, String loginType,Long timeout) {
        login(userId, Constant.IDENTITY_ADMIN, (long)PlatformType.OTHER.ordinal(),tokenType,loginType,null,timeout);
    }

    /**
     * 登录并设置sa-token属性
     *
     * @param userId     登录用户ID
     * @param identityId 登录身份ID
     * @param pfId       登录平台标识
     * @param tokenType  登录请求令牌类型
     * @param loginType  登录方式
     * @param timeout    有效时间
     */
    public static void login(Object userId, Long identityId, Long pfId, String tokenType, String loginType, Long logId, Long timeout) {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(userId);
        loginUser.setIdentityId(identityId);
        loginUser.setPfId(pfId);
        loginUser.setTokenType(tokenType);
        loginUser.setLoginType(loginType);
        loginUser.setLogId(logId);
        loginUser.setTimeout(timeout);
        SaLoginModel saLoginModel = new SaLoginModel();
        saLoginModel.setDevice(tokenType + "-" + pfId);
        saLoginModel.setExtra(String.valueOf(userId),loginUser);
        saLoginModel.setTimeout(timeout);
        StpUtil.login(loginUser.getUserId(),saLoginModel);
    }

    /**
     * 获取登录信息
     */
    public static LoginUser getLoginUser() {
        return JSON.parseObject(String.valueOf(StpUtil.getExtra(StpUtil.getLoginIdAsString())), LoginUser.class);
    }


}
