package com.tehbeard.beardach.achievement.rewards.vault;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;

@ComponentHelpDescription(description = "Gives a player some money", dependencies = { "Vault", "Economy plugin that works with Vault" })
@Configurable(tag = "money", name = "(vault) Give money")
public class EconomyReward implements IReward {

    private static Economy economy = null;
    @Expose
    @EditorField(alias = "Amount to give (can be a decimal")
    private double amount = 0.0D;

    private static Boolean setupEconomy() {
        if (economy == null) {
            RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
            if (economyProvider != null) {
                economy = economyProvider.getProvider();
            }
        }
        return economy != null;
    }

    @Override
    public void configure(Achievement Ach, String config) {
        amount = Double.parseDouble(config);
        setupEconomy();
    }

    @Override
    public void giveReward(Player player) {
        if (setupEconomy()) {
            economy.depositPlayer(player.getName(), amount);
        } else {
            BeardAch.instance().getLogger().severe("[ERROR] " + "Economy reward was not given because economy support did not load");
        }
    }

    @Override
    public void configure(Achievement ach) {
        setupEconomy();

    }

}
