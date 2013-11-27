package com.tehbeard.beardach.achievement.triggers.spatial;

import java.util.HashMap;
import java.util.Map;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;

import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;
import com.tehbeard.utils.cuboid.Cuboid;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
//TODO: Redo as worldguard based
@ComponentHelpDescription(description = "Triggers if player gets between two points within the given time")
@Configurable(tag="speedrun",name="Speed run")
public class SpeedRunTrigger implements ITrigger {

    @ComponentValueDescription(description="Area player enters to start cuboid. NOTE: Entry into this area starts the timer, so for race areas with gates, place the cuboid immediately after the starting gate")
    @Expose
    @EditorField(alias="start cuboid",type=EditorFieldType.cuboid)
    private Cuboid startCuboid = new Cuboid();
    
    @ComponentValueDescription(description="Area player must enter to finish the race")
    @Expose
    @EditorField(alias="end cuboid",type=EditorFieldType.cuboid)
    private Cuboid endCuboid = new Cuboid();
    
    @ComponentValueDescription(description="Time player must beat in order to trigger this achievement")
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
