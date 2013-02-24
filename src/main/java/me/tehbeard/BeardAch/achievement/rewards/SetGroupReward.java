package me.tehbeard.BeardAch.achievement.rewards;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.google.gson.annotations.Expose;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.help.*;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;

@Configurable(tag="vaultaddgroup")
@Usage(arguments={@Argument(name="group",desc="Add/set user to a group")},packageName="base",dependencies="Vault",blurb="Adds or sets a players group, via vault. Consult your permissions plugin documentation for further information on how this works.")
public class SetGroupReward implements IReward {
    
    
    private static Permission perms;
    @Expose
    @EditorField(alias="Group to add")
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

    public void configure(Achievement ach) {
        setupPerms();
        
    }

}
