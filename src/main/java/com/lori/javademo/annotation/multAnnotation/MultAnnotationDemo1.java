package com.lori.javademo.annotation.multAnnotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MultAnnotationDemo1 {

    String text1() default "111";
}
