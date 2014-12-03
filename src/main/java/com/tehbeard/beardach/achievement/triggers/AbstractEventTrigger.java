package com.tehbeard.beardach.achievement.triggers;

import java.util.HashSet;
import java.util.Set;

import org.spongepowered.api.entity.player.Player;
import org.bukkit.event.Listener;

import com.tehbeard.beardach.achievement.Achievement;

/**
 * Abstract trigger for listener based events, includes an active list. Typical
 * pattern is <code>void event(event){
 * if(satisfied config){
 * add(player)
 * getAchievement().check(player)
 * remove(player)
 * }
 * }</code>
 * 
 * @author James
 * 
 */
public abstract class AbstractEventTrigger implements ITrigger, Listener {

    private Set<String> active = new HashSet<String>();
    private Achievement achievement;

    @Override
    public void configure(Achievement ach) {
        achievement = ach;
    }


    protected void runCheck(Player player){
        if(!achievement.has(player)){
            add(player);
            achievement.checkAchievement(player);
            remove(player);
        }
    }

    private void add(Player p) {
        active.add(p.getName());
    }

    private void remove(Player p) {
        active.remove(p.getName());
    }

    @Override
    public boolean checkAchievement(Player player) {
        return active.contains(player.getName());
    }

}
