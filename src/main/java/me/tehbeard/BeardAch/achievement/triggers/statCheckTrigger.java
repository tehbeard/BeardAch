package me.tehbeard.BeardAch.achievement.triggers;

import me.tehbeard.BeardStat.containers.PlayerStatManager;

import org.bukkit.entity.Player;

/**
 * Checks if a players stat is => a certain threshold, then triggers. 
 * @author James
 *
 */
public class StatCheckTrigger extends Trigger {

	String cat;
	String stat;
	int threshold;

	public static ITrigger getInstance(String config) {
		StatCheckTrigger n =new StatCheckTrigger();
		String[] opt = config.split("\\:");
		if(opt.length==3){
			n.cat = opt[0];
			n.stat = opt[1];
			n.threshold = Integer.parseInt(opt[2]);
		}
		return n;
	}

	@Override
	public boolean checkAchievement(Player player) {
		//if player has stat
		if(PlayerStatManager.findPlayerBlob(player.getName()).hasStat(cat, stat)){
			//if player exceeds threshold
			if(PlayerStatManager.findPlayerBlob(player.getName()).hasStat(cat, stat)){
				return (PlayerStatManager.findPlayerBlob(player.getName()).getStat(cat, stat).getValue()>=threshold);
			}
		}
		return false;
	}

}
