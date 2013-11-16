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
