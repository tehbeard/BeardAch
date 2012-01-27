package me.tehbeard.BeardAch.dataSource.configurable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Configurable {
String tag();
}
