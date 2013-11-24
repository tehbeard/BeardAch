package com.tehbeard.beardach.achievement.rewards.droxperms;


import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.dataSource.configurable.Configurable;
import com.tehbeard.beardach.dataSource.json.editor.EditorField;
import com.tehbeard.beardach.dataSource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.dataSource.json.help.ComponentType;

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
			BeardAch.instance().getLogger().fine("Played added to subgroup");
		}
	}


    public void configure(Achievement ach) {
       
    }

}
