package com.tehbeard.beardach.achievement.rewards.player;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

@ComponentHelpDescription(description = "Apply a force to a player")
@Configurable(tag = "applyforce", name = "Apply force to player")
public class VelocityReward implements IReward {

    @ComponentValueDescription(description = "force along x  axis to apply")
    @Expose
    @EditorField(alias = "X", type = EditorFieldType.text)
    private float x;
    @ComponentValueDescription(description = "force along y axis to apply")
    @Expose
    @EditorField(alias = "Y", type = EditorFieldType.text)
    private float y;
    @ComponentValueDescription(description = "force along z axis to apply")
    @Expose
    @EditorField(alias = "Z", type = EditorFieldType.text)
    private float z;

    @Override
    public void giveReward(Player player) {
        player.setVelocity(new Vector(x, y, z));
    }

    @Override
    public void configure(Achievement ach) {
    }
}
