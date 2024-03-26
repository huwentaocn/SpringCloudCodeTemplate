package com.hwt.common.constant;

/**
 * @author lh
 * 通用常量信息
 */
public class Constant {

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * www主域
     */
    public static final String WWW = "www.";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 不能为空
     */
    public final static String NON_EMPTY  = "参数不能为空";

    /**
     * 系统故障
     */
    public final static String SYSTEM_FAIL = "系统故障";


    /**
     * 请求端(网页端)
     */
    public static final String REQUESTER_WEB = "web";

    /**
     * 请求端(移动端)
     */
    public static final String REQUESTER_APP = "app";

    /**
     * 请求端(客户端)
     */
    public static final String REQUESTER_CLIENT = "client";

    /**
     * 请求端(录播端)
     */
    public static final String REQUESTER_RECORD = "record";



    /**
     * 超管用户和角色id
     */
    public static final Long ADMIN_ID = 1L;

    /**
     * 0
     */
    public static final String ZERO_STR = "0";

    /**
     * 1
     */
    public static final String ONE_STR = "1";

    /**
     * 2
     */
    public static final String TWO_STR = "2";

    /**
     * 3
     */
    public static final String THREE_STR = "3";

    /**
     * 微信扫码登录
     */
    public static final String FOUR_STR = "4";



    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";


    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";


    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "1";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "0";


    /**
     * 密码加密密钥
     */
    public static final String PASSWORD_ENCRYPTION_KEY = "anhuiwenxiangisgreat";

    /**
     * 默认密码
     */
    public static final String PASSWORD_DEFAULT = "wx123456";

    /**
     * 初始管理员账号
     */
    public static final String ADMIN_USERNAME = "admin";



    /**
     * 身份-其它
     */
    public static final Long IDENTITY_OTHER = 0L;


    /**
     * 身份-管理员
     */
    public static final Long IDENTITY_ADMIN = 1L;

    /**
     * 身份-教师
     */
    public static final Long IDENTITY_TEACHER = 2L;

    /**
     * 身份-学生
     */
    public static final Long IDENTITY_STUDENT = 3L;
    /**
     * 身份-家长
     */
    public static final Long IDENTITY_PARENTS = 4L;
    /**
     * 身份-游客
     */
    public static final Long IDENTITY_VISITOR = 5L;

    /**
     * 业务类型-课程
     */
    public static final Integer BUSINESS_COURSE = 1;
    /**
     * 业务类型-直播
     */
    public static final Integer BUSINESS_LIVE = 2;
    /**
     * 业务类型-课例评课
     */
    public static final Integer BUSINESS_LESSON_SCHEDULE = 3;
    /**
     * 业务类型-教研直播
     */
    public static final Integer BUSINESS_TEACHING_LIVE = 4;
    /**
     * 业务类型-教研互动
     */
    public static final Integer BUSINESS_TEACHING_INTERACTION = 5;
    /**
     * 业务类型-系列课程
     */
    public static final Integer BUSINESS_SERIES_COURSE = 6;
    /**
     * 业务类型-专递课程
     */
    public static final Integer BUSINESS_DELIVER_COURSE = 7;
    /**
     * 业务关联附件类型-视频相关
     */
    public static final Integer BUSINESS_RESOURCE_VIDEO = 0;
    /**
     * 业务关联附件类型-文档相关，如doc、ppt、xls等
     */
    public static final Integer BUSINESS_RESOURCE_FILE = 1;
    /**
     * 开启
     */
    public static final String RECORD_ACTION_ON = "on";
    /**
     * 关闭
     */
    public static final String RECORD_ACTION_OFF = "off";


    /**
     * 文件路径映射前缀
     */
    public static final String FILE_RES = "/res/";
}
