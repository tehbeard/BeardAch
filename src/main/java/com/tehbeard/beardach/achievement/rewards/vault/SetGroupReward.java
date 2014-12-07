package com.tehbeard.beardach.achievement.rewards.vault;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;

@ComponentHelpDescription(description = "Sets the group of a player using vault, \\n Exact behaviour will depend on the permissions plugin, consult your permissions plugin as needed. \\n Should expected behaviour not occur, consider using the execute console command reward instead.", dependencies = {
        "Vault"})
@Configurable(tag = "vaultaddgroup", name = "(Vault) add/set group")
public class SetGroupReward implements IReward {

    @Expose
    @EditorField(alias = "Group to add")
    private String group = "";

    @Override
    public void giveReward(Player player) {
        
    }

    @Override
    public void configure(Achievement ach) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED");
    }

}
