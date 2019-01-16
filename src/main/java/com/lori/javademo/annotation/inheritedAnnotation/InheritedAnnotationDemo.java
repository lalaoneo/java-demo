package com.lori.javademo.annotation.inheritedAnnotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
/**
 * @see Inherited 该注解修饰的注解在目标对象被继承时自动继承到子类对象
 */
@Inherited
public @interface InheritedAnnotationDemo {

    String value();
}
