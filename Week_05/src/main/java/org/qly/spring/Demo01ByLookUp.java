package org.qly.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class Demo01ByLookUp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student1 = (Student) context.getBean("student");
        System.out.println(student1);

        Student student2 = context.getBean(Student.class);
        System.out.println(student2);

        Map<String, Student> studentMap = context.getBeansOfType(Student.class);
        System.out.println(studentMap);
    }
}
