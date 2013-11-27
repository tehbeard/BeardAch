package com.tehbeard.beardach.datasource.json.help;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value=ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ComponentHelpDescription {
ComponentType type();
String name();
String description();
String[] dependencies() default {}; 
}
