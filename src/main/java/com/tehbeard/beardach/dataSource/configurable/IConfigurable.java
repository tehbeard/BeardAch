package com.tehbeard.beardach.datasource.configurable;

import com.tehbeard.beardach.achievement.Achievement;

/**
 * Represents a configurable item, either a trigger or reward
 * 
 * @author James
 * 
 */
public interface IConfigurable {

    public void configure(Achievement ach);
}
