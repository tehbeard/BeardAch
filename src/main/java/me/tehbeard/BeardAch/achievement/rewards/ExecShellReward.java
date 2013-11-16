package me.tehbeard.BeardAch.achievement.rewards;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentType;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentValueDescription;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.tehbeard.BeardAch.BeardAch;

@ComponentHelpDescription(description = "Executes a shell command, this reward IS DANGEROUS, AND MUST BE ENABLED VIA CONFIG (DEFAULTS TO OFF)", name = "Exec. Shell", type = ComponentType.REWARD)
@Configurable(tag = "execshell", name = "Execute shell command")
public class ExecShellReward implements IReward {
    
    @ComponentValueDescription(description = "Command to execute, <PLAYER> token replaced with player name")
    @Expose
    @EditorField(alias = "Command")
    String cmd = "";
    @ComponentValueDescription(description = "Environment variables, using var=value&var2=value2 format, use \\& and \\\\ for & and \\ respectively")
    @Expose
    @EditorField(alias = "Env. variables")
    String env = "";
    @ComponentValueDescription(description = "Working directory, will default to however bukkit was started.")
    @Expose
    @EditorField(alias = "Working directory")
    String workDir = "";
    private String[] envVars = null;
    private File workDirFile = null;
    
    @Override
    public void configure(Achievement Ach, String config) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void giveReward(Player player) {
        try {
            if(BeardAch.isAllowExecRewards()){
              Runtime.getRuntime().exec(cmd.replaceAll("<PLAYER>", player.getName()), envVars,workDirFile);
            }
            else
            {
                BeardAch.self.getLogger().warning("ExecShell reward called but disabled in config");
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
                    sb.append(arr[i+1]);
                    break;
                default:
                    sb.append(currentChar);
                    break;
            }
        }
        
        envVars = envList.toArray(new String[0]);
        
    }
}
