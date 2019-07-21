package io.github.natsusai.cache;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存自动配置
 *
 * @author Kurenai
 * @since 2019-07-21 20:57
 */

@Configuration
@AutoConfigureAfter(CacheAutoConfiguration.class)
public class CacheAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public KeyGenerator keyGenerator() {
        return new DefaultKeyGenerator();
    }


}
