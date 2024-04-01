package com.hwt.common.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * @Description 封装请求错误信息返回参数
 * @Date 2023/8/31 14:02 星期四
 * @Author Hu Wentao
 */
public class BindingResultResponse {

    public static String getError(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String error = null;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getDefaultMessage();
        }
        return error;
    }
}
