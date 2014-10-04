package com.tehbeard.beardach.datasource.configurable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Config for a runnable object
 * 
 * @author James
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RunnableTime {
    long value();

    SyncType type() default SyncType.SYNC;

    public enum SyncType {
        SYNC, ASYNC
    }
}
