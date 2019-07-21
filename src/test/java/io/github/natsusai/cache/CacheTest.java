package io.github.natsusai.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.Target;

/**
 * @author Kurenai
 * @since 2019-07-21 18:30
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTest {
    @Autowired
    private CacheService service;

    @Test
    public void test() {
        service.test("1");
        service.test("2");
        service.test("1");
        service.test("2");
    }
}
