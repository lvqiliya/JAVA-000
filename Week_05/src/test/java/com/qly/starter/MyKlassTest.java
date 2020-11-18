package com.qly.starter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.qly.starter.Klass;
import org.qly.starter.KlassAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = KlassAutoConfiguration.class)
public class MyKlassTest {

    @Autowired
    Klass klass;

    @Test
    public void test() {
        System.out.println(klass.toString());
    }
}
