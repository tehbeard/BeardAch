package com.tehbeard.beardach.achievement.triggers.player;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

@ComponentHelpDescription(description = "Checks the players bank balance", dependencies = { "Vault"})
@Configurable(tag = "bankbalance", name = "(Vault)Check bank balance")
public class EconomyTrigger implements ITrigger {

    private static Economy economy = null;

    @ComponentValueDescription(value = "Balance must be atleast this value to trigger, supports decimal values")
    @Expose
    @EditorField(alias = "Lower threshold")
    private double amount;

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
    public boolean checkAchievement(Player player) {
        if (setupEconomy())
            return economy.bankBalance(player.getName()).balance >= amount;
        return false;
    }

    @Override
    public void configure(Achievement ach) {
        setupEconomy();

    }

}
