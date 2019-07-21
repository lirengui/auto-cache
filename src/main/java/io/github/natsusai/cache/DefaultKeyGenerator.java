package io.github.natsusai.cache;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

/**
 * 默认缓存键值生成器
 *
 * @author Kurenai
 * @since 2019-07-21 20:49
 */

public class DefaultKeyGenerator implements KeyGenerator {
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public String generateCacheName(String prefix, String appName, String clazzName, String methodName) {
        return String.join(StringPool.COLON, Arrays.asList(prefix, appName.toUpperCase(), clazzName)) + StringPool.HASH + methodName;
    }

    public String generateCacheKey(Object[] args) {
        try {
            return mapper.writeValueAsString(args);
        } catch (JsonProcessingException e) {
            return Arrays.toString(args);
        }
    }
}
