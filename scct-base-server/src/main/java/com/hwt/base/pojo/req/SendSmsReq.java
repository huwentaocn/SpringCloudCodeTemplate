package com.hwt.base.pojo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description 发送短信请求体
 * @Date 2023/8/28 10:17 星期一
 * @Author Hu Wentao
 */

@Data
@ApiModel(value = "SendSmsReq", description = "发送短信请求体")
public class SendSmsReq {

    @NotBlank(message = "电话号码未传")
    @ApiModelProperty(name = "phoneNumber", value = "电话号码", required = true)
    private String phoneNumber;

    @NotNull(message = "时长类型未传")
    @ApiModelProperty(name = "timeType", value = "时长类型：1一分钟，2二分钟，5五分钟", required = true)
    private Integer timeType;

}
