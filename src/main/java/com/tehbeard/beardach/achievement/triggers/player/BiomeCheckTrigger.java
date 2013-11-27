package com.tehbeard.beardach.achievement.triggers.player;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;

import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import org.bukkit.block.Biome;

/**
 * Checks if a player has a permission node
 *
 * @author James
 *
 */
@ComponentHelpDescription(description = "Is player in a biome")
@Configurable(tag = "biome", name = "Biome check")
public class BiomeCheckTrigger implements ITrigger {

    @ComponentValueDescription(description = "Biome to check for")
    @Expose
    @EditorField(alias = "Biome",type = EditorFieldType.selection,options = "org.bukkit.block.Biome")
    Biome biome;

    @Override
    public void configure(Achievement ach, String config) {
    }

    @Override
    public boolean checkAchievement(Player player) {
        return player.getWorld().getBiome(player.getLocation().getBlockX(), player.getLocation().getBlockX()).equals(biome);
    }

    @Override
    public void configure(Achievement ach) {
    }
}
