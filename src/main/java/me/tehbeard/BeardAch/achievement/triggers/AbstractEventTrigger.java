package me.tehbeard.BeardAch.achievement.triggers;

import java.util.HashSet;
import java.util.Set;

import me.tehbeard.BeardAch.achievement.Achievement;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * Abstract trigger for listener based events, includes an active list.
 * Typical pattern is 
 * <code>void event(event){
 * if(satisfied config){
 * add(player)
 * getAchievement().check(player)
 * remove(player)
 * }
 * }</code>
 * @author James
 *
 */
public abstract class AbstractEventTrigger implements ITrigger,Listener{
	
	private Set<String> active = new HashSet<String>();
	private Achievement achievement;

	@Override
	public void configure(Achievement ach, String config) {
	}
	
	@Override
	public void configure(Achievement ach) {
		achievement = ach;
	}
	
	protected Achievement getAchievement(){
		return achievement;
	}

	protected void add(Player p){
		active.add(p.getName());
	}
	
	protected void remove(Player p){
		active.remove(p.getName());
	}
	

	@Override
	public boolean checkAchievement(Player player) {
		// TODO Auto-generated method stub
		return active.contains(player.getName());
	}

}
