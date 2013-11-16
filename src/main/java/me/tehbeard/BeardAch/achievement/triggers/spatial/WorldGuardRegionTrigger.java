package me.tehbeard.BeardAch.achievement.triggers.spatial;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentValueDescription;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.google.gson.annotations.Expose;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.tehbeard.BeardAch.achievement.triggers.AbstractEventTrigger;

@ComponentHelpDescription(description = "Player inside a worldGuard region", name = "WorldGuard region", type = ComponentType.TRIGGER,dependencies="WorldGuard")
@Configurable(tag="wgregion",name="(WorldGuard) Inside region")
public class WorldGuardRegionTrigger extends AbstractEventTrigger{

    private RegionManager rm;
    
    @ComponentValueDescription(description = "name of region")
    @Expose
    @EditorField(alias="Region name")
    private String region = "";
    @ComponentValueDescription(description = "name of world")
    @Expose
    @EditorField(alias="World name")
    private String world = "";

    @Override
    public boolean checkAchievement(Player player) {
        if(rm==null){
            return false;
        }
        if(!player.getWorld().getName().equals(world)){return false;}
        
        ApplicableRegionSet zones = rm.getApplicableRegions(player.getLocation());
        for(ProtectedRegion zone : zones){
            if(zone.getId().equals(region)){
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void move(PlayerMoveEvent event){

        if(event.getTo().getBlockX() != event.getFrom().getBlockX() ||
                event.getTo().getBlockY() != event.getFrom().getBlockY() || 
                event.getTo().getBlockZ() != event.getFrom().getBlockZ()
                ){
            if(event.getPlayer().getWorld().getName().equals(world)){
                if(checkAchievement(event.getPlayer())){
                    getAchievement().checkAchievement(event.getPlayer());
                }
            }
        }
    }

    @Override
    public void configure(Achievement ach) {
        super.configure(ach);
        World w = Bukkit.getWorld(world);
        WorldGuardPlugin wg = BeardAch.instance().getWorldGuard();
        if(wg==null){
            BeardAch.instance().getLogger().severe("[ERROR] " + "WorldGuard not loaded! trigger will fail!");
            return;
        }
        rm = wg.getRegionManager(w);
        if(rm==null){
            BeardAch.instance().getLogger().severe("[ERROR] " + "World not found!");
            return;
        }
        
    }

}
