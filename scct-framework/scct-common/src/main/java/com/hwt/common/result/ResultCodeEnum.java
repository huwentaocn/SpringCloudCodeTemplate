package com.hwt.common.result;



import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * author : wx
 * date : 2022-06-14 09:54
 * description : 系统错误编码枚举类
 * @author lh
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {


    /**
     * 请求响应
     * 1 成功
     * 0 失败
     */
    SUCCESS(1, "成功"),

    FAIL(0, "失败"),

    FORBIDDEN_FAIL(401,"No login or permission"),

    HINT_FAIL(444, "失败提示"),

    // 重复请求
    REPEATED_REQUESTS_FAIL(900, "重复请求，请稍后重试"),


    PARAM_ERROR(1001,"传入参数错误"),

    /**
     * 数据故障 -3001 ~  -3999
     */
    DATA_ERROR(-3000, "数据异常"),

    DATA_EXIST_FAIL(-3001, "数据已存在"),

    DATA_NOT_EXIST_FAIL(-3002, "数据已删除或不存在"),

    DATA_IS_USED_FAIL(-3003, "数据被占用"),

    DATA_NON_EMPTY(-3004, "数据不为空"),

    JOIN_LIVE_FAIL(-3005,"该直播观看人数已达上限，看看其他直播吧~"),

    LIVE_STOP_STATUS(-3006,"直播已经结束"),

    LIVE_ING_STATUS(-3007,"直播中"),

    LIVE_PASSWORD_ERROR(-3008,"密码错误"),

    DATA_END(-3009,"已结束"),

    DATA_NO_START(-3010,"未开始"),

    NO_JURISDICTION(-3011,"没有参与权限"),

    DATA_SQLERROR(-3012,"执行的查询语句为空"),



    SYSTEM_ERROR500(500,"系统内部错误"),
    PASSWORD_ERROR(1002,"密码错误"),

    /**
     * 第三方故障  使用 -10001 ~  -19999
     */
    TRIPARTITE_FAIL(-10000, "三方异常"),
    APPLY_FAIL(7002,"申请失败"),

    SMS_SEND_FAIL(-10001, "短信发送失败"),

    WECHAT_CALL_FAIL(-10002, "微信调用失败"),

    WX_SAAS_AREA_SERVER_CALL_FAIL(-10003, "文香区域平台调用失败"),

    WECHAT_NOT_START_FAIL(-10004, "未开启微信登录功能"),

    QRCODE_INVALID_FAIL(-10005, "二维码失效"),

    ACCESS_IP_NOT_IN_WHITELIST(-10006, "访问ip不在白名单中"),



    // ========== 数据源配置 1001007000 ==========
    DATA_SOURCE_NOT_EXISTS(1001007000, "数据源配置不存在"),

    DATA_SOURCE_ALREADY_EXISTS(1001007001, "数据源配置已经存在"),
    DATA_SOURCE_NOT_OK(1001007002, "数据源配置不正确，无法进行连接"),

    DATA_SOURCE_DATABASE_ALREADY_EXISTS(1001007003, "数据库已经存在"),
    DATA_SOURCE_CREATE_DATABASE_ERROR(1001007004, "数据源创建库失败"),

    DATA_SOURCE_INIT_TABLE_STRUCT_ERROR(1001007005, "数据源初始化表结构失败"),
    DATA_SOURCE_INIT_TABLE_DATA_ERROR(1001007006, "数据源初始化表数据失败"),

    DATA_SOURCE_IS_USED(1001007007, "数据源被占用"),

    DATA_SOURCE_IS_NOT_EMPTY(1001007008, "数据源不为空"),

    // ========== 租户信息 1002015000 ==========
    TENANT_SWITCH_NOT_ENABLED(1002015000, "多租户开关未开启"),
    TENANT_NOT_EXISTS(1002015001, "租户不存在"),
    TENANT_DISABLE(1002015002, "名字为【{}】的租户已被禁用"),
    TENANT_EXPIRE(1002015003, "名字为【{}】的租户已过期"),

    TENANT_NAME_EXISTS(1002015004, "名字为【{}】的租户已存在"),
    TENANT_CAN_NOT_UPDATE_SYSTEM(1002015005, "系统租户不能进行修改、删除等操作！"),


    // ========== 租户套餐 1002016000 ==========
    TENANT_PACKAGE_NOT_EXISTS (1002016000, "租户套餐不存在"),
    TENANT_PACKAGE_USED (1002016001, "租户正在使用该套餐，请给租户重新设置套餐后再尝试删除"),
    TENANT_PACKAGE_DISABLE (1002016002, "名字为【{}】的租户套餐已被禁用"),
    DATA_INSERT_FAIL(2005,"添加失败"),
    DATA_UPDATE_FAIL(2006,"更新失败"),

    ;




    public Integer code;

    public String message;

}
