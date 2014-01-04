package com.tehbeard.beardach.datasource.configurable;

/**
 * Config for a runnable object
 * 
 * @author James
 * 
 */
public @interface RunnableTime {
    long value();

    SyncType type() default SyncType.SYNC;

    public enum SyncType {
        SYNC, ASYNC
    }
}
