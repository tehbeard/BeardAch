package com.tehbeard.beardach.achievement.triggers.player;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;
import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldDefaultCatalog;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;
import org.spongepowered.api.GameRegistry;
import org.spongepowered.api.world.biome.BiomeType;

/**
 * Checks if a player has a permission node
 * 
 * @author James
 * 
 */
@ComponentDescription(description = "Is player in a biome",categories ={"enviromental","player"})
@Configurable(tag = "biome", name = "Biome check")
public class BiomeCheckTrigger implements ITrigger {

    @ComponentFieldDescription(value = "Biome to check for")
    @Expose
    @EditorField(alias = "Biome", type = EditorFieldType.selection)
    @EditorFieldDefaultCatalog(BiomeType.class)
    BiomeType biome;

    @Override
    public boolean checkAchievement(Player player) {
        
        return player.getLocation().getBiome().getId().equals(biome);
    }

    @Override
    public void configure(Achievement ach) {
    }
}
