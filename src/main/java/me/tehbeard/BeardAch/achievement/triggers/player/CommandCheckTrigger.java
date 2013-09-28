package me.tehbeard.BeardAch.achievement.triggers.player;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorFieldType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentValueDescription;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.google.gson.annotations.Expose;

@ComponentHelpDescription(description = "Triggers when a user attempts to execute a command", name = "on Command executed", type = ComponentType.TRIGGER)
@Configurable(name="On command",tag="oncommand")
public class CommandCheckTrigger implements ITrigger, Listener {

    @ComponentValueDescription(description="Certain commands use the preprocess event instead of Bukkit's command api, check this to allow those commands")
    @Expose
    @EditorField(alias="Include commands that have been cancelled by preprocess event",type=EditorFieldType.bool)
    private boolean includeIgnored = true;
    
    @ComponentValueDescription(description = "Use a regex expression instead of checking if command starts with")
    @Expose
    @EditorField(alias="Treat prefix as regex expression",type=EditorFieldType.bool)
    private boolean isRegex = false;
    
    @ComponentValueDescription(description = "command prefix or regex expression to check",examples={"(prefix) /region","(regex) \\/[a-z]protect"})
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
