package com.qly.week_02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qly
 */
@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String doGet() {
        return "hello doGet";
    }

    @PostMapping("/post")
    public String doPost(String name, String age) {
        if ("zhangsan".equals(name) && "24".equals(age)) {
            return "hello doPost";
        }
        return "oh no";
    }
}
