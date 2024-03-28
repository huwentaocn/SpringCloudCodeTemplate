package com.hwt.base;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaseApplicationTests {

    @Value("${test}")
    public String test;

    @Test
    void contextLoads() {
        System.out.println(test);
    }

}
