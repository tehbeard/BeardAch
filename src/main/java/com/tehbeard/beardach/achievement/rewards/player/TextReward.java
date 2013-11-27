package com.tehbeard.beardach.achievement.rewards.player;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

@ComponentHelpDescription(description = "Display text to a player")
@Configurable(tag = "text", name = "Display text")
public class TextReward implements IReward {

    @ComponentValueDescription(description = "Text to display to a user")
    @Expose
    @EditorField(alias = "message", type = EditorFieldType.text)
    private String text;

    @Override
    public void configure(Achievement ach, String config) {
    }

    @Override
    public void giveReward(Player player) {
        player.sendMessage(text);
    }

    @Override
    public void configure(Achievement ach) {
    }
}
