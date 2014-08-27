package com.tehbeard.beardach.achievement.triggers.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

/**
 * Checks if a player is an OP
 * 
 * @author James
 * 
 */
@ComponentHelpDescription(description = "Is the player in a certain gamemode",categories ={"player"})
@Configurable(tag = "isgamemode", name = "is player in a certain gamemode")
public class IsGamemodeTrigger implements ITrigger {
    
    public IsGamemodeTrigger(){
        
    }
    
    public IsGamemodeTrigger(GameMode mode,boolean inverse){
        this.mode = mode;
        this.inverse = inverse;
    }

    @ComponentValueDescription(value = "Invert this trigger to be NOT in this gamemode")
    @Expose
    @EditorField(alias = "Invert (must NOT be in this mode)",type=EditorFieldType.bool)
    boolean inverse;
    
    @ComponentValueDescription(value = "Invert this trigger to be NOT in this gamemode")
    @Expose
    @EditorField(alias = "Gamemode to check",type=EditorFieldType.selection,options="org.bukkit.GameMode")
    GameMode mode;

    @Override
    public boolean checkAchievement(Player player) {
        return (player.getGameMode() == mode) != inverse;
    }

    @Override
    public void configure(Achievement ach) {

    }

}
