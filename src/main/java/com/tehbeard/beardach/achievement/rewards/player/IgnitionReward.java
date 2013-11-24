package com.tehbeard.beardach.achievement.rewards.player;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.dataSource.configurable.Configurable;
import com.tehbeard.beardach.dataSource.json.editor.EditorField;
import com.tehbeard.beardach.dataSource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.dataSource.json.help.ComponentType;

@ComponentHelpDescription(description="Sets a player on fire",name="ignite player",type=ComponentType.REWARD)
@Configurable(tag="ignite",name="Ignite the player")
public class IgnitionReward implements IReward {

    @Expose
    @EditorField(alias="Ignite for (ticks)")
    int ticks;

    public void configure(Achievement arg0, String config) {
        ticks = Integer.parseInt(config);
        
    }

    public void giveReward(Player player) {
        player.setFireTicks(ticks);
    }

	public void configure(Achievement ach) {
		// TODO Auto-generated method stub
		
	}
    
}
