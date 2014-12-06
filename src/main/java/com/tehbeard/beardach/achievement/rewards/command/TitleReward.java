package com.tehbeard.beardach.achievement.rewards.command;

import org.bukkit.Bukkit;
import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

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

    private static final String jsonTemplate = "{color:%s,text:\"%s\"}";
    @Override
    public void giveReward(Player player) {

        String comm = "title " + player.getName() + " ";
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comm + "reset");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comm + "times " + fadeIn + " " + stayTime + " "+ fadeOut);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comm + "subtitle " + String.format(jsonTemplate, subtitleColor,subtitleText));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comm + "title " + String.format(jsonTemplate, titleColor,titleText));
    }

    @Override
    public void configure(Achievement ach) {
        //Bump fade times from seconds to ticks
        fadeIn *=20;
        stayTime *=20;
        fadeOut *=20;
    }

}
