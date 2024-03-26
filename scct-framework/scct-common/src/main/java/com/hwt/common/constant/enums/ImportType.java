package com.hwt.common.constant.enums;

/**
 * 导入数据类型
 * @author lh
 * 导入模块类型
 * 1管理员 2管理员修改 3教师 4教师修改 5学生 6学生修改 7角色用户 8楼宇 9教室 10行政班 11教学班 12课表 13 组织架构
 */
public enum ImportType
{
    /**
     * 其它
     */
    OTHER,

    /**
     * 管理员
     */
    ADMIN,

    /**
     * 管理员修改
     */
    ADMIN_UPDATE,

    /**
     * 教师
     */
    TEACHER,

    /**
     * 教师修改
     */
    TEACHER_UPDATE,

    /**
     * 学生
     */
    STUDENT,

    /**
     * 学生修改
     */
    STUDENT_UPDATE,

    /**
     * 楼宇
     */
    BUILDING,

    /**
     * 教室
     */
    CLASSROOM,

    /**
     * 角色用户
     */
    ROLE_USER,

    /**
     * 行政班
     */
    CLASS_ADMIN,


    /**
     * 教学班
     */
    CLASS_TEACHING,



    /**
     * 课表导入
     */
    COURSE_TIMETABLE,


    /**
     * 组织架构
     */
    DEPT,



}
