package me.tehbeard.BeardAch.achievement.rewards.player;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.gson.annotations.Expose;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorFieldType;
import org.bukkit.potion.PotionType;

@ComponentHelpDescription(description = "Applies a potion effect to a player", name = "Apply potion", type = ComponentType.REWARD)
@Configurable(tag="potion",name="Apply potion effect")
public class PotionReward implements IReward {

    @Expose
    @EditorField(alias="Potion Type",type = EditorFieldType.selection,options = "org.bukkit.potion.PotionType")
    private PotionType potionType;
    @Expose
    @EditorField(alias="Amplifier")
    private int amplifier;
    @Expose
    @EditorField(alias="Duration")
    private int duration;
    
    @Expose
    @EditorField(alias="Ambient")
    private boolean ambient = false;
    
    private PotionEffect effect;
    
    public void configure(Achievement ach, String config) {
    }

    public void giveReward(Player player) {
        if(effect!=null){
            effect.apply(player);
        }
    }

    public void configure(Achievement ach) {
        PotionEffectType type = PotionEffectType.getByName(potionType.name());
        effect = new PotionEffect(type, duration, amplifier,ambient);
        
    }

}
