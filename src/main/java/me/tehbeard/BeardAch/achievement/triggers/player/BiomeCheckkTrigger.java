package me.tehbeard.BeardAch.achievement.triggers.player;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentValueDescription;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
import org.bukkit.block.Biome;

/**
 * Checks if a player has a permission node
 *
 * @author James
 *
 */
@ComponentHelpDescription(description = "Is player in a biome", name = "Biome check", type = ComponentType.TRIGGER)
@Configurable(tag = "biome", name = "Biome check")
public class BiomeCheckkTrigger implements ITrigger {

    @ComponentValueDescription(description = "Biome to check for")
    @Expose
    @EditorField(alias = "Biome")
    String biome;

    public void configure(Achievement ach, String config) {
    }

    public boolean checkAchievement(Player player) {
        return player.getWorld().getBiome(player.getLocation().getBlockX(), player.getLocation().getBlockX()) == Biome.valueOf(biome);
    }

    public void configure(Achievement ach) {
    }
}
