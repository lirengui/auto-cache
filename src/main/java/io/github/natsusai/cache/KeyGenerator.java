package io.github.natsusai.cache;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

/**
 * 缓存键值生成器
 *
 * @author Kurenai
 * @since 2019-07-21 16:46
 */

public class KeyGenerator {

    private static ObjectMapper mapper;

    static {
         mapper = new ObjectMapper();
         mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static String generateCacheName(String prefix, String appName, String clazzName, String methodName) {
        return String.join(":", Arrays.asList(prefix, appName.toUpperCase(), clazzName)) + "#" + methodName;
    }

    public static String generateCacheKey(Object[] args) {
        try {
            return mapper.writeValueAsString(args);
        } catch (JsonProcessingException e) {
            return Arrays.toString(args);
        }
    }
}
