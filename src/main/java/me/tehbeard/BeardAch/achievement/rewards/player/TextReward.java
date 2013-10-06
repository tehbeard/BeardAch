package me.tehbeard.BeardAch.achievement.rewards.player;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorFieldType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentValueDescription;

@ComponentHelpDescription(description = "Display text to a player", name = "Display text", type = ComponentType.REWARD)
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
