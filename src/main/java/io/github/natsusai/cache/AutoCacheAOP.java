package io.github.natsusai.cache;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 自动缓存AOP
 *
 * @author Kurenai
 * @since 2019-07-21 17:03
 */

@Component
@Aspect
@Slf4j
public class AutoCacheAOP {

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private CacheManager cacheManager;

    private static final String CACHE_PREFIX = "AUTO_CACHE";

    @Around("@annotation(io.github.natsusai.cache.AutoCache)")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        String methodName = point.getSignature().getName();
        String clazzName = point.getSignature().getDeclaringType().getName();
        String cacheName = KeyGenerator.generateCacheName(CACHE_PREFIX, appName, clazzName, methodName);
        String cacheKey = KeyGenerator.generateCacheKey(args);
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            log.warn("Not allow in flight cache creation, can't auto create cache!");
            return point.proceed();
        }
        log.info(" cacheName: {}, cacheKey: {}.", cacheName, cacheKey);
        Object result = Optional.ofNullable(cache.get(cacheKey)).map(Cache.ValueWrapper::get).orElse(null);
        if (result == null) {
            result = point.proceed();
            cache.put(cacheKey, result);
        }
        return result;
    }
}
