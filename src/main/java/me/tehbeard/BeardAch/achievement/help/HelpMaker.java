package me.tehbeard.BeardAch.achievement.help;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang.ArrayUtils;

import me.tehbeard.BeardAch.BeardAch;

public class HelpMaker {
    
    public static final String T_TAG = "{tag}";
    public static final String T_ARGS = "{args}";
    public static final String T_DEPENDENCIES = "{depends}";
    public static final String T_BLURB = "{blurb}";
    public static final String T_PACKAGE = "{package}";
    
    private static String sectionTemplate;
    
    private static String pageTemplate;
    
    private static List<String> triggers = new ArrayList<String>();
    private static List<String> rewards  = new ArrayList<String>();
    
    public static void loadTemplates(){
        sectionTemplate = read("help\\section.html");
        pageTemplate    = read("help\\page.html");
    }
    
    public static void addTrigger(String tag,Usage usage){
        triggers.add(process(tag, usage));
    }
    
    public static void addReward(String tag,Usage usage){
        rewards.add(process(tag, usage));
    }
    
    private static String process(String tag,Usage usage){
        String work = new String(sectionTemplate);
        work.replace(T_TAG, tag);
        work.replace(T_ARGS, args(usage.arguments()));
        work.replace(T_DEPENDENCIES,depends(usage.dependencies()));
        work.replace(T_BLURB, usage.blurb());
        work.replace(T_PACKAGE, usage.packageName());
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
        Scanner s = new Scanner(BeardAch.self.getResource(file));
        String str = "";
        while(s.hasNext()){
            str += s.nextLine() + "\n";
        }
        return str;
    }
}
