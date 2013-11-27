package com.tehbeard.beardach.dataSource;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceDescriptor {
String tag();
String version();
}