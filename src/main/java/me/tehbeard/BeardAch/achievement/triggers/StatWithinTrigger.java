package me.tehbeard.BeardAch.achievement.triggers;
import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.help.Argument;
import me.tehbeard.BeardAch.achievement.help.Usage;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardStat.BeardStat;
import me.tehbeard.BeardStat.containers.PlayerStatManager;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

/**
 * Checks if a players stat is above certain threshold, then triggers. 
 * @author James
 *
 */
@Configurable(tag="statwithin",name="Stat within boundaries")
@Usage(arguments={
        @Argument(name="category",desc=""),
        @Argument(name="stat",desc=""),
        @Argument(name="lower threshold",desc="Must be atleast this value"),
        @Argument(name="upper threshold",desc="Must be less than or at most this value")
        },packageName="base",blurb="")
public class StatWithinTrigger implements ITrigger {

    @Expose
    @EditorField(alias="Category")
	String cat;
    @Expose
    @EditorField(alias="statistic")
	String stat;
    @Expose
    @EditorField(alias="Lower bound threshold")
	int lowerThreshold;
    @Expose
    @EditorField(alias="Upper bound threshold")
	int upperThreshold;

	private static PlayerStatManager manager = null;

	public void configure(Achievement ach,String config) {
		
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
		else
        {
            BeardStat.printCon("[PANIC] Attempting to use Statwithin trigger when BeardStat not loaded!!!");
        }
		return false;
	}


    public void configure(Achievement ach) {
        if(manager==null){
            manager = BeardAch.self.getStats();
        }
        
    }
}
