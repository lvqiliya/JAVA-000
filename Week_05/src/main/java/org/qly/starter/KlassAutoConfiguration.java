package org.qly.starter;

import org.qly.spring.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@ConditionalOnClass(Klass.class)
@ConditionalOnProperty(prefix = "klass", value = "enabled", matchIfMissing = true, havingValue = "true")
@EnableConfigurationProperties(KlassProperty.class)
@PropertySource("classpath:application.properties")
public class KlassAutoConfiguration {

    @Autowired
    KlassProperty klassProperty;

    @Bean
    public Klass myKlass() {
        List<String> names = klassProperty.getStudentNames();
        List<Integer> ages = klassProperty.getStudentAges();
        Klass myKlass = new Klass(1, "初中");
        for (int i = 0; i<names.size();i++) {
            myKlass.addStudent(new Student(names.get(i), ages.get(i)));
        }
        System.out.println(names.toString());
        System.out.println(ages.toString());
        return myKlass;
    }
}
