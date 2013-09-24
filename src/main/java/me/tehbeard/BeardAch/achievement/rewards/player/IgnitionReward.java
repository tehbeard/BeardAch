package me.tehbeard.BeardAch.achievement.rewards.player;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;

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
