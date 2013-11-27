package com.tehbeard.beardach.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceDescriptor {
String tag();
String version();
}