package me.tehbeard.BeardAch.achievement.rewards.vault;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.google.gson.annotations.Expose;

@ComponentHelpDescription(description = "Gives a player some money", name = "give money", type = ComponentType.REWARD,dependencies={"Vault","Economy plugin that works with Vault"})
@Configurable(tag="money",name="(vault) Give money")
public class EconomyReward implements IReward {

	private static Economy economy = null;
	@Expose
	@EditorField(alias="Amount to give (can be a decimal")
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
