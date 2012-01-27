package me.tehbeard.BeardAch.achievement.triggers;
import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardStat.containers.PlayerStatManager;
import org.bukkit.entity.Player;

/**
 * Checks if a players stat is above certain threshold, then triggers. 
 * @author James
 *
 */
@Configurable(tag="statwithin")
public class StatWithinTrigger implements ITrigger {

	String cat;
	String stat;
	int lowerThreshold;
	int upperThreshold;

	private static PlayerStatManager manager = null;

	public void configure(String config) {
		if(manager==null){
			manager = BeardAch.self.getStats();
		}
		String[] opt = config.split("\\:");
		if(opt.length==4){
			cat = opt[0];
			stat = opt[1];
			lowerThreshold = Integer.parseInt(opt[2]);
			upperThreshold = Integer.parseInt(opt[3]);
		}

	}


	public boolean checkAchievement(Player player) {
		if(manager!=null){
			//if player has stat
			if(manager.findPlayerBlob(player.getName()).hasStat(cat, stat)){
				//if player exceeds threshold
				if(manager.findPlayerBlob(player.getName()).hasStat(cat, stat)){
					return (
							(manager.findPlayerBlob(player.getName()).getStat(cat, stat).getValue()>=lowerThreshold) 
							&&
							(manager.findPlayerBlob(player.getName()).getStat(cat, stat).getValue()<=upperThreshold)
							);
				}
			}
		}
		return false;
	}
}
