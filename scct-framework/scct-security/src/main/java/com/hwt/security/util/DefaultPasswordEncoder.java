package com.hwt.security.util;

import com.hwt.common.util.EncryptionUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 密码处理器
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        this(-1);
    }
    public DefaultPasswordEncoder(int strength) {
    }
    //进行MD5加密
    @Override
    public String encode(CharSequence charSequence) {
        return EncryptionUtil.encryptMD5(charSequence.toString());
    }
    //进行密码比对
    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        return encodedPassword.equals(EncryptionUtil.encryptMD5(charSequence.toString()));
    }
}
