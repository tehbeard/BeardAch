package com.tehbeard.beardach.achievement.triggers.player;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;
import org.spongepowered.api.world.biome.BiomeType;

/**
 * Checks if a player has a permission node
 * 
 * @author James
 * 
 */
@ComponentHelpDescription(description = "Is player in a biome",categories ={"enviromental","player"})
@Configurable(tag = "biome", name = "Biome check")
public class BiomeCheckTrigger implements ITrigger {

    @ComponentValueDescription(value = "Biome to check for")
    @Expose
    @EditorField(alias = "Biome", type = EditorFieldType.selection, options = "org.bukkit.block.Biome")
    BiomeType biome;

    @Override
    public boolean checkAchievement(Player player) {
        return player.getLocation().getExtent().getBiome(player.getLocation().getPosition()).getType().equals(biome);
    }

    @Override
    public void configure(Achievement ach) {
    }
}
