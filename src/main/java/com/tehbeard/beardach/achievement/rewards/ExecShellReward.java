package com.tehbeard.beardach.achievement.rewards;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.spongepowered.api.entity.Player;

@ComponentHelpDescription(description = "Executes a shell command, this reward IS DANGEROUS, AND MUST BE ENABLED VIA CONFIG (DEFAULTS TO OFF)")
@Configurable(tag = "execshell", name = "Execute shell command")
public class ExecShellReward implements IReward {

    @ComponentValueDescription(value = "Command to execute, <PLAYER> token replaced with player name")
    @Expose
    @EditorField(alias = "Command")
    String cmd = "";
    @ComponentValueDescription(value = "Environment variables, using var=value&var2=value2 format, use \\& and \\\\ for & and \\ respectively")
    @Expose
    @EditorField(alias = "Env. variables")
    String env = "";
    @ComponentValueDescription(value = "Working directory, will default to however bukkit was started.")
    @Expose
    @EditorField(alias = "Working directory")
    String workDir = "";
    private String[] envVars = null;
    private File workDirFile = null;

    @Override
    public void giveReward(Player player) {
        try {
            if (BeardAch.isAllowExecRewards()) {
                Runtime.getRuntime().exec(cmd.replaceAll("<PLAYER>", player.getName()), envVars, workDirFile);
            } else {
                BeardAch.instance().getLogger().warning("ExecShell reward called but disabled in config");
            }
        } catch (IOException ex) {
            Logger.getLogger(ExecShellReward.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void configure(Achievement ach) {
        if (workDir.length() > 0) {
            workDirFile = new File(workDir);
        }
        List<String> envList = new ArrayList<String>();

        StringBuilder sb = new StringBuilder();

        char[] arr = env.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char currentChar = arr[i];
            switch (currentChar) {
                case '&':
                    envList.add(sb.toString());
                    sb = new StringBuilder();
                    break;
                case '\\':
                    sb.append(arr[i + 1]);
                    break;
                default:
                    sb.append(currentChar);
                    break;
            }
        }

        envVars = envList.toArray(new String[0]);

    }
}
