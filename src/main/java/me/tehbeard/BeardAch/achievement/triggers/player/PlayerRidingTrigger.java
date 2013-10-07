package me.tehbeard.BeardAch.achievement.triggers.player;

import com.avaje.ebeaninternal.server.deploy.BeanDescriptor;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentValueDescription;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

@ComponentHelpDescription(description = "Is player riding an entity", name = "Riding entity", type = ComponentType.TRIGGER)
@Configurable(name = "Riding entity", tag = "riding")
public class PlayerRidingTrigger implements ITrigger {

    @ComponentValueDescription(description = "Entity type to check")
    @Expose
    @EditorField(alias = "Entity")
    private String type;

    @Override
    public void configure(Achievement ach, String config) {
    }

    @Override
    public void configure(Achievement ach) {
    }

    @Override
    public boolean checkAchievement(Player player) {
        for (Entity e : player.getNearbyEntities(8, 8, 8)) {
            if(!e.getType().equals(EntityType.valueOf(type))){continue;}
            Entity passenger = e.getPassenger();
            
            if (passenger != null) {
                if (passenger.equals(player)) {
                    return true;
                }
            }
        }
        return false;
    }
}
