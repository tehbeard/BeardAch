/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tehbeard.beardach.achievement.triggers.meta;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;

import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
@ComponentHelpDescription(description = "Meta trigger, will fire if all triggers under it return true")
@Configurable(name = "Meta AND trigger",tag = "metaand")
public class ANDTrigger implements MetaTrigger{
    
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
