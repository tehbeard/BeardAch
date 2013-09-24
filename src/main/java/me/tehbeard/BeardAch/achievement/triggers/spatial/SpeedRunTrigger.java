package me.tehbeard.BeardAch.achievement.triggers.spatial;

import java.util.HashMap;
import java.util.Map;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorFieldType;
import me.tehbeard.utils.cuboid.Cuboid;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
//TODO: Redo as worldguard based
@Configurable(tag="speedrun",name="Speed run")
public class SpeedRunTrigger implements ITrigger {

    @Expose
    @EditorField(alias="start cuboid",type=EditorFieldType.cuboid)
    private Cuboid startCuboid = new Cuboid();
    @Expose
    @EditorField(alias="end cuboid",type=EditorFieldType.cuboid)
    private Cuboid endCuboid = new Cuboid();
    @Expose
    @EditorField(alias="time to beat (seconds)")
    long timing = 0L;

    Map<String,Long> states = new HashMap<String,Long> ();

    public Cuboid getStartCuboid(){
        return startCuboid;
    }

    public Cuboid getEndCuboid(){
        return endCuboid;
    }

    public void configure(Achievement ach,String config) {
        String[] c= config.split("\\/");
        if(c.length == 3){
            startCuboid.setCuboid(c[0]);
            endCuboid.setCuboid(c[1]);
            timing = Long.parseLong(c[2]);
        }else
        {
            System.out.println("speedrun invalid");
        }
    }

    public boolean checkAchievement(Player player) {
        
        //if inside start cuboid and state does not exist, create record
        if((startCuboid.isInside(player.getLocation()))){
            startTimer(player.getName());
            return false;
        }
        //if inside end cuboid, and state exists, check and return value
        if(endCuboid.isInside(player.getLocation()) &&
                inTime(player.getName())){
            clearTimer(player.getName());
            return true;
        }
        return false;
    }



    /**
     * Start timer for a player
     * @param player
     */
    private void startTimer(String player){
        states.put(player, System.currentTimeMillis());
    }

    /**
     * Clear timer for a player
     * @param player
     */
    private void clearTimer(String player){
        states.remove(player);
    }

    /**
     * Has the players time expired?
     * @param player
     * @return
     */
    private boolean inTime(String player){
        if(hasTime(player)){
        return (System.currentTimeMillis() - states.get(player))/1000L <= timing;
        }
        return false;
            
    }
    
    /**
     * Do they have a time in the system
     * @param player
     * @return
     */
    private boolean hasTime(String player){
        return states.containsKey(player);
    }

    public void configure(Achievement ach) {
        
    }


}
