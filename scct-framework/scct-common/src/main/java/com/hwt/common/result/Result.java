package com.hwt.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @author : Hu wentao
 * date : 2022-05-14 09:54
 * description : 全局返回结构
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 响应码
     */
    public int code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 详细信息
     */
    private Object errorMsg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 成功，默认状态码,默认响应信息
     * 无自定义数据
     * @param <T> 返回类泛型
     */
    public static <T> Result<T> success(){
        return success(ResultCodeEnum.SUCCESS.code,ResultCodeEnum.SUCCESS.message,null);
    }

    /**
     * 成功，默认状态码,默认响应信息
     * 自定义数据
     * @param <T> 返回类泛型
     * @param data 响应数据
     */
    public static <T> Result<T> success(T data){
        return success(ResultCodeEnum.SUCCESS.code,ResultCodeEnum.SUCCESS.message,data);
    }

    /**
     * 成功（内部调用）
     * @param <T> 返回类泛型
     * @param code 响应码
     * @param msg  响应信息
     * @param data 响应数据
     */
    private static <T> Result<T> success(int code, String msg, T data){
        return new Result<>(code,msg,data);
    }

    /**
     * 失败，默认状态码,默认响应信息
     * 无自定义数据
     * @param <T> 返回类泛型
     */
    public static <T> Result<T> fail() {
        return fail(ResultCodeEnum.FAIL.code,ResultCodeEnum.FAIL.message);
    }

    /**
     * 成功，默认状态码,默认响应信息
     * 自定义数据
     * @param <T> 返回类泛型
     * @param msg 响应信息
     */
    public static <T> Result<T> fail(String msg) {
        return fail(ResultCodeEnum.FAIL.code,msg);
    }


    /**
     * 失败（内部调用）
     * @param <T> 返回类泛型
     * @param code 响应码
     * @param msg  响应信息
     */
    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code,msg,null);
    }

    public static<T> Result<T> fail(ResultCodeEnum codeEnum, String errorMessage){
        return new Result<>(codeEnum.getCode(), codeEnum.getMessage() + "==>" + errorMessage, null);
    }


    /**
     * 失败（内部调用）
     * @param <T> 返回类泛型
     * @param code 响应码
     * @param msg  响应信息
     */
    public static <T> Result<T> fail(int code, String msg, T data) {
        return new Result<>(code,msg,data);
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
