package me.tehbeard.BeardAch.achievement.triggers;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.help.Argument;
import me.tehbeard.BeardAch.achievement.help.Usage;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

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

@Configurable(tag="wgregion")
@Usage(arguments={
        @Argument(name="world",desc=""),
        @Argument(name="region name",desc="")
        },packageName="base",blurb="Fires if player enters the region",dependencies="worldguard")
public class WorldGuardRegionTrigger implements ITrigger,Listener{

    private RegionManager rm;
    @Expose
    private String region = "";
    @Expose
    private String world = "";
    private Achievement ach;

    public void configure(Achievement ach, String config) {
        this.ach=ach;
        String[] c = config.split(":");
        if(c.length !=2){
            throw new IllegalArgumentException("Region AND World must be defined");
        }
        
        world = c[0];
        region = c[1];


    }

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
                    ach.checkAchievement(event.getPlayer());
                }
            }
        }
    }

    public void configure(Achievement ach) {
        World w = Bukkit.getWorld(world);
        WorldGuardPlugin wg = BeardAch.self.getWorldGuard();
        if(wg==null){
            BeardAch.printError("WorldGuard not loaded! trigger will fail!");
            return;
        }
        rm = wg.getRegionManager(w);
        if(rm==null){
            BeardAch.printError("World not found!");
            return;
        }
        
    }

}
