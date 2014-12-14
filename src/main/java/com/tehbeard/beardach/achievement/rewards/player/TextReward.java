package com.tehbeard.beardach.achievement.rewards.player;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;

@ComponentDescription(description = "Display text to a player")
@Configurable(tag = "text", name = "Display text")
public class TextReward implements IReward {

    @ComponentFieldDescription(value = "Text to display to a user")
    @Expose
    @EditorField(alias = "message", type = EditorFieldType.text)
    private String text;

    @Override
    public void giveReward(Player player) {
        player.sendMessage(text);
    }

    @Override
    public void configure(Achievement ach) {
    }
}
