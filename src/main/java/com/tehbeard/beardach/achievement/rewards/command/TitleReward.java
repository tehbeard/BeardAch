package com.tehbeard.beardach.achievement.rewards.command;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldDefault;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldMax;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldMin;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.title.Title;
import org.spongepowered.api.text.title.Titles;

@ComponentDescription(description = "Sends a player a /title message")
@Configurable(tag = "title", name = "Display /Title")
public class TitleReward implements IReward {

    @ComponentFieldDescription(value = "Title Text")
    @Expose
    @EditorField(alias = "Title Text")
    private String titleText = "";
    
    @ComponentFieldDescription(value = "Title Text")
    @Expose
    @EditorField(alias = "Title colour",type = EditorFieldType.selection)
    @EditorFieldDefault({"black", "dark_blue", "dark_green", "dark_aqua", "dark_red", "dark_purple", "gold", "gray", "dark_gray", "blue", "green", "aqua", "red", "light_purple", "yellow", "white"})
    private String titleColor = "white";
    
    @ComponentFieldDescription(value = "Subtitle Text")
    @Expose
    @EditorField(alias = "Subtitle Text")
    private String subtitleText = "";
    
    @ComponentFieldDescription(value = "Subtitle Text")
    @Expose
    @EditorField(alias = "Subtitle colour",type = EditorFieldType.selection)
    @EditorFieldDefault({"black", "dark_blue", "dark_green", "dark_aqua", "dark_red", "dark_purple", "gold", "gray", "dark_gray", "blue", "green", "aqua", "red", "light_purple", "yellow", "white"})
    private String subtitleColor = "white";
    
    
    @ComponentFieldDescription(value = "Fade in time")
    @Expose
    @EditorField(alias = "Fade in time",type = EditorFieldType.number)
    @EditorFieldMin(0)
    @EditorFieldMax(120)
    private int fadeIn = 1;
    
    @ComponentFieldDescription(value = "Stay time")
    @Expose
    @EditorField(alias = "Stay time",type = EditorFieldType.number)
    @EditorFieldMin(0)
    @EditorFieldMax(120)
    private int stayTime = 3;
    
    @ComponentFieldDescription(value = "Fade out time")
    @Expose
    @EditorField(alias = "Fade out time",type = EditorFieldType.number)
    @EditorFieldMin(0)
    @EditorFieldMax(120)
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
                .title(Texts.builder(titleText)
                        .color(BeardAch.getGame().getRegistry().getType(TextColor.class,titleColor).or(TextColors.WHITE))
                        .build()
                )
                .subtitle(Texts.builder(subtitleText)
                        .color(BeardAch.getGame().getRegistry().getType(TextColor.class,subtitleColor).or(TextColors.WHITE))
                        .build()
                )
                .build();
    }

}
