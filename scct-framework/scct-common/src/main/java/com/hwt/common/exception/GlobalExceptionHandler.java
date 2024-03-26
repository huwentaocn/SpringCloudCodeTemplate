package com.hwt.common.exception;

import com.hwt.common.result.Result;
import com.hwt.common.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


/**
 * author : wx
 * date : 2022-03-02 15:35
 * description : 全局异常处理
 * @author lh
 */
@Slf4j
@Component
@RestControllerAdvice
public class GlobalExceptionHandler<T> {


    /**
     * 自定义异常 GlobalException
     * 用于前端弹出提示
     */
    @ExceptionHandler(value = {GlobalException.class})
    public Result<T> customHandleException(HttpServletRequest request, GlobalException e) {
        String method = request.getRequestURI();
        log.error(" method: " + method + " ====> 触发自定义异常！原因是:"+ e.getMsg());
        return Result.fail(ResultCodeEnum.HINT_FAIL.code,e.getMsg());
    }



    /**
     * 运行时异常 RuntimeException
     */
    @ExceptionHandler(value = RuntimeException.class)
    public Result<T> handleRuntimeException(HttpServletRequest request, RuntimeException e) {
        String method = request.getRequestURI();
        log.error(" method: " + method + " ====> 触发运行时未知异常！原因是：{} ",e);
        return Result.fail(e.getMessage());
    }


    /**
     * 传参异常
     * 注解 @Valid 和 @RequestBody 联合使用
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<T> handleValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        String method = request.getRequestURI();
        BindingResult bindingResult = e.getBindingResult();
        String error = BindingResultResponse.getError(bindingResult);
        log.error(" method: " + method + " ====> 触发入参异常！原因是:",error);
        return Result.fail(error);
    }

    /**
     * 数据异常 RuntimeException  FAIL(-1,"fail")
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result<T> handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException e) {
        String method = request.getRequestURI();
        log.error(" method: " + method + " ====> 触发数据异常！原因是:"+ e);
        return Result.fail(e.getMessage());
    }

}


