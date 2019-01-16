package com.lori.javademo.annotation.multAnnotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.Annotation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultAnnotationTest {

    /**
     * 获取组合注解测试
     *
     * 第一步：获取组合的注解MultAnnotationDemo
     *
     * 第二步：获取注解的注解, 获取MultAnnotationDemo的注解
     *
     * @author lori.li
     */
    @Test
    public void testMultAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName(MultAnnotationClass.class.getName());

        Annotation[] annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations){
            /**
             * get value from MultAnnotationDemo
             * @see  MultAnnotationDemo
             */
            if (annotation instanceof MultAnnotationDemo){
                System.out.println(((MultAnnotationDemo) annotation).value());
            }

            System.out.println("===================获取第一个注解");

            /**
             * get annotation from MultAnnotationDemo
             */
            Class annotationType = annotation.annotationType();

            Annotation[] typeAnnotations = annotationType.getAnnotations();

            for (Annotation anno : typeAnnotations){
                System.out.println(anno.annotationType().getName());

                if (anno instanceof MultAnnotationDemo1){
                    System.out.println(((MultAnnotationDemo1) anno).text1());
                }

                if (anno instanceof MultAnnotationDemo2){
                    System.out.println(((MultAnnotationDemo2) anno).text2());
                }
            }
        }
    }
}
