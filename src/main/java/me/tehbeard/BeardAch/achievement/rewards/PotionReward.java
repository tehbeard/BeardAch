package me.tehbeard.BeardAch.achievement.rewards;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Configurable(tag="potion")
public class PotionReward implements IReward {

    private PotionEffect effect;
    public void configure(Achievement ach, String config) {
        String[] c = config.split(":");
        if(c.length!=3){throw new IllegalArgumentException("");}
        PotionEffectType type = PotionEffectType.getByName(c[0].toUpperCase());
        int amplifier = Integer.parseInt(c[1]);
        int duration = Integer.parseInt(c[2]) * 20;
        
        effect = new PotionEffect(type, duration, amplifier);

    }

    public void giveReward(Player player) {
        effect.apply(player);
        
    }

}
