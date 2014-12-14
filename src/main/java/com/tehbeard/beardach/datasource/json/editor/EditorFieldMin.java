package com.tehbeard.beardach.datasource.json.editor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EditorFieldMin {
    int value() default Integer.MIN_VALUE;
}
