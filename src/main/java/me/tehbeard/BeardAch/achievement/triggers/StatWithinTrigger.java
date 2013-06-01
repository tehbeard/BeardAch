package me.tehbeard.BeardAch.achievement.triggers;
import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.BeardStat.containers.PlayerStatManager;

/**
 * Checks if a players stat is above certain threshold, then triggers. 
 * @author James
 *
 */
@Configurable(tag="statwithin",name="Stat within boundaries")
public class StatWithinTrigger implements ITrigger {


    @Expose
    @EditorField(alias="Domain")
    String domain = "default";
    @Expose
    @EditorField(alias="World")
    String world = "*";

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
            int value = manager.findPlayerBlob(player.getName()).getStat(domain,world,cat, stat).getValue();
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
