/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tehbeard.BeardAch.achievement.triggers.meta;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
@ComponentHelpDescription(name = "[Meta] AND trigger",description = "Meta trigger, will fire if all triggers under it return true",type = ComponentType.TRIGGER)
@Configurable(name = "Meta AND trigger",tag = "metaand")
public class ANDTrigger extends MetaTrigger{

    @Override
    public boolean checkAchievement(Player player) {
        for(ITrigger t : getTriggers()){
            if(!t.checkAchievement(player)){return false;}
        }
        return true;
    }

    @Override
    public void configure(Achievement ach, String config) {
        throw new UnsupportedOperationException("Not supported."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void configure(Achievement ach) {
        for(ITrigger t : getTriggers()){
            t.configure(ach);
        }
    }
    
}
