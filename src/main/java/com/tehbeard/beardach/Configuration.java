/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tehbeard.beardach;

import com.tehbeard.utils.syringe.InjectConfig;

/**
 *
 * @author James
 */
public class Configuration {
    
    @InjectConfig("ach.allow-exec-shell-reward")
    public boolean allowExecRewards = false;
    
    @InjectConfig("ach.database.type")
    public String dbType = "json";
    
    @InjectConfig("general.plugin-stats-opt-out")
    public boolean statsOptOut = true;
    
    @InjectConfig("ach.add-no-creative-trigger")
    public boolean noCreativeTrigger = false;
}
