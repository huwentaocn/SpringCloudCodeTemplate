package com.hwt.common.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.util.IOUtils;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @Description excel导入导出工具类
 * @Date 2023/4/24 13:46 星期一
 * @Author Hu Wentao
 */
public class ExcelUtil {

    /**
     * excel导出
     *
     * @param response
     * @param fileName  文件名
     * @param sheetName sheet名
     * @param data      数据
     * @param clazz     数据类型:(例：User.class)
     * @throws Exception
     */
    public static void writeExcel(HttpServletResponse response, String fileName, String sheetName, List<?> data, Class clazz) throws Exception {

        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String newFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");

        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
//        String newSheetName = URLEncoder.encode(sheetName,"UTF-8").replaceAll("\\+","%20");

//        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //此处指定了文件类型为xls，如果是xlsx的，请自行替换修改
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + newFileName + ".xlsx");
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "no-store");
        response.addHeader("Cache-Control", "max-age=0");

        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置表头居中对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置内容靠左对齐
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        EasyExcel.write(response.getOutputStream(), clazz).autoCloseStream(true).registerWriteHandler(horizontalCellStyleStrategy).sheet(sheetName).doWrite(data);
    }

    /**
     * excel导出，失败的时候返回json
     *
     * @param response
     * @param fileName
     * @param sheetName
     * @param data
     * @param clazz
     * @throws Exception
     */
    public static void writeExcelFailedUsingJson(HttpServletResponse response, String fileName, String sheetName, List<?> data, Class clazz) throws Exception {
        try {
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String newFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");

            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String newSheetName = URLEncoder.encode(sheetName, "UTF-8").replaceAll("\\+", "%20");

//            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            //此处指定了文件类型为xls，如果是xlsx的，请自行替换修改
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + newFileName + ".xlsx");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");

            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            //设置表头居中对齐
            headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            //内容样式
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            //设置内容靠左对齐
            contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
            HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), clazz).registerWriteHandler(horizontalCellStyleStrategy).autoCloseStream(false).sheet(newSheetName).doWrite(data);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = MapUtils.newHashMap();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }

    }


    /**
     * 下载excel模板
     * @param response     响应对象
     * @param sourcePath   模板路径
     * @param downloadName 下载模板名称
     */
    public static void downloadExcelTemplate(HttpServletResponse response, String sourcePath, String downloadName) {
        try {
            ClassPathResource classPathResource = new ClassPathResource(sourcePath);
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(downloadName, "UTF-8") + ExcelTypeEnum.XLSX.getValue());
            InputStream inputStream = classPathResource.getInputStream();
            ServletOutputStream out = response.getOutputStream();
            IOUtils.copy(inputStream, out);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
