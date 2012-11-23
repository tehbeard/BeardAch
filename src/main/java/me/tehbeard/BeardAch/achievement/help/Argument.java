package me.tehbeard.BeardAch.achievement.help;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Argument {
String name();
String desc();
}
