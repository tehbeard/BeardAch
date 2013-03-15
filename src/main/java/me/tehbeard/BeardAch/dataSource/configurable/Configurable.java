package me.tehbeard.BeardAch.dataSource.configurable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation used to tag a trigger/reward for inclusion into the system.
 * @author James
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Configurable {
String tag();
String name();
}
