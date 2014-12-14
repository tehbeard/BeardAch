/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tehbeard.beardach.achievement.rewards.player;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;
import org.spongepowered.api.effect.Sound;

/**
 * 
 * @author James
 */
@ComponentDescription(description = "plays a sound")
@Configurable(name = "play sound", tag = "playsnd")
public class PlaySoundReward implements IReward {

    @ComponentFieldDescription(value = "Name of the sound to play (same as playsound command)")
    @EditorField(alias = "sound name", type = EditorFieldType.selection, options = "org.bukkit.Sound")
    @Expose
    Sound sound;

    @ComponentFieldDescription(value = "Volume to play at")
    @EditorField(alias = "volume (decimal, 1.0 is normal)", type = EditorFieldType.text)
    @Expose
    float volume = 1.0f;

    @ComponentFieldDescription(value = "pitch to play at")
    @EditorField(alias = "pitch (decimal, 1.0 is normal)", type = EditorFieldType.text)
    @Expose
    float pitch = 1.0f;

    @Override
    public void giveReward(Player player) {
        player.playSound(sound,player.getLocation().getPosition() , volume, pitch);
    }

    @Override
    public void configure(Achievement ach) {
    }

}
