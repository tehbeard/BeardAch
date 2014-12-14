//package com.tehbeard.beardach.achievement.triggers.spatial;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//
//
//import com.google.gson.annotations.Expose;
//import com.tehbeard.beardach.BeardAch;
//import com.tehbeard.beardach.achievement.Achievement;
//import com.tehbeard.beardach.achievement.triggers.ITrigger;
//import com.tehbeard.beardach.annotations.Configurable;
//import com.tehbeard.beardach.datasource.json.editor.EditorField;
//import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
//import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
//import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;
//import com.tehbeard.utils.cuboid.Cuboid;
//import org.spongepowered.api.entity.player.Player;
//
///**
// * Checks if a players is in a cuboid for a specified amount of time
// * 
// * @author James
// * 
// */
//@ComponentDescription(description = "Player stays in an area for a certain amount of time. Timer resets when a player walks out the area",categories = "spatial")
//@Configurable(tag = "koth", name = "King of the hill")
//public class CuboidKingOfTheHillTrigger implements ITrigger {
//
//    @ComponentFieldDescription(value = "Area player must be inside of")
//    @Expose
//    @EditorField(alias = "cuboid", type = EditorFieldType.cuboid)
//    private Cuboid cuboid = new Cuboid();
//    @ComponentFieldDescription(value = "Time inside area")
//    @Expose
//    @EditorField(alias = "Time inside hill(seconds)")
//    private int time = 0;
//    private Achievement ach;
//
//    private Map<String, Long> times = new HashMap<String, Long>();
//
//    @Override
//    public boolean checkAchievement(Player player) {
//
//        long currentTime = System.currentTimeMillis() / 1000L;
//
//        if (hasTime(player.getName()))
//            return currentTime - getTime(player.getName()) >= time;
//
//        return false;
//    }
//
//    public ArrayList<String> getCache() {
//        return cuboid.getChunks();
//    }
//
//    private boolean hasTime(String player) {
//        return times.containsKey(player);
//    }
//
//    private Long getTime(String player) {
//        if (!hasTime(player)) {
//            times.put(player, System.currentTimeMillis() / 1000L);
//        }
//        return times.get(player);
//    }
//
//    @EventHandler(priority = EventPriority.HIGHEST)
//    public void onPlayerMove(PlayerMoveEvent event) {
//        if (event.getTo().getBlockX() != event.getFrom().getBlockX() || event.getTo().getBlockY() != event.getFrom().getBlockY() || event.getTo().getBlockZ() != event.getFrom().getBlockZ()) {
//            Player player = event.getPlayer();
//            if(!cuboid.getWorld().equals(event.getFrom().getWorld().getName())){
//                return;
//            }
//            
//            boolean wasInside = cuboid.isInside(BukkitUtils.fromLocation(event.getFrom()));
//            boolean isInside = cuboid.isInside(BukkitUtils.fromLocation(event.getTo()));
//            long currentTime = System.currentTimeMillis() / 1000L;
//
//            if (wasInside) {
//                l(player.getName() + " was inside cuboid");
//                if (currentTime - getTime(player.getName()) >= time) {
//                    l(player.getName() + " was inside cuboid for required amount of time");
//                    BeardAch.getAchievementManager().checkAchievement(ach);
//
//                }
//                if (!isInside) {
//                    l(player.getName() + " left cuboid, clearing time");
//                    times.remove(player.getName());
//                }
//            }
//            if (isInside && !wasInside) {
//                l(player.getName() + " entered cuboid, starting timer.");
//                getTime(player.getName());
//            }
//
//        }
//    }
//
//    private void l(String l) {
//        System.out.println(l);
//    }
//
//    @Override
//    public void configure(Achievement ach) {
//        BeardAch.getListener().add(cuboid, ach);
//    }
//
//}
