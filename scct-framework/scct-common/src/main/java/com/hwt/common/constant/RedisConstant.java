package com.hwt.common.constant;

/**
 * @author : wx
 * date : 2022-07-13 09:24
 * description : redis  常量
 */
public class RedisConstant {


    /**
     * 存储登录用户token前缀(文香)
     */
    public static final String LOGIN_USER_KEY = "login_user_key:";



    /**
     * 存储短信验证码登录前缀(文香)
     */
    public static final String SMS_LOGIN_CODE = "sms:login_code:";


    /**
     * 存储邮箱验证码登录前缀(文香)
     */
    public static final String MAIL_LOGIN_CODE = "mail:login_code:";


    /**
     * 访问后缀
     */
    public static final String LOGIN_USER_KEY_ACCESS = ":access:";


    /**
     * 刷新后缀
     */
    public static final String LOGIN_USER_KEY_REFRESH = ":refresh:";



    /**
     * 存储单点登录code的前缀
     */
    public static final String SSO_CODE_KEY = "sso_key:code:";


    /**
     * 存储三方使用 clientId 和 clientSecret 请求的token
     */
    public static final String LOGIN_USER_KEY_CLIENT = LOGIN_USER_KEY + "client:";


    /**
     *  防重复提交
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit_key:";


    /**
     * code 有效时间（单位：毫秒） 30 秒
     */
    public static final long SSO_CODE_KEY_VALID_TIME = 1000 * 60 * 30;


    /**
     * 存储单点登录token的前缀
     */
    public static final String SSO_TOKEN_KEY = "sso_key:token:";


    /**
     * token 有效时间（单位：毫秒）  2小时
     */
    public static final long SSO_TOKEN_KEY_VALID_TIME = 1000 * 60 * 60 * 2;

    /**
     * 微信扫码登录缓存key
     */
    public static final String WECHAT_QRCODE_LOGIN_KEY = "wechat_qrcode_login_key:";


    /**
     * 分片上传文件相关
     */
    public static final String FILE_UPLOAD_KEY = "file_upload_key:";

    public static final String FILE_UPLOAD_KEY_INIT = "init";

    public static final String FILE_UPLOAD_KEY_PART = "part";

    public static final long FILE_INIT_KEY_VALID_TIME = 1000 * 60 * 60 * 24 * 7;




}
