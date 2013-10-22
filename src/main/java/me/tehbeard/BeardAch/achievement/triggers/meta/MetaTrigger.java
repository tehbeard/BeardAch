/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tehbeard.BeardAch.achievement.triggers.meta;

import me.tehbeard.BeardAch.achievement.triggers.ITrigger;

/**
 * Base class for meta triggers, allows registering of the underlying triggers
 * @author James
 */
public interface MetaTrigger extends ITrigger{
    
    public ITrigger[] getTriggers();
}
