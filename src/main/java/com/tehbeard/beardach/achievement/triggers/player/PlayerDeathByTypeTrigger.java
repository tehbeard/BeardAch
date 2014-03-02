/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tehbeard.beardach.achievement.triggers.player;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.triggers.AbstractEventTrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

/**
 * 
 * @author James
 */
@ComponentHelpDescription(description = "fires when killed by specific damage type")
@Configurable(name = "Killed by", tag = "deathtype")
public class PlayerDeathByTypeTrigger extends AbstractEventTrigger {

    @ComponentValueDescription(value = "Damage type that killed the player")
    @Expose
    @EditorField(alias = "Damage type", type = EditorFieldType.selection, options = "org.bukkit.event.entity.EntityDamageEvent.DamageCause")
    private DamageCause cause;

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onKill(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            EntityDamageEvent damage = event.getEntity().getLastDamageCause();


            if (damage.getCause() == cause) {
                runCheck(player);
            }
        }
    }

}