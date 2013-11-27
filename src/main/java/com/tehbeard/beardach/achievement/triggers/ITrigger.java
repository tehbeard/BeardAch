package com.tehbeard.beardach.achievement.triggers;

import org.bukkit.entity.Player;

import com.tehbeard.beardach.datasource.configurable.IConfigurable;

public interface ITrigger extends IConfigurable {

    public boolean checkAchievement(Player player);
}
