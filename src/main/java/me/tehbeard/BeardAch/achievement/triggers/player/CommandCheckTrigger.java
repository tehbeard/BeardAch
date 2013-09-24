package me.tehbeard.BeardAch.achievement.triggers.player;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorFieldType;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.google.gson.annotations.Expose;

@Configurable(name="On command",tag="oncommand")
public class CommandCheckTrigger implements ITrigger, Listener {

    @Expose
    @EditorField(alias="Include commands that have been cancelled by preprocess event",type=EditorFieldType.bool)
    private boolean includeIgnored = true;
    @Expose
    @EditorField(alias="Treat prefix as regex expression",type=EditorFieldType.bool)
    private boolean isRegex = false;
    @Expose
    @EditorField(alias="Command prefix to detect",type=EditorFieldType.text)
    private String commandText = "";

    private Pattern pattern = null;

    private Set<String> active = new HashSet<String>();
    private Achievement ach;

    public void configure(Achievement ach, String config) {
        throw new UnsupportedOperationException("This trigger is not designed for old systems!");
    }

    public void configure(Achievement ach) {
        this.ach = ach;
        if(isRegex){
            pattern = Pattern.compile(commandText);
        }
    }

    public boolean checkAchievement(Player player) {
        return active.contains(player.getName());
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void commandPreprocess(PlayerCommandPreprocessEvent event){
        if(!event.isCancelled() || includeIgnored){
            if(isRegex){
                if(pattern.matcher(event.getMessage()).matches()){
                    active.add(event.getPlayer().getName());
                    ach.checkAchievement(event.getPlayer());
                    active.remove(event.getPlayer().getName());
                }
            }
            else
            {
                if(event.getMessage().startsWith(commandText)){
                    active.add(event.getPlayer().getName());
                    ach.checkAchievement(event.getPlayer());
                    active.remove(event.getPlayer().getName());
                }
            }
        }

    }

}
