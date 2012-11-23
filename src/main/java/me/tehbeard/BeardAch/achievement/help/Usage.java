package me.tehbeard.BeardAch.achievement.help;

import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
public @interface Usage {
Argument[] arguments();
String[] dependencies() default {};
String blurb() default "";
String packageName();
}
