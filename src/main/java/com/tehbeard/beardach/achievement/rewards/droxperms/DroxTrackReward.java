package com.tehbeard.beardach.achievement.rewards.droxperms;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;

@ComponentHelpDescription(description = "Track promotion player", dependencies = "DroxPerms")
@Configurable(tag = "promote", name = "(DroxPerms) Promote along track")
public class DroxTrackReward implements IReward {
    @Expose
    @EditorField(alias = "Track to promote along")
    String track = "";

    @Override
    public void giveReward(Player player) {
        if (BeardAch.droxAPI != null) {
            BeardAch.droxAPI.promotePlayer(player.getName(), track);
            BeardAch.getLogger().fine("Played Promoted");
        }
    }

    @Override
    public void configure(Achievement ach) {

    }

}
