package me.tehbeard.BeardAch.achievement.rewards;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.help.*;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.google.gson.annotations.Expose;

@Configurable(tag="money",name="(vault) Give money")
@Usage(arguments=@Argument(name="amount",desc="Amount of money to give player, decimals supported"),packageName="base",dependencies="Vault")
public class EconomyReward implements IReward {

	private static Economy economy = null;
	@Expose
	@EditorField(alias="Amount to give")
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
		amount = Double.parseDouble(config);
		setupEconomy();
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

    public void configure(Achievement ach) {
        setupEconomy();
        
    }

}
