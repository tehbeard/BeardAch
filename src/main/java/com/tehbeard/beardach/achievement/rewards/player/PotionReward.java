package com.tehbeard.beardach.achievement.rewards.player;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;

@ComponentHelpDescription(description = "Applies a potion effect to a player")
@Configurable(tag = "potion", name = "Apply potion effect")
public class PotionReward implements IReward {

    @Expose
    @EditorField(alias = "Potion Type", type = EditorFieldType.selection, options = "org.bukkit.potion.PotionType")
    private PotionType potionType;
    @Expose
    @EditorField(alias = "Amplifier")
    private int amplifier;
    @Expose
    @EditorField(alias = "Duration")
    private int duration;

    @Expose
    @EditorField(alias = "Ambient")
    private boolean ambient = false;

    private PotionEffect effect;

    @Override
    public void configure(Achievement ach, String config) {
    }

    @Override
    public void giveReward(Player player) {
        if (effect != null) {
            effect.apply(player);
        }
    }

    @Override
    public void configure(Achievement ach) {
        PotionEffectType type = PotionEffectType.getByName(potionType.name());
        effect = new PotionEffect(type, duration, amplifier, ambient);

    }

}
