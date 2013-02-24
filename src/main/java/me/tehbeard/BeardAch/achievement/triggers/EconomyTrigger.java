package me.tehbeard.BeardAch.achievement.triggers;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.help.Argument;
import me.tehbeard.BeardAch.achievement.help.Usage;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.google.gson.annotations.Expose;

@Configurable(tag="bankbalance")@Usage(arguments={
        @Argument(name="balance",desc="Amount player must have"),
        },packageName="base",dependencies="vault")
public class EconomyTrigger implements ITrigger {

	private static Economy economy = null;
	@Expose
	@EditorField(alias="Lower threshold")
	private double amount;

	private static Boolean setupEconomy() {
		if(economy==null){
			RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
			if (economyProvider != null) {
				economy = economyProvider.getProvider();
			}
		}
		return (economy != null);
	}

	public void configure(Achievement ach,String config) {
		
		amount = Double.parseDouble(config);
	}


	public boolean checkAchievement(Player player) {
		if(setupEconomy()){
		return (economy.bankBalance(player.getName()).balance >= amount); 
		}
		return false;
	}

    public void configure(Achievement ach) {
        setupEconomy();
        
    }

}
