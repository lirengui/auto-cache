package io.github.natsusai.cache;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author Kurenai
 * @since 2019-07-21 4:52
 */

@Service
@Slf4j
public class CacheService {

    @AutoCache
    public String test(String id) {
        log.info("find by service.");
        return Instant.now().toString();
    }
}
