package com.tehbeard.beardach.achievement.triggers.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.AbstractEventTrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;
import com.tehbeard.utils.misc.ItemSyntax;

@ComponentHelpDescription(description = "Triggers when a player fishes an item, using <a href='http://wiki.sk89q.com/wiki/CraftBook/Item_Syntax'>CraftBook item syntax</a> for the fish")
@Configurable(name = "Fished item", tag = "fisheditem")
public class FishingTrigger extends AbstractEventTrigger{

    @ComponentValueDescription(value = "Item stack fished")
    @Expose
    @EditorField(alias = "Fished item String (CraftBook item syntax)", type = EditorFieldType.text)
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
        super.configure(ach);
        item = ItemSyntax.getItem(itemStr);
    }

    public boolean sameItem(ItemStack stack) {
        if(stack == null){return false;}
        boolean sameId = item.getType() == stack.getType();
        boolean sameDurability = item.getDurability() == stack.getDurability() || ignoreDurability;
        boolean sameMeta = item.hasItemMeta() == stack.hasItemMeta() && (item.hasItemMeta() ? Bukkit.getItemFactory().equals(item.getItemMeta(), stack.getItemMeta()) : true) || ignoreMetadata;

        return sameId && sameDurability && sameMeta;
    }

    @EventHandler(priority=EventPriority.MONITOR , ignoreCancelled=true)
    public void onFish(PlayerFishEvent event){
        if(event.getCaught() instanceof Item){
            if(sameItem(((Item)event.getCaught()).getItemStack())){
                runCheck(event.getPlayer());
            }
        }
    }


}
