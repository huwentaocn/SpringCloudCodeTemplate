package com.wx.idempotent.aop;

import com.hwt.common.exception.GlobalException;
import com.hwt.common.result.ResultCodeEnum;
import com.hwt.common.util.CollectionUtils;
import com.wx.idempotent.annotation.Idempotent;
import com.wx.idempotent.redis.IdempotentRedisDAO;
import com.wx.idempotent.service.IdempotentKeyResolver;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * @Description 幂等性切面
 * 拦截声明了 {@link Idempotent} 注解的方法，实现幂等操作
 * @Date 2024/3/23 15:10 星期六
 * @Author Hu Wentao
 */

@Aspect
@Slf4j
public class IdempotentAspect {


    /**
     * IdempotentKeyResolver 集合
     */
    private final Map<Class<? extends IdempotentKeyResolver>, IdempotentKeyResolver> keyResolvers;

    private final IdempotentRedisDAO idempotentRedisDAO;

    public IdempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        this.keyResolvers = CollectionUtils.convertMap(keyResolvers, IdempotentKeyResolver::getClass);
        this.idempotentRedisDAO = idempotentRedisDAO;
    }

    @Before("@annotation(idempotent)")
    public void beforePointCut(JoinPoint joinPoint, Idempotent idempotent) {
        // 获得 IdempotentKeyResolver
        IdempotentKeyResolver keyResolver = keyResolvers.get(idempotent.keyResolver());
        Assert.notNull(keyResolver, "找不到对应的 IdempotentKeyResolver");
        // 解析 Key
        String key = keyResolver.resolver(joinPoint, idempotent);

        // 锁定 Key。
        boolean success = idempotentRedisDAO.setIfAbsent(key, idempotent.timeout(), idempotent.timeUnit());
        // 锁定失败，抛出异常
        if (!success) {
            log.info("[beforePointCut][方法({}) 参数({}) 存在重复请求]", joinPoint.getSignature().toString(), joinPoint.getArgs());
            throw new GlobalException(ResultCodeEnum.REPEATED_REQUESTS_FAIL, idempotent.message());
        }
    }

}
