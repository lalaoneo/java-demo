package com.lori.javademo.annotation.multAnnotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@MultAnnotationDemo1
@MultAnnotationDemo2
public @interface MultAnnotationDemo {

    String value();
}
