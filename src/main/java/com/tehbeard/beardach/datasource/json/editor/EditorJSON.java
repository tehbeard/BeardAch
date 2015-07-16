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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import org.spongepowered.api.CatalogType;

/**
 * Generates the json file for the achievements editor
 *
 * @author James
 *
 */
public class EditorJSON {

    public List<EditorElement> triggers = new ArrayList<EditorElement>();
    public List<EditorElement> rewards = new ArrayList<EditorElement>();

    public Map<String, HelpEntry> triggerHelp = new HashMap<String, HelpEntry>();
    public Map<String, HelpEntry> rewardHelp = new HashMap<String, HelpEntry>();

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
        addHelpEntry(t, triggerHelp);
    }

    public void addReward(Class<? extends IReward> r) {
        addItem(r, rewards);
        addHelpEntry(r, rewardHelp);
    }

    private void addHelpEntry(Class<?> _c, Map<String, HelpEntry> list) {
        try {
            list.put(_c.getAnnotation(Configurable.class).tag(), new HelpEntry(_c));
        } catch (Exception e) {
        }

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
                    if (a.type() == EditorFieldType.selection) {
                        List<String> options = new ArrayList<String>();
                        //Add hardwired strings
                        if (f.isAnnotationPresent(EditorFieldDefault.class)) {
                            options.addAll(Arrays.asList(f.getAnnotation(EditorFieldDefault.class).value()));
                        }
                        
                        //Add an enum
                        if (f.isAnnotationPresent(EditorFieldDefaultEnum.class)) {
                            @SuppressWarnings("unchecked")
                            Class<Enum<?>> enumClass = (Class<Enum<?>>) Class.forName(f.getAnnotation(EditorFieldDefaultEnum.class).value());
                            @SuppressWarnings("rawtypes")
                            Enum[] enums = (Enum[]) enumClass.getMethod("values").invoke(null);
                            for (int i = 0; i < enums.length; i++) {
                                options.add(enums[i].name());
                            }
                        }
                        
                        //Add a weird math based field thing
                        if (f.isAnnotationPresent(EditorFieldDefaultMethod.class)) {
                            EditorFieldDefaultMethod fieldData = f.getAnnotation(EditorFieldDefaultMethod.class);
                            @SuppressWarnings("unchecked")
                            Class<?> _class = Class.forName(fieldData.className());
                            List _list = (List) _class.getMethod(fieldData.listMethodName()).invoke(null);
                            for (Object o : _list) {
                                if(fieldData.nameMethod().length() == 0){
                                    options.add(o.getClass().toString()); // Use class name
                                }
                                else
                                {
                                options.add(o.getClass().getMethod(fieldData.nameMethod()).invoke(o).toString());
                                }
                            }
                        }
                        if(f.isAnnotationPresent(EditorFieldDefaultCatalog.class)){
                            EditorFieldDefaultCatalog fieldData = f.getAnnotation(EditorFieldDefaultCatalog.class);
                            Collection<? extends CatalogType> catalog = BeardAch.getGame().getRegistry().getAllOf(fieldData.value());
                            for (CatalogType catalogEntry : catalog) {
                                options.add(catalogEntry.getId());
                            }
                            
                        }
                        efe.values = options.toArray(new String[0]);
                    }

                    if (a.type() == EditorFieldType.number) {
                        if (f.isAnnotationPresent(EditorFieldMin.class)) {
                            efe.min = f.getAnnotation(EditorFieldMin.class).value() == Integer.MIN_VALUE ? false : f.getAnnotation(EditorFieldMin.class).value();
                        }
                        if (f.isAnnotationPresent(EditorFieldMax.class)) {
                            efe.max = f.getAnnotation(EditorFieldMax.class).value() == Integer.MAX_VALUE ? false : f.getAnnotation(EditorFieldMax.class).value();
                        }
                    }
                    ee.fields.add(efe);
                }
            }

            list.add(ee);
        } catch (NoClassDefFoundError e) {
            Logger.getLogger(EditorJSON.class.getName()).log(Level.WARNING, "Skipping item {0}, class not found.", ee.name);
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
        fw.write("baseDataset = ");
        JsonWriter writer = new JsonWriter(fw);
        writer.setIndent("  ");
        gson.toJson(this, TypeToken.get(EditorJSON.class).getType(), writer);
        writer.flush();
        fw.write(";");
        fw.flush();
        writer.close();
    }
}
