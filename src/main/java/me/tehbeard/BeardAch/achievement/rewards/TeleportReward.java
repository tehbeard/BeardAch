package me.tehbeard.BeardAch.achievement.rewards;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorFieldType;

@Configurable(tag="teleport",name="Teleport to location")
public class TeleportReward implements IReward {

    @Expose
    @EditorField(alias="Teleport to",type=EditorFieldType.location)
    private Location to;
    public void configure(Achievement ach, String config) {
        String[] c = config.split(":");
        to = new Location(Bukkit.getWorld(c[0]),
                Double.parseDouble(c[1]),
                Double.parseDouble(c[2]),
                Double.parseDouble(c[3]),
                Float.parseFloat(c[4]),
                Float.parseFloat(c[5])
                );
        if(c.length!=6){throw new IllegalArgumentException("Teleport must be given a valid location");}
        
    }

    public void giveReward(Player player) {
        player.teleport(to);
        
    }

    public void configure(Achievement ach) {
        
    }

}
