package com.tehbeard.beardach.achievement.rewards.player;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentType;

@ComponentHelpDescription(description = "Sets the players health to certain value", name = "set player health", type = ComponentType.REWARD)
@Configurable(tag="sethealth",name="Heal player")
public class SetHealthReward implements IReward {

    @Expose
    @EditorField(alias="Health to add")
    int health;

    public void configure(Achievement arg0, String config) {
        health = Integer.parseInt(config);
        
    }

    public void giveReward(Player player) {
        player.setHealth(health);
    }

	public void configure(Achievement ach) {
		// TODO Auto-generated method stub
		
	}
    
}
