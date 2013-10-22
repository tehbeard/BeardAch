package me.tehbeard.BeardAch.achievement.rewards.player;

import java.util.HashMap;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorFieldType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.gson.annotations.Expose;
import com.tehbeard.utils.misc.ItemSyntax;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

@ComponentHelpDescription(description = "Launches a firework, using <a href='http://wiki.sk89q.com/wiki/CraftBook/Item_Syntax'>CraftBook item syntax</a>", name = "Launch firework", type = ComponentType.REWARD)
@Configurable(name = "Launch firework", tag = "firework")
public class FireworkReward implements IReward {

    @Expose
    @EditorField(alias = "Firework String (CraftBook item syntax)", type = EditorFieldType.text)
    private String itemStr;
    private FireworkMeta meta;

    @Override
    @Deprecated
    public void configure(Achievement ach, String config) {
    }

    @Override
    public void configure(Achievement ach) {
        ItemStack item = ItemSyntax.getItem(itemStr);
        if (item.getItemMeta() instanceof FireworkMeta == false) {
            throw new IllegalStateException("Invalid item provided to firework reward for achievement " + ach.getSlug() + " :: " + ach.getName());
        } else {
            meta = (FireworkMeta) item.getItemMeta();
        }
    }

    @Override
    public void giveReward(Player player) {
        ((Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK)).setFireworkMeta(meta);
    }
}
