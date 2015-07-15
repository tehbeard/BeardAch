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
import com.tehbeard.beardach.datasource.json.editor.EditorFieldMin;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;

/**
 * 
 * @author James
 */
@ComponentDescription(description = "Temporal trigger, will fire for a certain amount of time if any trigger under it fires")
@Configurable(name = "Temporal trigger", tag = "metatemporal")
public class TemporalTrigger implements MetaTrigger {

    @ComponentFieldDescription(value = "list of triggers")
    @Expose
    @EditorField(type = EditorFieldType.trigger, alias = "triggers")
    private ITrigger[] triggers;
     
    @ComponentFieldDescription("Delay between a trigger being active and this meta trigger activating, in seconds.")
    @Expose
    @EditorField(type = EditorFieldType.number, alias="Start Delay")
    @EditorFieldMin(0)
    private int startDelay = 0;
    
    @ComponentFieldDescription("How long the trigger should remain active for")
    @Expose
    @EditorField(type = EditorFieldType.number, alias="Hold for")
    @EditorFieldMin(0)
    private int holdFor = 0;
    
    @ComponentFieldDescription("Extend latch time if trigger still active?")
    @Expose
    @EditorField(type = EditorFieldType.bool, alias="Latch as long as triggered")
    @EditorFieldMin(0)
    private boolean latch = false;
     

    @Override
    public ITrigger[] getTriggers() {
        return triggers;
    }

    @Override
    public boolean checkAchievement(Player player) {
        for (ITrigger t : getTriggers()) {
            if(t == null){continue;}
            if (t.checkAchievement(player))
                return true;
        }
        return false;
    }

    @Override
    public void configure(Achievement ach) {
        for (ITrigger t : getTriggers()) {
            if(t == null){continue;}
            t.configure(ach);
        }
    }

}
