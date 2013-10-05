/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tehbeard.BeardAch.achievement.triggers.meta;

import com.google.gson.annotations.Expose;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;

/**
 * Base class for meta triggers, allows registering of the underlying triggers
 * @author James
 */
public abstract class MetaTrigger implements ITrigger{
    @Expose    
    private ITrigger[] triggers;
    
    public ITrigger[] getTriggers(){return triggers;}
}
