package com.hwt.common.constant.enums;

/**
 * @Description 设备类型枚举
 * @Date 2022/9/30 11:44 星期五
 * @Author Hu Wentao
 */
public enum CourseSourceTypeEnum {

    WEB(1, "web端"),

    PC(2, "PC端"),

    APP(3, "APP端"),

    RECORDING_AND_BROADCASTING_EQUIPMENT(4, "录播设备端"),

    ;


    public final Integer code;

    public final String message;

    CourseSourceTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
