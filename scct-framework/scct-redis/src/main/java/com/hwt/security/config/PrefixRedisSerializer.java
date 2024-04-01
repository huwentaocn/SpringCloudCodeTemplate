//package com.hwt.redis.config;
//
//import com.wx.tenant.context.TenantContextHolder;
//import org.apache.commons.lang3.StringUtils;
//import org.junit.platform.commons.util.StringUtils;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Order
//@Configuration
//public class PrefixRedisSerializer extends StringRedisSerializer {
//    /**
//     * 演示这里就写死了 可以抽取到配置文件中
//     */
//    public static final String DEFUAL_PREFIX = "tenantId:";
//
//    /**
//     * 序列化
//     *
//     * @param s key
//     * @return 结果
//     */
//    @Override
//    public byte[] serialize(String s) {
//        if (s == null) {
//            return new byte[0];
//        }
//        // 这里加上你需要加上的key前缀
//        String realKey = DEFUAL_PREFIX + TenantContextHolder.getRequiredTenantId() + ":" + s + ":";
//        return super.serialize(realKey);
//    }
//
//    /**
//     * 反序列化
//     *
//     * @param bytes 数据
//     * @return 结果
//     */
//    @Override
//    public String deserialize(byte[] bytes) {
//        String s = bytes == null ? null : new String(bytes);
//        if (StringUtils.isBlank(s)) {
//            return s;
//        }
//        int index = s.indexOf(DEFUAL_PREFIX);
//        if (index != -1) {
//            return s.substring(index + 2);
//        }
//        return s;
//    }
//}
