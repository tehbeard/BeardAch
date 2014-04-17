package com.tehbeard.beardach.datasource.json.editor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.help.HelpEntry;

/**
 * Generates the json file for the achievements editor
 * 
 * @author James
 * 
 */
public class EditorJSON {

    public List<EditorElement> triggers = new ArrayList<EditorElement>();
    public List<EditorElement> rewards = new ArrayList<EditorElement>();

    public Map<String,HelpEntry> triggerHelp = new HashMap<String,HelpEntry>();
    public Map<String,HelpEntry> rewardHelp = new HashMap<String,HelpEntry>();

    public class EditorElement {

        public List<EditorFormElement> fields = new ArrayList<EditorFormElement>();
        public String type;
        public String name;
    }

    /**
     * Structural class for elements of a trigger/reward
     * 
     * @author James
     * 
     */
    public class EditorFormElement {

        public String key;
        public String name;
        public String type;
        public String[] values = null;
        public Object min = false;
        public Object max = false;
    }

    public void addTrigger(Class<? extends ITrigger> t) {
        addItem(t, triggers);
        addHelpEntry(t,triggerHelp);
    }

    public void addReward(Class<? extends IReward> r) {
        addItem(r, rewards);
        addHelpEntry(r,rewardHelp);
    }

    private void addHelpEntry(Class<?> _c,Map<String,HelpEntry> list){
        try{
            list.put(_c.getAnnotation(Configurable.class).tag(),new HelpEntry(_c));
        }catch(Exception e){}

    }

    private void addItem(Class<?> c, List<EditorElement> list) {

        EditorElement ee = new EditorElement();
        ee.name = c.getAnnotation(Configurable.class).name();
        ee.type = c.getAnnotation(Configurable.class).tag();
        try {
            for (Field f : c.getDeclaredFields()) {
                if (!f.isAnnotationPresent(Expose.class)) {
                    continue;
                }
                EditorFormElement efe = new EditorFormElement();
                efe.key = f.getName();
                efe.name = efe.key;
                efe.type = "text";
                EditorField a = f.getAnnotation(EditorField.class);
                if (a != null) {
                    efe.name = a.alias();
                    efe.type = a.type().toString().toLowerCase();
                    //Parse selection
                    if(a.type() == EditorFieldType.selection){
                        if (a.options().length > 0) {
                            if (a.options().length == 1) {
                                @SuppressWarnings("unchecked")
                                Class<Enum<?>> enumClass = (Class<Enum<?>>) Class.forName(a.options()[0]);
                                @SuppressWarnings("rawtypes")
                                Enum[] enums = (Enum[]) enumClass.getMethod("values").invoke(null);
                                String[] options = new String[enums.length];
                                for (int i = 0; i < enums.length; i++) {
                                    options[i] = enums[i].name();
                                }
                                efe.values = options;
                            } else {
                                efe.values = a.options();
                            }
                        }
                    }
                    
                    if(a.type() == EditorFieldType.number){
                        efe.min = a.min() == Integer.MIN_VALUE ? false : a.min();
                        efe.max = a.max() == Integer.MAX_VALUE ? false : a.max();
                    }
                }
                ee.fields.add(efe);
            }

            list.add(ee);
        } catch (NoClassDefFoundError e) {
            BeardAch.instance().getLogger().log(Level.WARNING, "Skipping item {0}, class not found.", ee.name);
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditorJSON.class.getName()).log(Level.SEVERE, "Failed to load a class needed by " + ee.name, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(EditorJSON.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(EditorJSON.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(EditorJSON.class.getName()).log(Level.SEVERE, "No method found " + ee.name, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(EditorJSON.class.getName()).log(Level.SEVERE, "Security exception" + ee.name, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EditorJSON.class.getName()).log(Level.SEVERE, "Illegal access exception " + ee.name, ex);
        }
    }

    public void write(File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        FileWriter fw = new FileWriter(file);
        fw.write("$(function(){initConfig(");
        JsonWriter writer = new JsonWriter(fw);
        writer.setIndent("  ");
        gson.toJson(this, TypeToken.get(EditorJSON.class).getType(), writer);
        writer.flush();
        fw.write(");});");
        fw.flush();
        writer.close();
    }
}

