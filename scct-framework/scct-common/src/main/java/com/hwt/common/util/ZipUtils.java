package com.hwt.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author wds
 * @version 1.0
 * @date 2023/6/6 9:56
 */
public class ZipUtils {

    /**
     * 将指定路径下的多个文件压缩到一个zip文件中
     * @param fileList 待压缩的文件名列表
     * @param zipFilePath 压缩后的zip文件路径
     */
    public static void compressFiles(List<String> fileList, String zipFilePath) throws IOException {
        byte[] buffer = new byte[1024];
        List<File> filesToZip = new ArrayList<>();
        for (String fileName : fileList) {
            filesToZip.add(new File(fileName));
        }
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            for (File file : filesToZip) {
                ZipEntry ze = new ZipEntry(file.getName());
                zos.putNextEntry(ze);
                try (FileInputStream in = new FileInputStream(file)) {
                    int len;
                    while ((len = in.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                }
                zos.closeEntry();
            }
            // 关闭ZipOutputStream
            zos.finish();
        }
    }
}
