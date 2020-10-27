package com.qly.week_02;

import org.springframework.web.bind.annotation.GetMapping;
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
}
