package me.tehbeard.BeardAch.achievement.triggers.player;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentValueDescription;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import com.google.gson.annotations.Expose;

@ComponentHelpDescription(description = "Is player on a team", name = "On team", type = ComponentType.TRIGGER)
@Configurable(name="Player team",tag="onteam")
public class PlayerTeamTrigger implements ITrigger {
    
    @ComponentValueDescription(description="Team name to check for (Case Sensitive)")
    @Expose
    @EditorField(alias = "Team name")
    private String name;

    public void configure(Achievement ach, String config) {
        
    }

    public void configure(Achievement ach) {
        
    }

    public boolean checkAchievement(Player player) {
        Team team = Bukkit.getScoreboardManager().getMainScoreboard().getPlayerTeam(player);
        if(team == null){return false;}
        return team.getName().equals(name);
    }

}
