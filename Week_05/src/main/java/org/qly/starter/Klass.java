package org.qly.starter;

import org.qly.spring.Student;

import java.util.ArrayList;
import java.util.List;

public class Klass {

    private int id;

    private String name;

    List<Student> students = new ArrayList<>();

    public Klass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public String toString() {
        return id + "-" + name + ":{" + students.toString() + "}";
    }
}
