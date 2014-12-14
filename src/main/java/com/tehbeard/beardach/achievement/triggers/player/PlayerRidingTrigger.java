//package com.tehbeard.beardach.achievement.triggers.player;
//
//import org.bukkit.entity.Entity;
//import org.bukkit.entity.EntityType;
//import org.spongepowered.api.entity.player.Player;
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
//@ComponentDescription(description = "Is player riding an entity",categories ="player")
//@Configurable(name = "Riding entity", tag = "riding")
//public class PlayerRidingTrigger implements ITrigger {
//
//    @ComponentFieldDescription(value = "Entity type to check")
//    @Expose
//    @EditorField(alias = "Entity", type = EditorFieldType.selection, options = "org.bukkit.entity.EntityType")
//    private EntityType entityType;
//
//    @Override
//    public void configure(Achievement ach) {
//    }
//
//    @Override
//    public boolean checkAchievement(Player player) {
//        for (Entity e : player.getNearbyEntities(8, 8, 8)) {
//            if (!e.getType().equals(entityType)) {
//                continue;
//            }
//            Entity passenger = e.getPassenger();
//
//            if (passenger != null) {
//                if (passenger.equals(player))
//                    return true;
//            }
//        }
//        return false;
//    }
//}
