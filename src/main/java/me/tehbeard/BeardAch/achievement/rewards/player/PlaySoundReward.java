/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tehbeard.BeardAch.achievement.rewards.player;

import com.google.gson.annotations.Expose;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorFieldType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentValueDescription;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 *
 * @author James
 */
@ComponentHelpDescription(name = "Play sound",description = "plays a sound",type = ComponentType.REWARD)
@Configurable(name = "play sound",tag = "playsnd")
public class PlaySoundReward implements IReward{
    
    @ComponentValueDescription(description = "Name of the sound to play (same as playsound command)")
    @EditorField(alias = "sound name",type = EditorFieldType.selection,options = "org.bukkit.Sound")
    @Expose
    Sound sound;
    
    @ComponentValueDescription(description = "Volume to play at")
    @EditorField(alias = "volume (decimal, 1.0 is normal)",type = EditorFieldType.text)
    @Expose
    float volume = 1.0f;
    
    @ComponentValueDescription(description = "pitch to play at")
    @EditorField(alias = "pitch (decimal, 1.0 is normal)",type = EditorFieldType.text)
    @Expose
    float pitch = 1.0f;

    @Override
    public void giveReward(Player player) {
        player.playSound(player.getLocation(),sound, volume, pitch);
    }

    @Override
    public void configure(Achievement ach, String config) {
    }

    @Override
    public void configure(Achievement ach) {
    }
    
}
