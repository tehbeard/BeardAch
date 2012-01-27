package me.tehbeard.BeardAch.achievement.triggers;
import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardStat.containers.PlayerStatManager;
import org.bukkit.entity.Player;

/**
 * Checks if a players stat is between certain threshold, then triggers. 
 * @author James
 *
 */
@Configurable(tag="stat")
public class StatCheckTrigger implements ITrigger {


	String cat;
	String stat;
	int threshold;
	private static PlayerStatManager manager = null;

	public void configure(String config) {
		if(manager==null){
			manager = BeardAch.self.getStats();
		}
		String[] opt = config.split("\\:");
		if(opt.length==3){
			cat = opt[0];
			stat = opt[1];
			threshold = Integer.parseInt(opt[2]);

		}
	}

	public boolean checkAchievement(Player player) {
		//if player has stat
		if(manager!=null){
			if(manager.findPlayerBlob(player.getName()).hasStat(cat, stat)){
				//if player exceeds threshold
				return manager.findPlayerBlob(player.getName()).getStat(cat, stat).getValue()>=threshold;
			}
		}
		return false;
	}

}
