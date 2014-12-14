//package com.tehbeard.beardach.achievement.triggers.spatial;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.bukkit.Location;
//import org.bukkit.block.Block;
//import org.spongepowered.api.entity.player.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.player.PlayerInteractEvent;
//
//import com.google.gson.annotations.Expose;
//import com.tehbeard.beardach.achievement.Achievement;
//import com.tehbeard.beardach.achievement.triggers.ITrigger;
//import com.tehbeard.beardach.annotations.Configurable;
//import com.tehbeard.beardach.datasource.json.editor.EditorField;
//import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
//import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
//import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;
//
//@Configurable(tag = "interact", name = "Click block at location")
//@ComponentDescription(description = "Player clicks a block",categories = "spatial")
//public class InteractTrigger implements ITrigger, Listener {
//
//    @ComponentFieldDescription(value = "Block location to be clicked")
//    @Expose
//    @EditorField(alias = "Block to interact", type = EditorFieldType.location)
//    private Location l;
//
//    private Achievement ach;
//    private Set<String> active = new HashSet<String>();
//
//    @Override
//    public boolean checkAchievement(Player player) {
//
//        return active.contains(player.getName());
//    }
//
//    @EventHandler
//    public void interactEvent(PlayerInteractEvent event) {
//        Block b = event.getClickedBlock();
//        if (b != null) {
//            if (b.getLocation().equals(l)) {
//                active.add(event.getPlayer().getName());
//                ach.checkAchievement(event.getPlayer());
//                active.remove(event.getPlayer().getName());
//            }
//        }
//    }
//
//    @Override
//    public void configure(Achievement ach) {
//        this.ach = ach;
//    }
//
//}
