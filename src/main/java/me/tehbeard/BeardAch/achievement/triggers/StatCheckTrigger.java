package me.tehbeard.BeardAch.achievement.triggers;
import me.tehbeard.BeardStat.containers.PlayerStatManager;
import org.bukkit.entity.Player;

/**
 * Checks if a players stat is between certain threshold, then triggers. 
 * @author James
 *
 */
public class StatCheckTrigger extends Trigger {

	String cat;
	String stat;
	int lowerThreshold;
	int upperThreshold;

	public static ITrigger getInstance(String config) {
		StatCheckTrigger n =new StatCheckTrigger();
		String[] opt = config.split("\\:");
		if(opt.length==4){
			n.cat = opt[0];
			n.stat = opt[1];
			n.lowerThreshold = Integer.parseInt(opt[2]);
			n.upperThreshold = Integer.parseInt(opt[3]);
		}
		return n;
	}

	@Override
	public boolean checkAchievement(Player player) {
		//if player has stat
		if(PlayerStatManager.findPlayerBlob(player.getName()).hasStat(cat, stat)){
			//if player exceeds threshold
			if(PlayerStatManager.findPlayerBlob(player.getName()).hasStat(cat, stat)){
				return (
						(PlayerStatManager.findPlayerBlob(player.getName()).getStat(cat, stat).getValue()>=lowerThreshold) 
						&&
						(PlayerStatManager.findPlayerBlob(player.getName()).getStat(cat, stat).getValue()<=upperThreshold)
						);
			}
		}
		return false;
	}
}
