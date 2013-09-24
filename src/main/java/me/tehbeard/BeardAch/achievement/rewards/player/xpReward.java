package me.tehbeard.BeardAch.achievement.rewards.player;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;

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
