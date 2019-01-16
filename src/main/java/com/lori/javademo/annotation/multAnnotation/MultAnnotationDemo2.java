package com.lori.javademo.annotation.multAnnotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MultAnnotationDemo2 {

    String text2() default "222";
}
