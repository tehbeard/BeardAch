package com.tehbeard.beardach.achievement.rewards.player;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldDefaultMethod;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import org.spongepowered.api.potion.PotionEffect;
import org.spongepowered.api.potion.PotionEffectType;

@ComponentDescription(description = "Applies a potion effect to a player")
@Configurable(tag = "potion", name = "Apply potion effect")
public class PotionReward implements IReward {

    @Expose
    @EditorField(alias = "Potion Type", type = EditorFieldType.selection)
    @EditorFieldDefaultMethod(className = "org.spongepowered.api.potion.PotionEffectTypes",nameMethod = "")
    private PotionEffectType potionType;
    
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
    public void giveReward(Player player) {
        if (effect != null) {
            effect.apply(player);
        }
    }

    @Override
    public void configure(Achievement ach) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED");
    }

}
