package me.tehbeard.BeardAch.dataSource.configurable;

import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
public @interface Usage {
String[] arguments();
String[] dependencies() default {};
String blurb() default "";
}
