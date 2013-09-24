package me.tehbeard.BeardAch.achievement.rewards.droxperms;


import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

@Configurable(tag="subgroup",name="(DroxPerms) add subgroup")
public class DroxSubGroupReward implements IReward{

    @Expose
    @EditorField(alias="Subgroup to add")
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
