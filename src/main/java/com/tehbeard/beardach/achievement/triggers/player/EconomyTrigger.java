package com.tehbeard.beardach.achievement.triggers.player;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentType;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.google.gson.annotations.Expose;

@ComponentHelpDescription(description = "Checks the players bank balance", name = "Check bank balance", type = ComponentType.TRIGGER,dependencies={"Vault","Economy plugin with Vault support"})
@Configurable(tag="bankbalance",name="(Vault)Check bank balance")
public class EconomyTrigger implements ITrigger {

	private static Economy economy = null;
	
	@ComponentValueDescription(description="Balance must be atleast this value to trigger, supports decimal values",examples={"100000","55.56"})
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
