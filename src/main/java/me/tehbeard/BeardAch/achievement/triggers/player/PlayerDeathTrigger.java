/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tehbeard.BeardAch.achievement.triggers.player;

import com.google.gson.annotations.Expose;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.AbstractEventTrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorFieldType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentValueDescription;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 *
 * @author James
 */
@ComponentHelpDescription(name = "Killed by entity",description = "fires when killed by a specific entity",type = ComponentType.TRIGGER)
@Configurable(name = "Killed by",tag = "deathentity")
public class PlayerDeathTrigger  extends AbstractEventTrigger{

    @ComponentValueDescription(description = "Entity type to kill player")
    @Expose
    @EditorField(alias = "Entity",type = EditorFieldType.selection,options = "org.bukkit.entity.EntityType")
    private EntityType entityType;

    @Override
    public void configure(Achievement ach, String config) {
    }

    @Override
    public void configure(Achievement ach) {
    }

    
    
    @EventHandler(priority = EventPriority.MONITOR,ignoreCancelled = true)
    public void onKill(EntityDeathEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player)event.getEntity();
            EntityDamageEvent damage = event.getEntity().getLastDamageCause();
            if(damage instanceof EntityDamageByEntityEvent){
                EntityDamageByEntityEvent ede = (EntityDamageByEntityEvent)damage;
                Entity entity = ede.getDamager();
                
                if(entity instanceof Projectile){
                    entity = ((Projectile)entity).getShooter();
                }
                
                if(entity.getType() == entityType){
                    add(player);
                    checkAchievement(player);
                    remove(player);
                }
            }
        }
    }
}