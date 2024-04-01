package com.hwt.common.exception;




import com.hwt.common.result.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * @Description 自定义全局异常实体
 * @Date 2023/8/31 14:02 星期四
 * @Author Hu Wentao
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private Object error;


    /**
     * 返回前端提示使用，全部默认为 fail = -1
     * @param msg 用户提示信息
     */
    public GlobalException(String msg){
        super(msg);
        this.code = ResultCodeEnum.FAIL.code;
        this.msg = msg;
    }

    /**
     * 返回前端提示使用，全部默认为 fail = -1
     * @param code 状态码
     * @param msg  用户提示信息
     */
    public GlobalException(int code, String msg){
        this.code = code;
        this.msg = msg;
    }


    /**
     * 返回前端提示使用，全部默认为 fail = -1
     * @param msg 用户提示信息
     * @param error 记录日志信息 error
     */
    public GlobalException(String msg, Object error){
        this.code = ResultCodeEnum.FAIL.code;
        this.msg = msg;
        this.error = error;
    }

    /**
     *
     * @param resultCodeEnum
     */
    public GlobalException(ResultCodeEnum resultCodeEnum){
        this.code = resultCodeEnum.code;
        this.msg = resultCodeEnum.message;
    }

    /**
     * 返回指定code码，详细错误信息
     * @param codeEnum
     * @param errorMessage
     */
    public GlobalException(ResultCodeEnum codeEnum, String errorMessage){
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMessage() + "==>" + errorMessage;
    }

}
