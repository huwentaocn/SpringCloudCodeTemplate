package com.hwt.idempotent.config;

import com.hwt.idempotent.aop.IdempotentAspect;
import com.hwt.idempotent.redis.IdempotentRedisDAO;
import com.hwt.idempotent.service.IdempotentKeyResolver;
import com.hwt.idempotent.service.impl.DefaultIdempotentKeyResolver;
import com.hwt.idempotent.service.impl.ExpressionIdempotentKeyResolver;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/**
 * @Description 幂等性配置文件
 * @Date 2024/3/23 15:09 星期六
 * @Author Hu Wentao
 */
@AutoConfiguration
public class IdempotentConfiguration {


    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}
