package com.hwt.base.service.impl;

import com.hwt.base.pojo.entity.SystemUser;
import com.hwt.base.mapper.SystemUserMapper;
import com.hwt.base.service.SystemUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Hu Wentao
 * @since 2024-03-30
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

}
