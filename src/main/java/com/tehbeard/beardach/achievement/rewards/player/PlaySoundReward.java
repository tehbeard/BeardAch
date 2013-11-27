/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tehbeard.beardach.achievement.rewards.player;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

/**
 * 
 * @author James
 */
@ComponentHelpDescription(description = "plays a sound")
@Configurable(name = "play sound", tag = "playsnd")
public class PlaySoundReward implements IReward {

    @ComponentValueDescription(description = "Name of the sound to play (same as playsound command)")
    @EditorField(alias = "sound name", type = EditorFieldType.selection, options = "org.bukkit.Sound")
    @Expose
    Sound sound;

    @ComponentValueDescription(description = "Volume to play at")
    @EditorField(alias = "volume (decimal, 1.0 is normal)", type = EditorFieldType.text)
    @Expose
    float volume = 1.0f;

    @ComponentValueDescription(description = "pitch to play at")
    @EditorField(alias = "pitch (decimal, 1.0 is normal)", type = EditorFieldType.text)
    @Expose
    float pitch = 1.0f;

    @Override
    public void giveReward(Player player) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }

    @Override
    public void configure(Achievement ach, String config) {
    }

    @Override
    public void configure(Achievement ach) {
    }

}
