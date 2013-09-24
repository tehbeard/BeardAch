package me.tehbeard.BeardAch.achievement.rewards;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorFieldType;

@Configurable(tag="lightning",name="Strike lightning on player")
public class LightningReward implements IReward {

    @Expose
    @EditorField(alias="Real Lightning?",type=EditorFieldType.bool)
    private boolean real = false;
    
    public void configure(Achievement ach, String config) {
        if(config.equalsIgnoreCase("true")){
            real = true;
        }
        
        if(config.equalsIgnoreCase("false")){
            real = false;
        }
        throw new IllegalArgumentException("invalid value for lightning");
    }

    public void giveReward(Player player) {
        World world = player.getWorld();
        if(real){
            world.strikeLightning(player.getLocation());
        }else{
            world.strikeLightningEffect(player.getLocation());
        }
        
    }

	public void configure(Achievement ach) {
		// TODO Auto-generated method stub
		
	}

     
}
