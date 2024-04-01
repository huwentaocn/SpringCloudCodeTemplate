package com.hwt.base.controller;

import com.hwt.base.pojo.req.SendSmsReq;
import com.hwt.base.service.SmsService;
import com.hwt.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description 短信管理模块
 * @Date 2023/8/26 13:55 星期六
 * @Author Hu Wentao
 */

@Slf4j
@Api(tags = "短信管理模块")
@RestController
@CrossOrigin
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @ApiOperation(value = "发送通用短信", notes = "发送通用短信")
    @PostMapping("/send/common/message")
    public Result sendCommonMessage(@RequestBody @Valid SendSmsReq req) {
        smsService.sendCommonMessage(req);
        return Result.success();
    }
}
