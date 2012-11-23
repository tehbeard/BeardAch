package me.tehbeard.BeardAch.achievement.rewards;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.configurable.Usage;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

@Configurable(tag="money")
@Usage(arguments="amount|Amount of money to give (decimals supported)",packageName="base",dependencies="Vault")
public class EconomyReward implements IReward {

	private static Economy economy = null;
	private double amount = 0.0D;

	private static Boolean setupEconomy() {
		if(economy==null){
			RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
			if (economyProvider != null) {
				economy = economyProvider.getProvider();
			}
		}
		return (economy != null);
	}

	public void configure(Achievement Ach,String config) {
		setupEconomy();
		amount = Double.parseDouble(config);
	}

	public void giveReward(Player player) {
		if(setupEconomy()){
			economy.depositPlayer(player.getName(), amount);
		}
		else
		{
			BeardAch.printError("Economy reward was not given because economy support did not load");
		}
	}

}
