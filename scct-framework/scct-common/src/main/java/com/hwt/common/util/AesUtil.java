package com.hwt.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;


/**
 * @author : wx
 * date : 2023-08-12 16:14
 * description :  AES 加密解密
 */
@Slf4j
public class AesUtil {

    private static final String KEY = "a-h-w-x-is-great";

    private static final String CIPHER_ALGORITHM_CBC = "AES/ECB/PKCS5Padding";
    /**
     * 加密
     * @param content 加密的字符串
     * @param encryptKey key值
     * @return 字符串
     */
    private static String encrypt(String content, String encryptKey) {
        KeyGenerator kgen = null;
        try {
            kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
            byte[] b = cipher.doFinal(content.getBytes("utf-8"));
            // 采用base64算法进行转码,避免出现中文乱码
            return Base64.encodeBase64String(b);
        } catch (Exception e) {
            throw new RuntimeException("AES 加密异常！",e);
        }
    }

    /**
     * 解密
     * @param encryptStr 解密的字符串
     * @param decryptKey 解密的key值
     * @return 字符串
     */
    private static String decrypt(String encryptStr, String decryptKey) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
            // 采用base64算法进行转码,避免出现中文乱码
            byte[] encryptBytes = Base64.decodeBase64(encryptStr);
            byte[] decryptBytes = cipher.doFinal(encryptBytes);
            return new String(decryptBytes);
        }catch (Exception e){
            throw new RuntimeException("AES 解密异常！",e);
        }
    }

    public static String encrypt(String content) {
        return encrypt(content, KEY);
    }
    public static String decrypt(String encryptStr) {
        return decrypt(encryptStr, KEY);
    }
}
