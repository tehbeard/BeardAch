package me.tehbeard.BeardAch.achievement.triggers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.utils.cuboid.Cuboid;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Checks if a players is in a cuboid for a specified amount of time
 * @author James
 *
 */
@Configurable(tag="koth")
public class CuboidKingOfTheHillTrigger implements ITrigger, Runnable {


    private Cuboid c = new Cuboid();
    private int time = 0;
    private Achievement ach;

    private Map<String,Long> times = new HashMap<String, Long>();
    
    
    public Cuboid getCuboid(){
        return c;
    }

    public void configure(Achievement ach,String config) {
        this.ach = ach;
        String[] con= config.split("\\/");
        if(con.length == 2){
            c.setCuboid(con[0]);
            time = Integer.parseInt(con[1]);
        }
    }

    public boolean checkAchievement(Player player) {

        boolean wasInside = wasInside(player.getName());
        boolean isInside  = c.isInside(player.getLocation());
        long currentTime = System.currentTimeMillis() / 1000L;
        
        if(wasInside){
            if((currentTime-getTime(player.getName()))>=time){
                times.remove(player.getName());
                return true;
            }
            if(!isInside){
                times.remove(player.getName());
            }
        }
        if(isInside && !wasInside){
            getTime(player.getName());
        }
        
        return false;
    }

    public ArrayList<String> getCache(){
        return c.getChunks();
    }
    
    private boolean wasInside(String player){
        return times.containsKey(player);
    }
    
    private Long getTime(String player){
        if(!wasInside(player)){
            times.put(player, System.currentTimeMillis()/1000L);
        }
        return times.get(player);
    }

    public void run() {

            BeardAch.self.getAchievementManager().checkAchievement(ach);
        
    }



}
