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

@ComponentHelpDescription(description="Track promotion player",name="promote along track",type=ComponentType.REWARD,dependencies="DroxPerms")
@Configurable(tag="promote",name="(DroxPerms) Promote along track")
public class DroxTrackReward implements IReward{
    @Expose
    @EditorField(alias="Track to promote along")
	String track = "";
	public void configure(Achievement Ach,String config) {
		
		track = config;
	
	}

	
	public void giveReward(Player player) {
		if(BeardAch.droxAPI!=null){
			BeardAch.droxAPI.promotePlayer(player.getName(), track);
			BeardAch.instance().getLogger().fine("Played Promoted");
		}
	}


    public void configure(Achievement ach) {
        
    }

}
