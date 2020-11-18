package org.qly.spring;

import org.springframework.stereotype.Component;

@Component
public class Student {
    private String name = "lisi";
    private int age = 18;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name:" + name + "; age:" + age + ".";
    }
}
