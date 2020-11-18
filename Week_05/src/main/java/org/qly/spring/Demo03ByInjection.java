package org.qly.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo03ByInjection {

    @Autowired
    private Student student;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Demo03ByInjection.class);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        String xmlPath = "applicationContext.xml";
        reader.loadBeanDefinitions(xmlPath);
        context.refresh();
        Demo03ByInjection demo = context.getBean(Demo03ByInjection.class);
        Student s = demo.student;
        System.out.println(s);
    }
}
