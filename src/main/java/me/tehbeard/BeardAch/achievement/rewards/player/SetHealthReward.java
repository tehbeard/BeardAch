package me.tehbeard.BeardAch.achievement.rewards.player;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;

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
