package com.tehbeard.beardach.achievement.rewards.player;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentType;

@ComponentHelpDescription(description = "Gives a player some xp", name = "give xp", type = ComponentType.REWARD)
@Configurable(tag="xp",name="Give xp")
public class xpReward implements IReward {

    @Expose
    @EditorField(alias="Amount to give")
    int xp;

    public void configure(Achievement arg0, String config) {
        xp = Integer.parseInt(config);
        
    }

    public void giveReward(Player player) {
        player.giveExp(xp);
    }

	public void configure(Achievement ach) {
		// TODO Auto-generated method stub
		
	}
    
}
