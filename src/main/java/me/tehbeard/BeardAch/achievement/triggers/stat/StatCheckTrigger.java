package me.tehbeard.BeardAch.achievement.triggers.stat;
import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentValueDescription;

import com.tehbeard.BeardStat.containers.PlayerStatManager;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

/**
 * Checks if a players stat is between certain threshold, then triggers. 
 * @author James
 *
 */
@ComponentHelpDescription(description = "Value of statistic is atleast provided value. This trigger supports regex values", name = "Stat Threshold", type = ComponentType.TRIGGER,dependencies="BeardStat")
@Configurable(tag="stat",name="stat above threshold")
public class StatCheckTrigger implements ITrigger {


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
    @ComponentValueDescription(description = "Threshold statistic must equal or beat")
    @Expose
    @EditorField(alias="Lower threshold")
    int threshold;
    private static PlayerStatManager manager = null;

    public void configure(Achievement ach,String config) {

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
            //if player exceeds threshold
            return manager.findPlayerBlob(player.getName()).getStats(domain,world,cat, stat).getValue()>=threshold;
        }
        else
        {
            BeardAch.printCon("[PANIC] Attempting to use Stat trigger when BeardStat not loaded!!!");
        }
        return false;
    }

    public void configure(Achievement ach) {
        if(manager==null){
            manager = BeardAch.self.getStats();
        }

    }

}
