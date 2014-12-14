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
import org.spongepowered.api.world.World;

@ComponentDescription(description = "Throws a lightning bolt at the player")
@Configurable(tag = "lightning", name = "Strike lightning on player")
public class LightningReward implements IReward {

    @ComponentFieldDescription(value = "If false, uses bukkit's fake lightning that does not injure a player")
    @Expose
    @EditorField(alias = "Real Lightning?", type = EditorFieldType.bool)
    private boolean real = false;

    @Override
    public void giveReward(Player player) {
        World world = player.getWorld();
//        if (real) {
//            world.strikeLightning(player.getLocation());
//        } else {
//            world.strikeLightningEffect(player.getLocation());
//        }
        throw new UnsupportedOperationException("NOT IMPLEMENTED");
    }

    @Override
    public void configure(Achievement ach) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED");
    }

}
