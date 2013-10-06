/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tehbeard.BeardAch.achievement.triggers.meta;

import com.google.gson.annotations.Expose;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentValueDescription;

/**
 * Base class for meta triggers, allows registering of the underlying triggers
 * @author James
 */
public abstract class MetaTrigger implements ITrigger{
    
    @ComponentValueDescription(description = "list of triggers")
    @Expose    
    private ITrigger[] triggers;
    
    public ITrigger[] getTriggers(){return triggers;}
}
