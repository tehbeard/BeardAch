package com.tehbeard.beardach.achievement.rewards.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentType;

@ComponentHelpDescription(description="Sets the player's compass target to new location",name="Set Compass target",type=ComponentType.REWARD)
@Configurable(tag="compass",name="set compass target")
public class CompassTargetReward implements IReward {

    @Expose
    @EditorField(alias="Location",type=EditorFieldType.location)
    private Location l;

    public void configure(Achievement ach, String config) {
        String[] c = config.split(":");
        if(c.length!=4){
            throw new IllegalArgumentException("invalid compass config");
        }
        l = new Location(Bukkit.getWorld(c[0]),
                Double.parseDouble(c[1]),
                Double.parseDouble(c[2]),
                Double.parseDouble(c[3])
                
                );
        
    }

    public void giveReward(Player player) {
        player.setCompassTarget(l);
        
    }

	public void configure(Achievement ach) {
		// TODO Auto-generated method stub
		
	}

}
