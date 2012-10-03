package me.tehbeard.BeardAch.achievement.rewards;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.configurable.Usage;

@Configurable(tag="vaultaddgroup")
@Usage(arguments={"group|group to add/set"},packageName="base",dependencies="Vault",blurb="Adds or sets a players group, via vault. Consult your permissions plugin documentation for further information on how this works.")
public class SetGroupReward implements IReward {
    
    
    private static Permission perms;
    private String group = ""; 
    private static Boolean setupPerms() {
        if(perms==null){
            RegisteredServiceProvider<Permission> economyProvider = Bukkit.getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
            if (economyProvider != null) {
                perms = economyProvider.getProvider();
            }
        }
        return (perms != null);
    }

    public void configure(Achievement ach, String config) {
        group = config;
        
    }

    public void giveReward(Player player) {
        if(setupPerms()){
            perms.playerAddGroup(player, group);
        }
    }

}
