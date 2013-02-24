package me.tehbeard.BeardAch.achievement.rewards;



import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.help.*;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardStat.containers.PlayerStatManager;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
@Configurable(tag="counter",name="Increment counter")
@Usage(arguments={
        @Argument(name="name",desc="counter name"),
        @Argument(name="count",desc="Amount to increment by")
        }, 
        packageName = "base",
        blurb="Serves as a counter, value is stored in BeardStat under the achCount category, can be read by other achievements using stat triggers")
public class CounterReward implements  IReward{

    @Expose
    @EditorField(alias="Counter name")
	String name = "";
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
			manager.getPlayerBlob(player.getName()).getStat("achCount", name).incrementStat(count);
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
