package com.hwt.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonUtils {

    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);


    public static String createFileByUrl(String url, String suffix) {
        byte[] byteFile = getImageFromNetByUrl(url);
        if (byteFile != null) {
            String patternUrl = getFileFromBytes(byteFile, suffix);
            return patternUrl;
        } else {
            return null;
        }
    }

    // 创建临时文件
    private static String getFileFromBytes(byte[] b, String suffix) {
        BufferedOutputStream stream = null;
        File file = null;
        String patternUrl = "";
        try {
            file = File.createTempFile("pattern", "." + suffix);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
            patternUrl = file.getCanonicalPath();
        } catch (Exception e) {
            logger.error("getFileFromBytes错误" + e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return patternUrl;
    }

    private static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            String str = URLEncoder.encode(strUrl, "utf-8");
            str = str.replace("%2F", "/");
            str = str.replace("%3A", ":");
            URL url = new URL(str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            logger.error("getImageFromNetByUrl错误:" + e);
        }
        return null;
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 判断是否不是空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if ((str != null) && !"".equals(str.trim()) && !"null".equals(str)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * @return java.lang.String
     * @Author wjx
     * @Description ip
     * Date 2019/4/3 10:17
     * @Param []
     **/
    public static String getIp() {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr.getHostAddress();
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress.getHostAddress();
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            return jdkSuppliedAddress.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMapValues(Map<?, ?> map) {
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            buffer.append("key= " + entry.getKey() + " and value= " + entry.getValue() + "---");
        }
        return buffer.toString();
    }


    public static String getFilecharset(File sourceFile) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                return charset; //文件编码为 ANSI
            } else if (first3Bytes[0] == (byte) 0xFF
                    && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE"; //文件编码为 Unicode
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE
                    && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE"; //文件编码为 Unicode big endian
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF
                    && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8"; //文件编码为 UTF-8
                checked = true;
            }
            bis.reset();
            if (!checked) {
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
                            // (0x80
                            // - 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charset;
    }

    public static String getEncoding(String str) {
        String encode;
        encode = "UTF-16";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "ASCII";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                //字符串<< " + str + " >>中仅由数字和英文字母组成，无法识别其编码格式
                return "UTF-8";
            }
        } catch (Exception ex) {
        }

        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        return "未识别编码格式";
    }



    /**
     *      * 判断Object对象为空或空字符串
     *      * @param obj
     *      * @return
     *     
     */
    public static Boolean isObjectEmpty(Object obj) {
        String str = ObjectUtils.toString(obj, "");
        return StringUtils.isBlank(str);
    }

    public static int AddMins(int minate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minate);
        Date goodDate = calendar.getTime();
        return Integer.valueOf(sdf.format(goodDate));
    }

    public static String upperStart(String name) {
        char firstChar = name.charAt(0);
        if ((firstChar >= 'a') && (firstChar <= 'z')) {
            char[] arr = name.toCharArray();
            int tmp25_24 = 0;
            char[] tmp25_23 = arr;
            tmp25_23[tmp25_24] = (char) (tmp25_23[tmp25_24] - ' ');
            return new String(arr);
        }
        return name;
    }

    public static boolean isEmpty(Object str) {
        if ((str == null) || "".equals(str.toString().trim())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(Object str) {
        if ((str == null) || "".equals(str.toString().trim())) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isPureDigital(String string) {
        if (StringUtils.isBlank(string))
            return false;
        String regEx1 = "\\d+";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }

    public static String getJsonValue(JSONObject mchnt_info, String partnerNum) {
        String value = "";
        if (mchnt_info != null) {
            value = mchnt_info.get(partnerNum) == null ? "" : mchnt_info.get(partnerNum).toString();
        }
        return value;
    }

    /**
     * 判断一个obj 是否是数组
     * @param obj
     * @return
     */
    public static boolean isArray(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj.getClass().isArray();
    }

    /**
     *  校验传参是否正确（字符串）
     *  map{"param":"str"}
     * @param map 实体
     * @param param 需要的参数
     * @return
     */
    public static boolean checkMap(Map map,String param) {
        if(map==null||!map.containsKey(param)|| CommonUtils.isEmpty(map.get(param))){
            return true;
        }
        return false;
    }

    /**
     *  校验传参是否正确（字符串）
     *  map{"param":"str"}
     * @param map 实体
     * @param param 需要的参数
     * @return
     */
    public static boolean checkMapKey(Map map,String param) {
        if(map==null||!map.containsKey(param)){
            return true;
        }
        return false;
    }


    /**
     *校验传参是否正确（数组）
     *  map{"param":["1","2"]}
     * @param map 实体
     * @param param 需要的参数
     * @return
     */
    public static boolean checkMapList(Map map,String param) {
        if(!map.containsKey(param)||isEmpty(map.get(param))||isArray(map.get(param))||List.class.cast(map.get(param)).size()==0){
            return true;
        }
        return false;
    }

    /**
     * JAVA实现对包含数字、字母、中文的字符串按首字母排序
     * 对包含数字、字母、中文的字符串按首字母排序。
     * 看似简单，但是实现起来却很难。
     * 因为这里面包含了中文，
     * 不能单纯的取首字母的ASSIC值进行比较。
     * 考虑到最终是根据英文字母进行比较的，
     * 索性将字符串统统转化成字符串进行处理。具体的代码
     * 汉字转为拼音
     *
     * @param chinese
     * @return
     */
//    public static String ToPinyin(String chinese) {
//        String pinyinStr = "";
//        char[] newChar = chinese.toCharArray();
//        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//        for (int i = 0; i < newChar.length; i++) {
//            if (newChar[i] > 128) {
//                try {
//                    String[] strArray = PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat);
//                    if(strArray != null && strArray.length > 0) {
//                        pinyinStr += strArray[0];
//                    }
//                } catch (BadHanyuPinyinOutputFormatCombination e) {
//                    e.printStackTrace();
//                }
//            } else {
//                pinyinStr += newChar[i];
//            }
//        }
//        return pinyinStr;
//    }
//
//    /**
//     * 汉字转为拼音并获取大写的首字母;
//     *
//     * @param chinese
//     * @return
//     */
//    public static String ToPinyinAndGetFirstChar(String chinese) {
//        String pinyinStr = "";
//        char[] newChar = chinese.toCharArray();
//        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//        for (int i = 0; i < newChar.length; i++) {
//            if (newChar[i] > 128) {
//                try {
//                    String[] strArray = PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat);
//                    if(strArray != null && strArray.length > 0) {
//                        pinyinStr += strArray[0];
//                    }
//                } catch (BadHanyuPinyinOutputFormatCombination e) {
//                    e.printStackTrace();
//                }
//            } else {
//                pinyinStr += newChar[i];
//            }
//        }
//        return pinyinStr.toUpperCase();
//    }
//
//    public static boolean isInteger(String str) {
//        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
//        return pattern.matcher(str).matches();
//    }

}
