package com.tehbeard.beardach.achievement.rewards.player;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.utils.misc.ItemSyntax;

@ComponentHelpDescription(description = "Launches a firework, using <a href='http://wiki.sk89q.com/wiki/CraftBook/Item_Syntax'>CraftBook item syntax</a>")
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
        if (item.getItemMeta() instanceof FireworkMeta == false)
            throw new IllegalStateException("Invalid item provided to firework reward for achievement " + ach.getSlug() + " :: " + ach.getName());
        else {
            meta = (FireworkMeta) item.getItemMeta();
        }
    }

    @Override
    public void giveReward(Player player) {
        ((Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK)).setFireworkMeta(meta);
    }
}
