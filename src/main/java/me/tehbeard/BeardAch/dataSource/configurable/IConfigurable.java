package me.tehbeard.BeardAch.dataSource.configurable;

import me.tehbeard.BeardAch.achievement.Achievement;

/**
 * Represents a configurable item, either a trigger or reward
 * @author James
 *
 */
public interface IConfigurable {
    /**
     * This method will be removed in 0.6, to finialize the transition from yaml to json
     * @param ach
     * @param config
     */
    @Deprecated
	public void configure(Achievement ach,String config);
	
	public void configure(Achievement ach);
}
