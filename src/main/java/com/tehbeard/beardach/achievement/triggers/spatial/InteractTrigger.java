package com.tehbeard.beardach.achievement.triggers.spatial;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.google.gson.annotations.Expose;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.dataSource.configurable.Configurable;
import com.tehbeard.beardach.dataSource.json.editor.EditorField;
import com.tehbeard.beardach.dataSource.json.editor.EditorFieldType;
import com.tehbeard.beardach.dataSource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.dataSource.json.help.ComponentType;
import com.tehbeard.beardach.dataSource.json.help.ComponentValueDescription;

@Configurable(tag="interact",name="Click block at location")
@ComponentHelpDescription(description = "Player clicks a block", name = "Click block", type = ComponentType.TRIGGER)
public class InteractTrigger implements ITrigger, Listener {

    @ComponentValueDescription(description="Block location to be clicked")
    @Expose
    @EditorField(alias="Block to interact",type=EditorFieldType.location)
    private Location l;
    
    private Achievement ach;
    private Set<String> active = new HashSet<String>();
    public void configure(Achievement ach, String config) {
        this.ach = ach;
        String[] c = config.split(":");
        if(c.length!=4){
            throw new IllegalArgumentException("invalid interact config");
        }
        l = new Location(Bukkit.getWorld(c[0]),
                Double.parseDouble(c[1]),
                Double.parseDouble(c[2]),
                Double.parseDouble(c[3])
                
                );

    }

    public boolean checkAchievement(Player player) {

        return active.contains(player.getName());
    }
    
    @EventHandler
    public void interactEvent(PlayerInteractEvent event){
        Block b = event.getClickedBlock();
        if(b!=null){
            if(b.getLocation().equals(l)){
                active.add(event.getPlayer().getName());
                ach.checkAchievement(event.getPlayer());
                active.remove(event.getPlayer().getName());
            }
        }
    }

    public void configure(Achievement ach) {
        this.ach = ach;
    }

}
