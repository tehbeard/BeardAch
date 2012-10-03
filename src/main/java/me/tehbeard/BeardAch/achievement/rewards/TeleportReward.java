package me.tehbeard.BeardAch.achievement.rewards;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.configurable.Usage;

@Configurable(tag="teleport")
@Usage(arguments={"world|World to Teleport to","x|","y|","z|","yaw|","pitch|"},packageName="base")
public class TeleportReward implements IReward {

    private Location l;
    public void configure(Achievement ach, String config) {
        String[] c = config.split(":");
        l = new Location(Bukkit.getWorld(c[0]),
                Double.parseDouble(c[1]),
                Double.parseDouble(c[2]),
                Double.parseDouble(c[3]),
                Float.parseFloat(c[4]),
                Float.parseFloat(c[5])
                );
        if(c.length!=6){throw new IllegalArgumentException("Teleport must be given a valid location");}
        
    }

    public void giveReward(Player player) {
        player.teleport(l);
        
    }

}
