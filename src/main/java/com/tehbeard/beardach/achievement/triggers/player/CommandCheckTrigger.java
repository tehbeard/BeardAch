package com.tehbeard.beardach.achievement.triggers.player;

import java.util.regex.Pattern;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.AbstractEventTrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentFieldDescription;

@ComponentDescription(description = "Triggers when a user attempts to execute a command",categories ={"evented","player"})
@Configurable(name = "On command", tag = "oncommand")
public class CommandCheckTrigger extends AbstractEventTrigger {

    @ComponentFieldDescription(value = "Certain commands use the preprocess event instead of Bukkit's command api, check this to allow those commands")
    @Expose
    @EditorField(alias = "Include commands that have been cancelled by preprocess event", type = EditorFieldType.bool)
    private boolean includeIgnored = true;

    @ComponentFieldDescription(value = "Use a regex expression instead of checking if command starts with")
    @Expose
    @EditorField(alias = "Treat prefix as regex expression", type = EditorFieldType.bool)
    private boolean isRegex = false;

    @ComponentFieldDescription(value = "command prefix or regex expression to check")
    @Expose
    @EditorField(alias = "Command prefix to detect", type = EditorFieldType.text)
    private String commandText = "";
    
    
    @ComponentFieldDescription("Set this true to prevent the command executing.")
    @Expose
    @EditorField(alias = "Stop command being processed by bukkit",type=EditorFieldType.bool)
    private boolean cancelCommand = false;

    private Pattern pattern = null;

    @Override
    public void configure(Achievement ach) {
        super.configure(ach);
        if (isRegex) {
            pattern = Pattern.compile(commandText);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void commandPreprocess(PlayerCommandPreprocessEvent event) {
        if (!event.isCancelled() || includeIgnored) {
            if (isRegex) {
                if (pattern.matcher(event.getMessage()).matches()) {
                    runCheck(event.getPlayer());
                }
            } else {
                if (event.getMessage().startsWith(commandText)) {
                    runCheck(event.getPlayer());
                }
            }
            if(cancelCommand){event.setCancelled(true);}
        }

    }

}
