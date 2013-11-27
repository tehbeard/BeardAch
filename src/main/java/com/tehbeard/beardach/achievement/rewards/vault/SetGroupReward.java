package com.tehbeard.beardach.achievement.rewards.vault;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.google.gson.annotations.Expose;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentType;


@ComponentHelpDescription(description = "Sets the group of a player using vault, \\n Exact behaviour will depend on the permissions plugin, consult your permissions plugin as needed. \\n Should expected behaviour not occur, consider using the execute console command reward instead.", name = "Set group", type = ComponentType.REWARD,dependencies={"Vault","Permissions plugin that works with Vault"})
@Configurable(tag="vaultaddgroup",name="(Vault) add/set group")
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
