package com.hwt.common.util.ffmpeg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Author gxj
 * Date: 2024-03-08 13:28
 * description FFMpeg工具类
 */
public class FFMpegUtil {

    private int runtime = 0;

    private enum FFMpegUtilStatus {Empty, CheckingFile, GettingRuntime}

    ;
    private FFMpegUtilStatus status = FFMpegUtilStatus.Empty;
    private static FFMpegUtil instance = null;

    private boolean isSupported;

    private static Logger logger = LoggerFactory.getLogger(FFMpegUtil.class);


    public static FFMpegUtil getInstance() {
        if (instance == null) {
            syncInit();
        }
        return instance;
    }

    private static synchronized void syncInit() {
        if (instance == null) {
            instance = new FFMpegUtil();
        }

    }

    private FFMpegUtil() {
    }

    /**
     * 生成视频截图
     *
     * @param imageSavePath 截图文件保存全路径
     * @param screenSize    截图大小 如640x480
     */
    public void makeScreenCut(String originFileUri, String imageSavePath, String screenSize, String ffmpegUri) {
        List<String> cmd = new ArrayList<String>();
        cmd.add(ffmpegUri);
        cmd.add("-i");
        cmd.add(originFileUri);
        cmd.add("-y");
        cmd.add("-f");
        cmd.add("image2");
        cmd.add("-ss");
        cmd.add("5");
        cmd.add("-t");
        cmd.add("0.001");
        cmd.add("-s");
        cmd.add(screenSize);
        cmd.add(imageSavePath);
        logger.info(cmd.toString());
        CmdExecuter.exec(cmd, null);
    }

    /**
     * 视频截取
     *
     * @param ffmpegUri
     * @param originFileUri 源文件
     * @param stime         开始时间
     * @param tlength
     * @param fileSavePath  视频保存路径
     */
    public void videoCut(String originFileUri, String stime, String tlength, String fileSavePath, String ffmpegUri) {
        try {
            List<String> cmd = new ArrayList<String>();
            cmd.add(ffmpegUri);
            cmd.add("-ss");
            cmd.add(stime);
            cmd.add("-t");
            cmd.add(tlength);
            cmd.add("-i");
            cmd.add(originFileUri);
            cmd.add("-vcodec");
            cmd.add("copy");
            cmd.add("-acodec");
            cmd.add("copy");
            cmd.add(fileSavePath);
            //CmdExecuter.exec(cmd, null);

            String str = "";
            for (int i = 0; i < cmd.size(); i++) {
                str =str + cmd.get(i) + " ";
            }
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(str.toString());
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ( (line = br.readLine()) != null);


            logger.error(str);
        } catch (Exception e) {
            logger.error("videoCut", e);
        }

    }

    public void videoFormat(String srcfile, String fileSavePath, String format, String ffmpegUri) {
        List<String> cmd = new ArrayList<String>();
        cmd.add(ffmpegUri);
        cmd.add("-i");
        cmd.add(srcfile);
        cmd.add("-f");
        cmd.add(format);
        cmd.add(fileSavePath);
        CmdExecuter.exec(cmd, null);
    }


    //处理视频加快播放
    public void moveforward(String ffmpegUri, String currentFileUri, String targetFileUri) {
        List<String> cmd = new ArrayList<String>();
        cmd.add(ffmpegUri);
        cmd.add("-i");
        cmd.add(currentFileUri);
        cmd.add("-y");
        cmd.add("-c:v");
        cmd.add("copy");
        cmd.add("-c:a");
        cmd.add("copy");
        cmd.add("-movflags");
        cmd.add("faststart");
        cmd.add(targetFileUri);
        CmdExecuter.exec(cmd, null);
    }

    /**
     * mp4转m3u8格式
     * 拼接ffmpeg命令：ffmpeg -i test.mp4 -c:v libx264 -hls_time 60 -hls_list_size 0 -c:a aac -strict -2 -f hls output.m3u8
     *
     * @param source
     * @return
     */
    public void processM3U8(String source, String target, String ffmpegUri) {
        File targetFile = new File(target);
        File parentDir = targetFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        List<String> cmd = new ArrayList<String>();
        cmd.add(ffmpegUri);
        cmd.add("-i");
        cmd.add(source);
        cmd.add("-c:v");
        cmd.add("libx264");
        cmd.add("-hls_time");
        cmd.add("60");
        cmd.add("-hls_list_size");
        cmd.add("0");
        cmd.add("-c:a");
        cmd.add("aac");
        cmd.add("-strict");
        cmd.add("-2");
        cmd.add("-f");
        cmd.add("hls");
        cmd.add(target);
        CmdExecuter.exec(cmd, null);
    }
}
