package me.tehbeard.BeardAch.achievement.rewards;


import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

@Configurable(tag="comm",name="Execute console command")
public class CommandReward implements IReward{

    @Expose
    @EditorField(alias="Command")
	String command = "";
	public void configure(Achievement Ach,String config) {
		command = config;
		
	}

	public void giveReward(Player player) {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command.replace("<PLAYER>", player.getName()));
	}

    public void configure(Achievement ach) {
        
    }

}
