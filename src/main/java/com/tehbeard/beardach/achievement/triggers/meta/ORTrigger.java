/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tehbeard.beardach.achievement.triggers.meta;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.dataSource.configurable.Configurable;
import com.tehbeard.beardach.dataSource.json.editor.EditorField;
import com.tehbeard.beardach.dataSource.json.editor.EditorFieldType;
import com.tehbeard.beardach.dataSource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.dataSource.json.help.ComponentType;
import com.tehbeard.beardach.dataSource.json.help.ComponentValueDescription;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
@ComponentHelpDescription(name = "[Meta] OR trigger",description = "Meta trigger, will fire if any trigger under it returns true",type = ComponentType.TRIGGER)
@Configurable(name = "Meta OR trigger",tag = "metaor")
public class ORTrigger implements MetaTrigger{
    
    @ComponentValueDescription(description = "list of triggers")
    @Expose
    @EditorField(type = EditorFieldType.trigger,alias = "triggers")
    private ITrigger[] triggers;
    
    public ITrigger[] getTriggers(){
        return triggers;
    }

    @Override
    public boolean checkAchievement(Player player) {
        for(ITrigger t : getTriggers()){
            if(t.checkAchievement(player)){return true;}
        }
        return false;
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
