package me.tehbeard.BeardAch.achievement.triggers.player;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.utils.misc.ItemSyntax;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorFieldType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentValueDescription;
import org.bukkit.inventory.ItemStack;

@ComponentHelpDescription(description = "Checks player is wearing an item, using <a href='http://wiki.sk89q.com/wiki/CraftBook/Item_Syntax'>CraftBook item syntax</a>", name = "Wearing item", type = ComponentType.TRIGGER)
@Configurable(name="wearingitem",tag="wearitem")
public class PlayerArmorCheckTrigger implements ITrigger {
    
    @ComponentValueDescription(description = "Item stack to look for in an armor slot")
    @Expose
    @EditorField(alias="Item String (CraftBook item syntax)",type=EditorFieldType.text)
    private String itemStr;
    
    @ComponentValueDescription(description = "Whether to ignore the durability (damage) of the item")
    @Expose
    @EditorField(alias="Ignore durability values",type= EditorFieldType.bool)
    private boolean ignoreDurability = false;
    
    @ComponentValueDescription(description = "Ignore metadata such as enchantments, lore etc")
    @Expose
    @EditorField(alias="Ignore metadata (Enchantments, Lore)",type= EditorFieldType.bool)
    private boolean ignoreMetadata = false;
    
    
    private ItemStack item;
    
    public void configure(Achievement ach, String config) {
        
    }

    public void configure(Achievement ach) {
         item = ItemSyntax.getItem(itemStr);
    }

    public boolean checkAchievement(Player player) {
        for(ItemStack is: player.getInventory().getArmorContents()){
            if(sameItem(is)){
                return true;
            }
        }
        return false;
    }

    public boolean sameItem(ItemStack stack){
        boolean sameId = item.getType() == stack.getType();
        boolean sameDurability = item.getDurability() == stack.getDurability() || ignoreDurability; 
        boolean sameMeta = item.hasItemMeta() == stack.hasItemMeta() && (item.hasItemMeta() ? Bukkit.getItemFactory().equals(item.getItemMeta(), stack.getItemMeta()) : true) || ignoreMetadata;
        
        return sameId && sameDurability && sameMeta;
    }
}
