package me.tehbeard.BeardAch.achievement.rewards.player;

import org.bukkit.World;
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

@ComponentHelpDescription(description = "Throws a lightning bolt at the player", name = "Lightning", type = ComponentType.REWARD)
@Configurable(tag="lightning",name="Strike lightning on player")
public class LightningReward implements IReward {

    @ComponentValueDescription(description="If false, uses bukkit's fake lightning that does not injure a player")
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
