package com.hwt.common.util;

import com.wx.common.util.ffmpeg.FFMpegUtil;

import java.io.File;
import java.util.Date;

/**
 * Author gxj
 * Date: 2024-03-08 13:36
 * description 截取视频帧工具类
 */
public class CaptureFrameUtil {

    /**
     * 获取视频第一帧图片
     * @param videoPath 视频地址(服务器本地磁盘文件路径)
     */
    public static String makeScreenCut(String videoPath, String resDirectory, String resDir, String ffmpegPath){
        //新文件名(加个时间戳,尽量避免文件名称重复)
        String fileName = DateUtil.dateToLong(new Date()) + StringUtils.getNumberRandom(6) + ".jpg";
        //每一年创建一个大的目录
        String year = DateUtil.getSimpleYear(new Date());
        // 创建年份文件夹
        File yearFolder = new File(resDirectory + "/" + year);
        if (!yearFolder.exists()) {
            yearFolder.mkdir();
        }
        //每一个月创建一个小的目录
        String month = DateUtil.getSimpleMonth(new Date());
        // 创建月份文件夹
        File monthFolder = new File(yearFolder.getPath() + "/" + month);
        if (!monthFolder.exists()) {
            monthFolder.mkdir();
        }
        // 图片绝对路径
        String fileUrl = resDirectory + year + "/" + month + "/" + fileName;
        // 图片相对路径
        String accessUrl = resDir + year + "/" + month + "/" + fileName;
        // 创建对应文件夹
        FFMpegUtil.getInstance().makeScreenCut(videoPath, fileUrl, "640x360",ffmpegPath);
        return accessUrl;

    }
}
