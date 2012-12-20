package me.tehbeard.BeardAch.achievement.rewards;


import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.help.*;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

@Configurable(tag="subgroup")
@Usage(arguments = { @Argument(name="subgroup",desc="subgroup to add to a player")}, packageName = "base",dependencies="DroxPerms")
public class DroxSubGroupReward implements IReward{

    @Expose
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


    public void configure(Achievement ach) {
       
    }

}
