package com.hwt.common.exception;

/**
 * @author : wx
 * date : 2023-10-09 13:42
 * description : 自定义返回提示
 */
public class GlobalExceptionConstant {


    public static final String USER_PHONE_NUMBER_EXIST = "手机号已存在";

    public static final String USER_EMAIL_EXIST = "邮箱已存在";

    public static final String USER_PASSWORD_NOT_RIGHT = "密码不正确";

    public static final String USER_CODE_NOT_RIGHT = "验证码不正确";

    public static final String QRCODE_INVALID_FAIL = "二维码失效";

    public static final String USER_NOT_EXIT = "用户不存在";
    public static final String USER_NOT_SAME = "用户不一致";
    public static final String USER_UNAUTHORIZED = "用户未授权";


    public static final String USER_ADMIN_NOT_MOD = "不允许操作超级管理员用户";

    public static final String USER_NOT_DELETE = "当前用户不能删除";

    public static final String USER_NOT_PERMISSION = "用户权限不足";

    public static final String USER_NOT_ROLE_PERMISSION = "未分配权限";

    public static final String COMMON_NOT_EMPTY = "请至少选择一条数据";

    public static final String COMMON_DATA_NOT_EXIST  = "数据不存在";

    public static final String COMMON_DATA_EXIST = "数据已存在";

    public static final String COMMON_DATA_NOT_USE = "数据已停用";

    public static final String COMMON_DATA_USE = "数据已使用,不能删除";

    public static final String COMMON_HAS_CHILD = "存在子集，不能删除";
}
