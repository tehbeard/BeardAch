package me.tehbeard.BeardAch.achievement.rewards;

import java.util.HashMap;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorFieldType;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.gson.annotations.Expose;
import com.tehbeard.utils.misc.ItemSyntax;

@Configurable(name="Item reward",tag="item")
public class ItemReward implements IReward {
    
    @Expose
    @EditorField(alias="Item String (CraftBook syntax)",type=EditorFieldType.text)
    private String itemStr;
    
    @Expose
    @EditorField(alias="try placed in enderchest",type=EditorFieldType.bool)
    private boolean tryEnderChest = true;
    
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
            putIn(player.getEnderChest(),is);
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
