package me.tehbeard.BeardAch.dataSource.configurable;

import me.tehbeard.BeardAch.achievement.Achievement;

public interface IConfigurable {
    @Deprecated
	public void configure(Achievement ach,String config);
	
	public void configure(Achievement ach);
}
