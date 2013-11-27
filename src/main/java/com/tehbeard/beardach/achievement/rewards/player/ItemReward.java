package com.tehbeard.beardach.achievement.rewards.player;

import java.util.HashMap;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;

import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.gson.annotations.Expose;
import com.tehbeard.utils.misc.ItemSyntax;

@ComponentHelpDescription(description = "Gives a player an item, using <a href='http://wiki.sk89q.com/wiki/CraftBook/Item_Syntax'>CraftBook item syntax</a>")
@Configurable(name="Item reward",tag="item")
public class ItemReward implements IReward {
    
    @Expose
    @EditorField(alias="Item String (CraftBook item syntax)",type=EditorFieldType.text)
    private String itemStr;
    
    @ComponentValueDescription(description="Attempts to place the item in the users enderchest if ticked")
    @Expose
    @EditorField(alias="try placed in enderchest",type=EditorFieldType.bool)
    private boolean tryEnderChest = true;
    
    @ComponentValueDescription(description="Attempts to drop the item in world if inventory/s are full")
    @Expose
    @EditorField(alias="drop item in world",type=EditorFieldType.bool)
    private boolean tryDrop = true;
    
    private ItemStack item;

    @Override
    @Deprecated
    public void configure(Achievement ach, String config) {
    }

    @Override
    public void configure(Achievement ach) {
       item = ItemSyntax.getItem(itemStr);
    }

    @Override
    public void giveReward(Player player) {
        
        ItemStack is = putIn(player.getInventory(),item);
        
        if(tryEnderChest){
            is = putIn(player.getEnderChest(),is);
        }
        
        if(tryDrop && is != null){
            player.getWorld().dropItemNaturally(player.getLocation(), is);
        }
    }
    
    private ItemStack putIn(Inventory inv,ItemStack is){
        if(is == null){return null;}
         HashMap<Integer, ItemStack> res = inv.addItem(is);
        if(res.size() > 0){
            return  res.get(0);
        }
        return null;
    }

}
