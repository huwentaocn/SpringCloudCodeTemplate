package com.hwt.mybatisPlus.constant;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description 用户信息响应体
 * @Date 2023/8/28 15:46 星期一
 * @Author Hu Wentao
 */

@Data
public class UserInfoVo {

    /**
     * 唯一id
     */
    private Long id;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 昵称
     */
    private String name;

    /**
     * 性别：1男，2女， 默认1
     */
    private Integer sex;

    /**
     * 头像url
     */
    private String headUrl;

    /**
     * 账号状态（0正常，1停用），默认0
     */
    private Integer status;

    /**
     * 电话号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 最后登录ip
     */
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Integer sort;
}
