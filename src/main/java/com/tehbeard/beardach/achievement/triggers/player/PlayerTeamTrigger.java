package com.tehbeard.beardach.achievement.triggers.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

@ComponentHelpDescription(description = "Is player on a team")
@Configurable(name = "Player team", tag = "onteam")
public class PlayerTeamTrigger implements ITrigger {

    @ComponentValueDescription(value = "Team name to check for (Case Sensitive)")
    @Expose
    @EditorField(alias = "Team name")
    private String name;

    @Override
    public void configure(Achievement ach) {

    }

    @Override
    public boolean checkAchievement(Player player) {
        Team team = Bukkit.getScoreboardManager().getMainScoreboard().getPlayerTeam(player);
        if (team == null)
            return false;
        return team.getName().equals(name);
    }

}