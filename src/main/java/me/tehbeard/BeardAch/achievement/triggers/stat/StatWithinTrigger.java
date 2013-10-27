package me.tehbeard.BeardAch.achievement.triggers.stat;
import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentValueDescription;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.BeardStat.DataProviders.IStatDataProvider;
import com.tehbeard.BeardStat.manager.EntityStatManager;

/**
 * Checks if a players stat is above certain threshold, then triggers. 
 * @author James
 *
 */
@ComponentHelpDescription(description = "Triggers if statistic is between two values", name = "Stat within boundaries", type = ComponentType.TRIGGER)
@Configurable(tag="statwithin",name="Stat within boundaries")
public class StatWithinTrigger implements ITrigger {


    @ComponentValueDescription(description="domain to check, for the regular stats use default as the value")
    @Expose
    @EditorField(alias="Domain")
    String domain = "default";
    @ComponentValueDescription(description = "World to check stats for (* matches all worlds)")
    @Expose
    @EditorField(alias="World")
    String world = "*";
    @ComponentValueDescription(description = "statistic category to check")
    @Expose
    @EditorField(alias="Category")
    String cat;
    @ComponentValueDescription(description = "Statistic name to check")
    @Expose
    @EditorField(alias="Statistic")
    String stat;
    @ComponentValueDescription(description = "Threshold statistic must equal or greater than")
    @Expose
    @EditorField(alias="Lower bound threshold")
    int lowerThreshold;
    @ComponentValueDescription(description = "Threshold statistic must equal or be less than")
    @Expose
    @EditorField(alias="Upper bound threshold")
    int upperThreshold;

    private static EntityStatManager manager = null;

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
            int value = manager.getBlobByNameType(player.getName(), IStatDataProvider.PLAYER_TYPE).getValue().getStat(domain,world,cat, stat).getValue();
            return (value>=lowerThreshold && value<=upperThreshold);
        }
        else
        {
            BeardAch.printCon("[PANIC] Attempting to use Statwithin trigger when BeardStat not loaded!!!");
        }
        return false;
    }


    public void configure(Achievement ach) {
        if(manager==null){
            manager = BeardAch.self.getStats();
        }

    }
}
