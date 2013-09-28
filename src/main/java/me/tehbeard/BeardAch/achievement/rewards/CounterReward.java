package me.tehbeard.BeardAch.achievement.rewards;



import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentValueDescription;

import com.tehbeard.BeardStat.containers.PlayerStatManager;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

@ComponentHelpDescription(description = "Increment counter value. Counters are stored in BeardStat and accessible from the stats triggers", name = "Increment counter", type = ComponentType.REWARD,dependencies={"BeardStat"})
@Configurable(tag="counter",name="Increment counter")
public class CounterReward implements  IReward{

    private static final String DOMAIN = "beardach";

    @ComponentValueDescription(description="Name of the counter")
    @Expose
    @EditorField(alias="Counter name")
	String name = "";
    
    @ComponentValueDescription(description="Amount to increment counter by")
    @Expose
    @EditorField(alias="Amount to increment")
	int count = 0;
	private static PlayerStatManager manager = null;
	public void configure(Achievement Ach,String config) {
		
		String[] opt = config.split("\\:");
		if(opt.length==2){
			name = opt[0];
			count = Integer.parseInt(opt[1]);
		}

	}


	public void giveReward(Player player) {
		if(manager != null){
			manager.getPlayerBlob(player.getName()).getStat(DOMAIN,player.getWorld().getName(),"achCount", name).incrementStat(count);
		}
		else{
		    BeardAch.printError("BeardStat not loaded, reward not given!");
		}
	}


    public void configure(Achievement ach) {
        if(manager==null){
            manager = BeardAch.self.getStats();
        }
    }

}
