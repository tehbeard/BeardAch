package me.tehbeard.BeardAch.achievement.triggers;

import java.util.HashMap;

/**
 * Responsible for creating triggers
 * @author James
 *
 */
public class TriggerFactory {

	static HashMap<String,Class<? extends Trigger>> triggers = new HashMap<String,Class<? extends Trigger>>();
	
	public static void addTrigger(String name,Class<? extends Trigger> trigger){
		triggers.put(name, trigger);
		
	}
	public static ITrigger getTrigger(String name,String config){
		if(triggers.containsKey(name)){
			
			try {
				return triggers.get(name).newInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public static void clearTriggers(){
		triggers.clear();
	}
}
