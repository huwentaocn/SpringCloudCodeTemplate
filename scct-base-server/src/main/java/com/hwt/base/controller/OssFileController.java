package com.hwt.base.controller;

import com.hwt.base.pojo.vo.FileVo;
import com.hwt.base.service.OssFileService;
import com.hwt.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * oss文件表 前端控制器
 * </p>
 *
 * @author Hu Wentao
 * @since 2024-03-30
 */
@Slf4j
@Api(tags = "OSS文件管理模块")
@RestController
@CrossOrigin
@RequestMapping("/oss/file")
public class OssFileController {

    @Autowired
    private OssFileService ossFileService;

    @ApiOperation(value = "oss文件上传", notes = "oss文件上传")
    @PostMapping("/upload/file")
    public Result<FileVo> uploadFile(MultipartFile file) {

        return Result.success(ossFileService.uploadFile(file));
    }

}
