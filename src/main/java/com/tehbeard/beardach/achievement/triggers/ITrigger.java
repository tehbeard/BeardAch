package com.tehbeard.beardach.achievement.triggers;

import com.tehbeard.beardach.datasource.configurable.IConfigurable;
import org.spongepowered.api.entity.Player;

public interface ITrigger extends IConfigurable {

    public boolean checkAchievement(Player player);
}
