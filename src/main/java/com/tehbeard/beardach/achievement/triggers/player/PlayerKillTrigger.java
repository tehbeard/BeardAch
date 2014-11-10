/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tehbeard.beardach.achievement.triggers.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.spongepowered.api.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.projectiles.ProjectileSource;

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
@ComponentHelpDescription(description = "fires when killing a specific entity",categories ={"evented","player"})
@Configurable(name = "Kill entity", tag = "killentity")
public class PlayerKillTrigger extends AbstractEventTrigger {

    @ComponentValueDescription(value = "Entity type to kill")
    @Expose
    @EditorField(alias = "Entity", type = EditorFieldType.selection, options = "org.bukkit.entity.EntityType")
    private EntityType entityType;

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onKill(EntityDeathEvent event) {
        if (event.getEntity().getType() == entityType) {
            EntityDamageEvent damage = event.getEntity().getLastDamageCause();
            if (damage instanceof EntityDamageByEntityEvent) {
                EntityDamageByEntityEvent ede = (EntityDamageByEntityEvent) damage;
                Entity entity = ede.getDamager();

                if (entity instanceof Projectile) {
                    ProjectileSource src = ((Projectile) entity).getShooter();
                    entity = src instanceof Entity ? (Entity)src : null;
                    
                }
                
                if (entity != null && entity.getType() == entityType) {
                    runCheck((Player) entity);
                }
            }
        }
    }
}