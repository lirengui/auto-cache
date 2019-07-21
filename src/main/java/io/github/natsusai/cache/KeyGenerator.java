package io.github.natsusai.cache;

/**
 * 缓存键值生成器接口
 *
 * @author Kurenai
 * @since 2019-07-21 16:46
 */

public interface KeyGenerator {

    String generateCacheName(String prefix, String appName, String clazzName, String methodName);

    String generateCacheKey(Object[] args);
}
