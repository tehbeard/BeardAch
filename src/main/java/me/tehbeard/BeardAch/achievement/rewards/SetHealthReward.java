package me.tehbeard.BeardAch.achievement.rewards;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;

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
