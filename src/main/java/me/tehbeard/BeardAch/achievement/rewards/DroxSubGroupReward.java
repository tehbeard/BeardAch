package me.tehbeard.BeardAch.achievement.rewards;


import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.configurable.Usage;

import org.bukkit.entity.Player;

@Configurable(tag="subgroup")
@Usage(arguments = { "subgroup|subgroup to set player to" }, packageName = "base",dependencies="DroxPerms")
public class DroxSubGroupReward implements IReward{

	String subgroup = "";
	public void configure(Achievement Ach,String config) {
		subgroup = config;
	}


	public void giveReward(Player player) {
		if(BeardAch.droxAPI!=null){
			BeardAch.droxAPI.addPlayerSubgroup(player.getName(), subgroup);
			BeardAch.printDebugCon("Played added to subgroup");
		}
	}

}
