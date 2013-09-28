package me.tehbeard.BeardAch.achievement.rewards.droxperms;


import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

@ComponentHelpDescription(description="Add a subgroup to a player",name="Add subgroup",type=ComponentType.REWARD,dependencies="DroxPerms")
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
