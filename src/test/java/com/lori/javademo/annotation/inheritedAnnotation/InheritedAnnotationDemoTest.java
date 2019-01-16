package com.lori.javademo.annotation.inheritedAnnotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.Annotation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InheritedAnnotationDemoTest {

    @Test
    public void testInheritedAnnotationDemo() throws ClassNotFoundException {
        Class clazz = Class.forName(Dog.class.getName());

        Annotation[] annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations){

            System.out.println(annotation.annotationType().getName());

            if (annotation instanceof InheritedAnnotationDemo){
                System.out.println(((InheritedAnnotationDemo) annotation).value());
            }
        }
    }
}
