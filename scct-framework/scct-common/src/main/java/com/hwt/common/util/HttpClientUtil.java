package com.hwt.common.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Map.Entry;

public class HttpClientUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
     * @param strSrc  要加密的字符串
     * @param encName 加密类型
     * @return
     */
    public static String Encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); //to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

//    // 加密
//    public static String getBase64(String str) {
//        byte[] b = null;
//        String s = null;
//        try {
//            b = str.getBytes("utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        if (b != null) {
//            s = new BASE64Encoder().encode(b);
//        }
//        return s;
//    }

//    //BASE64解密
//    public static String decryptBASE64(String key) {
//        byte[] bt;
//        try {
//            bt = (new BASE64Decoder()).decodeBuffer(key);
//            return new String(bt,"utf-8");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString().toLowerCase();
    }


    public static String sendPost(String urlParam, Map<String, Object> params, String charset) {
        StringBuffer resultBuffer = null;
        // 构建请求参数
        Boolean flag = true;
        StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Entry<String, Object> e : params.entrySet()) {
                sbParams.append(e.getKey());
                sbParams.append("=");
                sbParams.append(e.getValue());
                sbParams.append("&");
            }
        }
        HttpURLConnection con = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        // 发送请求
        try {
            URL url = new URL(urlParam);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            if (sbParams != null && sbParams.length() > 0) {
                osw = new OutputStreamWriter(con.getOutputStream(), charset);
                osw.write(sbParams.substring(0, sbParams.length() - 1));
                osw.flush();
            }
            // 读取返回内容
            resultBuffer = new StringBuffer();
            //long contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));
            //if (contentLength > 0) {
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
            String temp;
            while ((temp = br.readLine()) != null) {
                resultBuffer.append(temp);
            }
            //}
        } catch (Exception e) {
            flag = false;
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    osw = null;
                    throw new RuntimeException(e);
                } finally {
                    if (con != null) {
                        con.disconnect();
                        con = null;
                    }
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                    throw new RuntimeException(e);
                } finally {
                    if (con != null) {
                        con.disconnect();
                        con = null;
                    }
                }
            }
        }
        if (flag) {
            return resultBuffer.toString();
        }else {
            return "-1";
        }

    }

    public static String getHttpResponse(String httpurl) {
        String message="";
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                InputStream inputStream=connection.getInputStream();
                byte[] data=new byte[1024];
                StringBuffer sb1=new StringBuffer();
                int length=0;
                while ((length=inputStream.read(data))!=-1){
                    String s=new String(data, 0,length);
                    sb1.append(s);
                }
                message=sb1.toString();
                inputStream.close();
            }
            //关闭连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    // 只用于桐梓项目，测试
    public static String getHttpResponseByTongZi(String httpurl) {
        String message="";
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(2000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                InputStream inputStream=connection.getInputStream();
                byte[] data=new byte[1024];
                StringBuffer sb1=new StringBuffer();
                int length=0;
                while ((length=inputStream.read(data))!=-1){
                    String s=new String(data, 0,length);
                    sb1.append(s);
                }
                message=sb1.toString();
                inputStream.close();
            }
            //关闭连接
            connection.disconnect();
        } catch (SocketTimeoutException et){
            log.error("调用接口url ==> 超时：" + httpurl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

//   public static String setPostRequest(String url,String data) {
//        HttpClient client = new HttpClient(); // 创建一个客户端，类似打开一个浏览器
//        // 使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
//        PostMethod oneGetMethod = new PostMethod(url);
//        String result = "";
//        try {
//            HttpClientParams httparams = new HttpClientParams();
//            httparams.setSoTimeout(9000);
//            httparams.setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
//            client.setParams(httparams);
//
//            NameValuePair[] namevalue = {new NameValuePair("data", data)};
//            oneGetMethod.setRequestBody(namevalue);
//
//            int httpStatusCode = client.executeMethod(oneGetMethod);
//            if (httpStatusCode == HttpStatus.SC_OK) {
//                // result = oneGetMethod.getResponseBodyAsString();//返回的信息
//
//                InputStream resStream = oneGetMethod.getResponseBodyAsStream();
//                BufferedReader br = new BufferedReader(new InputStreamReader(
//                        resStream));
//                StringBuffer resBuffer = new StringBuffer();
//                String resTemp = "";
//                while ((resTemp = br.readLine()) != null) {
//                    resBuffer.append(resTemp);
//                }
//                result = resBuffer.toString();
//            }
//            oneGetMethod.releaseConnection();// 释放连接
//        } catch (HttpException e) {
//            log.error("http connect error：" + e.getMessage());
//        } catch (IOException e) {
//            log.error("io error：" + e.getMessage());
//        }
//        return result;
//    }
//
//
//    public static String
//    setJsonPostRequest(String url, String data) {
//        HttpClient client = new HttpClient(); // 创建一个客户端，类似打开一个浏览器
//        // 使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
//        PostMethod oneGetMethod = new PostMethod(url);
//        String result = "";
//        try {
//            HttpClientParams httparams = new HttpClientParams();
//            httparams.setSoTimeout(3000);
//            httparams.setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
//            client.setParams(httparams);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            oneGetMethod.setRequestBody(data);
//
//            int httpStatusCode = client.executeMethod(oneGetMethod);
//            if (httpStatusCode == HttpStatus.SC_OK) {
//                // result = oneGetMethod.getResponseBodyAsString();//返回的信息
//
//                InputStream resStream = oneGetMethod.getResponseBodyAsStream();
//                BufferedReader br = new BufferedReader(new InputStreamReader(
//                        resStream));
//                StringBuffer resBuffer = new StringBuffer();
//                String resTemp = "";
//                while ((resTemp = br.readLine()) != null) {
//                    resBuffer.append(resTemp);
//                }
//                result = resBuffer.toString();
//            }
//            oneGetMethod.releaseConnection();// 释放连接
//        } catch (HttpException e) {
//            log.error("http connect error：" + e.getMessage());
//        } catch (IOException e) {
//            log.error("io error：" + e.getMessage());
//        }
//        return result;
//    }




}
