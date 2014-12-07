package com.tehbeard.beardach.achievement.rewards.command;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.message.Messages;
import org.spongepowered.api.text.title.Title;
import org.spongepowered.api.text.title.Titles;

@ComponentHelpDescription(description = "Sends a player a /title message")
@Configurable(tag = "title", name = "Display /Title")
public class TitleReward implements IReward {

    @ComponentValueDescription(value = "Title Text")
    @Expose
    @EditorField(alias = "Title Text")
    private String titleText = "";
    
    @ComponentValueDescription(value = "Title Text")
    @Expose
    @EditorField(alias = "Title colour",type = EditorFieldType.selection, options = {"black", "dark_blue", "dark_green", "dark_aqua", "dark_red", "dark_purple", "gold", "gray", "dark_gray", "blue", "green", "aqua", "red", "light_purple", "yellow", "white"})
    private String titleColor = "white";
    
    @ComponentValueDescription(value = "Subtitle Text")
    @Expose
    @EditorField(alias = "Subtitle Text")
    private String subtitleText = "";
    
    @ComponentValueDescription(value = "Subtitle Text")
    @Expose
    @EditorField(alias = "Subtitle colour",type = EditorFieldType.selection, options = {"black", "dark_blue", "dark_green", "dark_aqua", "dark_red", "dark_purple", "gold", "gray", "dark_gray", "blue", "green", "aqua", "red", "light_purple", "yellow", "white"})
    private String subtitleColor = "white";
    
    
    @ComponentValueDescription(value = "Fade in time")
    @Expose
    @EditorField(alias = "Fade in time",type = EditorFieldType.number,min = 0,max=120)
    private int fadeIn = 1;
    
    @ComponentValueDescription(value = "Stay time")
    @Expose
    @EditorField(alias = "Stay time",type = EditorFieldType.number,min = 0,max=120)
    private int stayTime = 3;
    
    @ComponentValueDescription(value = "Fade out time")
    @Expose
    @EditorField(alias = "Fade out time",type = EditorFieldType.number,min = 0,max=120)
    private int fadeOut = 1;

    private Title title = null;
    
    @Override
    public void giveReward(Player player) {
        player.sendTitle(title);
    }

    @Override
    public void configure(Achievement ach) {
        title = Titles
                .builder()
                .reset()
                .fadeIn(fadeIn*20)
                .stay(stayTime*20)
                .fadeOut(fadeOut*20)
                .title(Messages.builder(titleText)
                        .color(TextColors.valueOf(titleColor).or(TextColors.WHITE))
                        .build()
                )
                .subtitle(Messages.builder(subtitleText)
                        .color(TextColors.valueOf(subtitleColor).or(TextColors.WHITE))
                        .build()
                )
                .build();
    }

}
