package me.tehbeard.BeardAch.achievement.rewards;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.help.*;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

@Configurable(tag="teleport")
@Usage(arguments={
        @Argument(name="world",desc=""),
        @Argument(name="x",desc=""),
        @Argument(name="y",desc=""),
        @Argument(name="z",desc=""),
        @Argument(name="yaw",desc=""),
        @Argument(name="pitch",desc=""),
},packageName="base")
public class TeleportReward implements IReward {

    @Expose
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
