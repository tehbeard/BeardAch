package me.tehbeard.BeardAch.achievement.help;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bukkit.util.FileUtil;

import me.tehbeard.BeardAch.BeardAch;

public class HelpMaker {
    
    public static final String T_TAG = "{tag}";
    public static final String T_ARGS = "{args}";
    public static final String T_DEPENDENCIES = "{depends}";
    public static final String T_BLURB = "{blurb}";
    public static final String T_PACKAGE = "{package}";
    
    public static final String T_TRIGGERS = "{trigger}";
    public static final String T_REWARDS  = "{reward}";
    
    private static String sectionTemplate;
    
    private static String pageTemplate;
    
    private static List<String> triggers = new ArrayList<String>();
    private static List<String> rewards  = new ArrayList<String>();
    
    public static void loadTemplates(){
        sectionTemplate = read("section.html");
        pageTemplate    = read("page.html");
        System.out.println(sectionTemplate);
        System.out.println(pageTemplate);
    }
    
    public static void addTrigger(String tag,Usage usage){
        triggers.add(process(tag, usage));
    }
    
    public static void addReward(String tag,Usage usage){
        rewards.add(process(tag, usage));
    }
    
    public static void writeHelp(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(BeardAch.self.getDataFolder(),"help.html")));
            String output = new String(pageTemplate);
            
            StringBuilder sb = new StringBuilder();
            for(String s : triggers){
                sb.append(s);
            }
            output = output.replace(T_TRIGGERS,sb.toString());
            
            sb = new StringBuilder();
            for(String s : rewards){
                sb.append(s);
            }
            output = output.replace(T_REWARDS,sb.toString());
            
            bw.write(output);
            bw.flush();
            bw.close();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private static String process(String tag,Usage usage){
        if(usage==null){return "";}
        String work = new String(sectionTemplate);
        work = work.replace(T_TAG, tag);
        work = work.replace(T_ARGS, args(usage.arguments()));
        work = work.replace(T_DEPENDENCIES,depends(usage.dependencies()));
        work = work.replace(T_BLURB, usage.blurb());
        work = work.replace(T_PACKAGE, usage.packageName());
        return work;
    }
    
    private static String depends(String[] dependencies) {
        String s = "";
        for(String ss : dependencies){
            if(s.length() > 0){s+=",";}
            s+=ss;
        }
        return s;
    }

    private static String args(Argument[] a){
        String s = "<ul>";
        for(Argument aa : a){
            s+="<li>" + aa.name() + " - " + aa.desc() + "</li>\n";
        }
        s+="</ul>";
        return s;
    }
    
    private static String read(String file){
        BufferedReader br = new BufferedReader(new InputStreamReader(BeardAch.self.getResource(file)));
        
        String str ="";
        String s = null;
        try {
            s = br.readLine();
            while(s!=null){
                str+=s;
                s = br.readLine() + "\n";
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (IOException e) {
            }
        }
        
        
        return str;
    }
}
