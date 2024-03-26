package com.hwt.common.constant;

import java.text.MessageFormat;

/**
 * @Description 消息常量
 * @Date 2024/3/18 9:51 星期一
 * @Author Hu Wentao
 */

public enum NoticeConstant {

    /**
     * 1-1：角色申请通知；1-2：角色审核结果通知；2-1：直播通知；3-1：互动通知；4-1：教研组邀请通知；4-2：教研组申请加入通知；4-3：教研组申请加入审核结果通知
     */

    IDENTITY_APPLY_NOTICE("1-1", 1, 2, 2, 1, 1, "身份申请通知", "{0}申请加入【{1}】身份，点击跳转到审核页面！"),

    IDENTITY_APPLY_DEAL_NOTICE("1-2", 1, 2, 2, 1, 1, "身份审核结果通知", "您申请加入【{0}】身份，已被{1}{2}！"),

    LIVE_EVENT_NOTICE("2-1", 1, 2, 3, 2, 0, "直播活动通知", "您的直播活动即将开始，快去直播吧！"),

    DELIVER_CLASS_NOTICE("3-1", 1, 2, 3, 3, 0, "远程互动通知", "您有一条互动消息，快去查看吧 ！"),

    TEACH_RESEARCH_GROUP_INVITE_NOTICE("4-1", 1, 2, 2, 4, 2, "教研组邀请通知", "{0}老师({1})邀请您加入【{2}】教研组！"),

    TEACH_RESEARCH_GROUP_APPLY_NOTICE("4-2", 1, 2, 2, 4, 1, "教研组申请加入通知", "{0}老师({1})申请加入【{2}】教研组， 点击跳转到审核页面！"),

    TEACH_RESEARCH_GROUP_APPLY_DEAL_NOTICE("4-3", 1, 2, 2, 4, 0, "教研组申请加入审核通知", "您申请加入【{0}】教研组的申请，已被{1}{2}！"),



    ;

    //发布类型(1-1：角色审核通知；2-1：直播通知；3-1：互动通知；4-1：教研组邀请通知；4-2：教研组申请加入通知；4-3：教研组申请加入审核通过/拒绝通知)
    private final String publishType;

    //平台id：1三个课堂
    private final Integer pfType;

    //类型：1公告，2通知
    private final Integer type;

    //触发类型：1人为发送，2自动触发，3定时触发
    private final Integer triggerType;

    //业务类型：1系统通知，2直播通知，3互动通知，4教研通知
    private final Integer businessType;

    // 操作类型：0无操作，1跳转，2选择
    private final Integer operateType;

    //标题
    private final String title;

    //内容
    private final String content;

    NoticeConstant(String publishType, Integer pfType, Integer type, Integer triggerType, Integer businessType, Integer operateType, String title, String content) {
        this.publishType = publishType;
        this.pfType = pfType;
        this.type = type;
        this.triggerType = triggerType;
        this.businessType = businessType;
        this.operateType = operateType;
        this.title = title;
        this.content = content;
    }

    public String getPublishType() {
        return publishType;
    }

    public Integer getPfType() {
        return pfType;
    }

    public Integer getType() {
        return type;
    }

    public Integer getTriggerType() {
        return triggerType;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getContent(String ... params) {
        return MessageFormat.format(getContent(), params);
    }

}
