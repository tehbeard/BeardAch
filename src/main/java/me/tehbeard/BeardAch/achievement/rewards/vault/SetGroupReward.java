package me.tehbeard.BeardAch.achievement.rewards.vault;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.google.gson.annotations.Expose;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;


@ComponentHelpDescription(description = "Sets the group of a player using vault, \\n Exact behaviour will depend on the permissions plugin, consult your permissions plugin as needed. \\n Should expected behaviour not occur, consider using the execute console command reward instead.", name = "give money", type = ComponentType.REWARD,dependencies={"Vault","Permissions plugin that works with Vault"})
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
