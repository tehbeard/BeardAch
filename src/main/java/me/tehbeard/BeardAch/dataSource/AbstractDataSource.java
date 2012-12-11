package me.tehbeard.BeardAch.dataSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.Achievement.Display;
import me.tehbeard.BeardAch.achievement.AchievementPlayerLink;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.utils.factory.ConfigurableFactory;


public abstract class AbstractDataSource implements IDataSource{

    
    public abstract HashSet<AchievementPlayerLink> getPlayersAchievements(String Player);

    public abstract void setPlayersAchievements(String player,
            String achievement);
    public abstract void flush();

    public abstract void clearAchievements(String player) ;
}
