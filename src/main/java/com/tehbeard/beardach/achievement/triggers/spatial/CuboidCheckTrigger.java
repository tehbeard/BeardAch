package com.tehbeard.beardach.achievement.triggers.spatial;

import java.util.ArrayList;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;
import com.tehbeard.utils.cuboid.Cuboid;
import com.tehbeard.utils.sponge.SpongeUtils;

/**
 * Checks if a players is in a cuboid
 * 
 * @author James
 * 
 */
@ComponentDescription(description = "Is player inside an area",categories = "spatial")
@Configurable(tag = "cuboid", name = "cuboid")
public class CuboidCheckTrigger implements ITrigger {

    @ComponentFieldDescription(value = "Area to check")
    @Expose
    @EditorField(alias = "cuboid", type = EditorFieldType.cuboid)
    private Cuboid cuboid = new Cuboid();

    public Cuboid getCuboid() {
        return cuboid;
    }

    @Override
    public boolean checkAchievement(Player player) {
        // if player has stat
        // if(player.getWorld().getName().equals(world)){

        return cuboid.isInside(SpongeUtils.vectorToVec3(player.getLocation().getPosition()));
    }

    public ArrayList<String> getCache() {
        return cuboid.getChunks();
    }

    @Override
    public void configure(Achievement ach) {
        // Register with cuboid listener
        BeardAch.getListener().add(cuboid, ach);
    }

}
