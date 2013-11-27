package com.tehbeard.beardach.achievement.triggers.player;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentType;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.triggers.AbstractEventTrigger;

@ComponentHelpDescription(description = "Triggers when a user attempts to execute a command", name = "on Command executed", type = ComponentType.TRIGGER)
@Configurable(name="On command",tag="oncommand")
public class CommandCheckTrigger extends AbstractEventTrigger {

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

    public void configure(Achievement ach, String config) {
        throw new UnsupportedOperationException("This trigger is not designed for old systems!");
    }

    public void configure(Achievement ach) {
        super.configure(ach);
        if(isRegex){
            pattern = Pattern.compile(commandText);
        }
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void commandPreprocess(PlayerCommandPreprocessEvent event){
        if(!event.isCancelled() || includeIgnored){
            if(isRegex){
                if(pattern.matcher(event.getMessage()).matches()){
                    add(event.getPlayer());
                    getAchievement().checkAchievement(event.getPlayer());
                    remove(event.getPlayer());
                }
            }
            else
            {
                if(event.getMessage().startsWith(commandText)){
                    add(event.getPlayer());
                    getAchievement().checkAchievement(event.getPlayer());
                    remove(event.getPlayer());
                }
            }
        }

    }

}
