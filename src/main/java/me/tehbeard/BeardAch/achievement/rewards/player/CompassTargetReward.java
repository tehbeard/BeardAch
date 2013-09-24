package me.tehbeard.BeardAch.achievement.rewards.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorFieldType;

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
