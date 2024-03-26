package com.hwt.common.constant;

/**
 * @description: 设备主题(服务端发送) 所有topic 后面同一 /#  # 代表 设备sn
 * @author: gxj
 * @date: Created in 2024-01-03 13:32
 * @copyright: Copyright (c) 2024
 * @version: V1.0
 * @modified: gxj
 */
public class DevPubTopic {

    /**
     * 通用
     */
    private static final String PUB_RECORD_TOPIC = "/edu/cc/bb/cmd";


    /**
     * 录制控制
     */
    public static final String PUB_RECORD_RECORD_TOPIC = PUB_RECORD_TOPIC + "/record/";


    /**
     * 直播控制
     */
    public static final String PUB_RECORD_LIVE_TOPIC = PUB_RECORD_TOPIC + "/live/";


    /**
     * 开关机控制
     */
    public static final String PUB_RECORD_CMD_TOPIC = PUB_RECORD_TOPIC + "/";



    /************************************** 新设备使用 ***********************************/


    /**
     *  直播控制
     *  { action:on    on:开始直播  off:停止直播
     *   liveId:xxxxxx   直播id
     *   liveType: 0:录制直播   1:微课直播
     *   }
     */
    public static final String PUB_NEW_RECORDER_LIVE_TOPIC = "/edu/recorder/cmd/live/";


    /**
     *  录制控制
     *  { action:on    on:开始录制  off:停止录制 }
     */
    public static final String PUB_NEW_RECORDER_RECORD_TOPIC = "/edu/recorder/cmd/record/";


    /**
     *  设备上下线控制
     *  { action:on    on:开机  off:关机 }
     */
    public static final String PUB_NEW_RECORDER_ONLINE_TOPIC = "/edu/recorder/cmd/online/";


    /**
     *  创建直播/互动   更新直播/互动
     *  发送状态提醒，监听方刷新直播/互动列表
     *  { type: live,action:create,id:xxx }
     *  type: live 直播
     *  type: activity 互动
     *  /#1/#2
     *  #1 代表设备sn
     *  #2 代表接收方标识
     */
    public static final String PUB_NEW_RECORDER_NOTIFY_TOPIC = "/edu/recorder/notify/";



    public static final String PUB_NEW_RECORDER_NOTIFY_TYPE = "1";


    public static final String PUB_NEW_APPLET_NOTIFY_TYPE = "2";

    /**
     * 平台增删改查设备指令 设备监听此指令 有变动则调用详情接口
     */
    public static final String PUB_NEW_RECORDER_UPBIND_TOPIC = "/edu/recorder/cmd/upbind/";

    /**
     * 修改设备设置基础信息指令 增删改查有变动也要调详情接口
     */
    public static final String PUB_NEW_RECORDER_UPDEVSET_TOPIC = "/edu/recorder/cmd/updevset/";

    /**
     * 开启/关闭设备巡课
     */
    public static final String PUB_NEW_DEV_UPDEVSET_TOPIC ="/edu/cc/bb/cmd/webrtc/";

    /**
     * 开启上传设备帧画面
     */
    public static final String PUB_NEW_DEV_UPDEVFRAME_TOPIC ="/edu/cc/bb/cmd/frame/";

    /**
     * 上报录播设备状态
     */
    public static final String PUB_NEW_DEV_UPRECORDERSTATUS_TOPIC ="/edu/recorder/cmd/recorderstatus/";

    /**
     * 上报摄像机设备状态
     */
    public static final String PUB_NEW_DEV_UPCAMERASTATUS_TOPIC ="/edu/camera/cmd/camerastatus/";
    /**
     * 上报大屏设备状态
     */
    public static final String PUB_NEW_DEV_UPLARGESCREENSTATUS_TOPIC ="/edu/largescreen/cmd/largescreenstatus/";

    /**
     * 平台用户创建课程关联到录播设备 通知设备刷新课程列表
     */
    public static final String PUB_NEW_RECORDER_REFCOURES_TOPIC = "/edu/recorder/cmd/refcoures/";
}
