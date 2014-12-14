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
import org.spongepowered.api.math.Vectors;

@ComponentDescription(description = "Apply a force to a player")
@Configurable(tag = "applyforce", name = "Apply force to player")
public class VelocityReward implements IReward {

    @ComponentFieldDescription(value = "force along x  axis to apply")
    @Expose
    @EditorField(alias = "X", type = EditorFieldType.text)
    private float x;
    @ComponentFieldDescription(value = "force along y axis to apply")
    @Expose
    @EditorField(alias = "Y", type = EditorFieldType.text)
    private float y;
    @ComponentFieldDescription(value = "force along z axis to apply")
    @Expose
    @EditorField(alias = "Z", type = EditorFieldType.text)
    private float z;

    @Override
    public void giveReward(Player player) {
        //player.setVelocity( Vectors.create3d(x, y, z));
        
    }

    @Override
    public void configure(Achievement ach) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED");
    }
}
