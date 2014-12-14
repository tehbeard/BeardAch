package com.tehbeard.beardach.achievement.triggers.player;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;
import org.spongepowered.api.entity.player.gamemode.GameMode;

/**
 * Checks if a player is an OP
 * 
 * @author James
 * 
 */
@ComponentDescription(description = "Is the player in a certain gamemode",categories ={"player"})
@Configurable(tag = "isgamemode", name = "is player in a certain gamemode")
public class IsGamemodeTrigger implements ITrigger {
    
    public IsGamemodeTrigger(){
        
    }
    
    public IsGamemodeTrigger(GameMode mode,boolean inverse){
        this.mode = mode;
        this.inverse = inverse;
    }

    @ComponentFieldDescription(value = "Invert this trigger to be NOT in this gamemode")
    @Expose
    @EditorField(alias = "Invert (must NOT be in this mode)",type=EditorFieldType.bool)
    boolean inverse;
    
    @ComponentFieldDescription(value = "Invert this trigger to be NOT in this gamemode")
    @Expose
    @EditorField(alias = "Gamemode to check",type=EditorFieldType.selection)
    GameMode mode;

    @Override
    public boolean checkAchievement(Player player) {
        //return (player.getGamemode() == mode) != inverse;
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void configure(Achievement ach) {

    }

}
