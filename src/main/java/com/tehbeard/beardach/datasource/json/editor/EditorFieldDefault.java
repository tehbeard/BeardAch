package com.tehbeard.beardach.datasource.json.editor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EditorFieldDefault {
    String[] value() default {};
}
