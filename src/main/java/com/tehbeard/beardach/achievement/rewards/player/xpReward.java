package com.tehbeard.beardach.achievement.rewards.player;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

@ComponentHelpDescription(description = "Gives a player some xp")
@Configurable(tag = "xp", name = "Give xp")
public class xpReward implements IReward {

    @Expose
    @EditorField(alias = "Amount to give",type=EditorFieldType.number,min=0)
    @ComponentValueDescription("Raw amount of XP to give a player (for example, a blaze drops 10 xp")
    int xp;
    
    @Expose
    @EditorField(alias = "Amount of levels to add",type=EditorFieldType.number,min=0)
    @ComponentValueDescription("XP levels to add to a player (note: negative values will take away, and cap at 0)")
    int xpLevels = 0;

    @Override
    public void giveReward(Player player) {
        player.giveExp(xp);
        if(xpLevels != 0){
          int newXpLevel = player.getLevel() - xpLevels;
          if(newXpLevel >= 0){
              player.setLevel(newXpLevel);
          }
        }
        
    }

    @Override
    public void configure(Achievement ach) {
    }

}
