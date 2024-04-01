package com.hwt.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hwt.base.pojo.entity.OssFile;
import com.hwt.base.pojo.vo.FileVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * oss文件表 服务类
 * </p>
 *
 * @author Hu Wentao
 * @since 2024-03-30
 */
public interface OssFileService extends IService<OssFile> {


    FileVo uploadFile(MultipartFile file);

}
