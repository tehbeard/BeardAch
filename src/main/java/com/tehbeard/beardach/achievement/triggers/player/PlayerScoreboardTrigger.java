package com.tehbeard.beardach.achievement.triggers.player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;
import org.bukkit.Bukkit;
import org.spongepowered.api.entity.player.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

@ComponentDescription(description = "Check if a scoreboard objective meets certain criteria",categories ={"player"})
@Configurable(name = "scoreboard value", tag = "scoreboard")
public class PlayerScoreboardTrigger implements ITrigger {

    @ComponentFieldDescription(value = "Scoreboard value")
    @Expose
    @EditorField(alias = "Value", type = EditorFieldType.number)
    private int value;

    @ComponentFieldDescription(value = "Scoreboard value")
    @Expose
    @EditorField(alias = "Objective", type = EditorFieldType.text)
    private String objective;

    @ComponentFieldDescription(value = "Scoreboard value")
    @Expose
    @EditorField(alias = "Use main scoreboard", type = EditorFieldType.bool)
    private boolean useMain;
    
    @ComponentFieldDescription(value = "Scoreboard value")
    @Expose
    @EditorField(alias = "Operation", type = EditorFieldType.selection,options = "com.tehbeard.beardach.achievement.triggers.player.PlayerScoreboardTrigger$Operation")
    private Operation operation;
    
    public enum Operation{
        lessThan,
        equalTo,
        greaterThan,
        lessThanEq,
        greaterThanEq
        
    }

    @Override
    public void configure(Achievement ach) {

    }

    @Override
    public boolean checkAchievement(Player player) {
       
        Scoreboard scoreboard = useMain ? Bukkit.getScoreboardManager().getMainScoreboard() : player.getScoreboard();
        Objective obj = scoreboard.getObjective(objective);
        if(obj != null){
            Score score = obj.getScore(player);
            if(score != null){
                int v = score.getScore();
                return (operation == Operation.equalTo && v == value) ||
                       (operation == Operation.lessThan && v < value ) ||
                       (operation == Operation.greaterThan && v > value)||
                       (operation == Operation.lessThanEq && v <= value ) ||
                       (operation == Operation.greaterThanEq && v >= value);
            }
        }
        
        return false;
    }
}
