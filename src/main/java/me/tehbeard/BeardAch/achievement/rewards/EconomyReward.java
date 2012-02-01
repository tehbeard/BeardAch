package me.tehbeard.BeardAch.achievement.rewards;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

@Configurable(tag="economy")
public class EconomyReward implements IReward {

	private static Economy economy = null;
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

	public void configure(String config) {
		setupEconomy();
		amount = Double.parseDouble(config);
	}

	public void giveReward(Player player) {
		if(setupEconomy()){
			economy.depositPlayer(player.getName(), amount);
		}
		else
		{
			BeardAch.printCon("ALERT!: Economy reward was not given because economy support did not load");
		}
	}

}
