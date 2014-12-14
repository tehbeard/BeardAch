
package com.tehbeard.beardach.achievement.triggers.player;


import org.spongepowered.api.entity.player.Player;
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
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;

/**
 * 
 * @author James
 */
@ComponentDescription(description = "fires when killed by specific damage type",categories ={"evented","player"})
@Configurable(name = "Killed by damage", tag = "deathtype")
public class PlayerDeathByTypeTrigger extends AbstractEventTrigger {

    @ComponentFieldDescription(value = "Damage type that killed the player")
    @Expose
    @EditorField(alias = "Damage type", type = EditorFieldType.selection, options = "org.bukkit.event.entity.EntityDamageEvent$DamageCause")
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