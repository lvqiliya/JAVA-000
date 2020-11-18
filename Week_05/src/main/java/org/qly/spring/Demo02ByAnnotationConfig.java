package org.qly.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Demo02ByAnnotationConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Demo02ByAnnotationConfig.class);
        context.refresh();
        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());
    }
}
