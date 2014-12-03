/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tehbeard.beardach.achievement.triggers.meta;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

/**
 * 
 * @author James
 */
@ComponentHelpDescription(description = "Meta trigger, will fire if all triggers under it return true")
@Configurable(name = "Meta AND trigger", tag = "metaand")
public class ANDTrigger implements MetaTrigger {

    @ComponentValueDescription(value = "list of triggers")
    @Expose
    @EditorField(type = EditorFieldType.trigger, alias = "triggers")
    private ITrigger[] triggers;

    @Override
    public ITrigger[] getTriggers() {
        return triggers;
    }

    @Override
    public boolean checkAchievement(Player player) {
        for (ITrigger t : getTriggers()) {
            if(t == null){continue;}
            if (!t.checkAchievement(player))
                return false;
        }
        return true;
    }

    @Override
    public void configure(Achievement ach) {
        for (ITrigger t : getTriggers()) {
            if(t == null){continue;}
            t.configure(ach);
        }
    }

}
