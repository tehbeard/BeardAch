package com.tehbeard.beardach.achievement.triggers.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;
import com.tehbeard.utils.misc.ItemSyntax;

@ComponentHelpDescription(description = "Checks player is wearing an item, using <a href='http://wiki.sk89q.com/wiki/CraftBook/Item_Syntax'>CraftBook item syntax</a>")
@Configurable(name = "wearingitem", tag = "wearitem")
public class PlayerArmorCheckTrigger implements ITrigger {

    @ComponentValueDescription(value = "Item stack to look for in an armor slot")
    @Expose
    @EditorField(alias = "Item String (CraftBook item syntax)", type = EditorFieldType.text)
    private String itemStr;

    @ComponentValueDescription(value = "Whether to ignore the durability (damage) of the item")
    @Expose
    @EditorField(alias = "Ignore durability values", type = EditorFieldType.bool)
    private boolean ignoreDurability = false;

    @ComponentValueDescription(value = "Ignore metadata such as enchantments, lore etc")
    @Expose
    @EditorField(alias = "Ignore metadata (Enchantments, Lore)", type = EditorFieldType.bool)
    private boolean ignoreMetadata = false;

    private ItemStack item;

    @Override
    public void configure(Achievement ach) {
        item = ItemSyntax.getItem(itemStr);
    }

    @Override
    public boolean checkAchievement(Player player) {
        for (ItemStack is : player.getInventory().getArmorContents()) {
            if (sameItem(is))
                return true;
        }
        return false;
    }

    public boolean sameItem(ItemStack stack) {
        boolean sameId = item.getType() == stack.getType();
        boolean sameDurability = item.getDurability() == stack.getDurability() || ignoreDurability;
        boolean sameMeta = item.hasItemMeta() == stack.hasItemMeta() && (item.hasItemMeta() ? Bukkit.getItemFactory().equals(item.getItemMeta(), stack.getItemMeta()) : true) || ignoreMetadata;

        return sameId && sameDurability && sameMeta;
    }
}