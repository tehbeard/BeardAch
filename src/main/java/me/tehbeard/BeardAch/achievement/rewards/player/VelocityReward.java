package me.tehbeard.BeardAch.achievement.rewards.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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
import org.bukkit.util.Vector;

@ComponentHelpDescription(description = "Apply a force to a player", name = "Apply force", type = ComponentType.REWARD)
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
    public void configure(Achievement ach, String config) {
    }

    @Override
    public void giveReward(Player player) {
        player.setVelocity(new Vector(x, y, z));
    }

    @Override
    public void configure(Achievement ach) {
    }
}
