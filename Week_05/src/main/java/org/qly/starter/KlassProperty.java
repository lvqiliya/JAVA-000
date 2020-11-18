package org.qly.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "klass")
public class KlassProperty {

    private List<String> studentNames;

    private List<Integer> studentAges;

    public List<String> getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(List<String> studentNames) {
        this.studentNames = studentNames;
    }

    public List<Integer> getStudentAges() {
        return studentAges;
    }

    public void setStudentAges(List<Integer> studentAges) {
        this.studentAges = studentAges;
    }
}
