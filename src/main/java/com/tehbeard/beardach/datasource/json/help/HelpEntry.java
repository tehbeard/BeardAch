package com.tehbeard.beardach.datasource.json.help;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;

public class HelpEntry {
    
    private String name;
    private String description;
    private String[] dependencies = {"none"};
    public transient String[] categories= {};
    private List<HelpFieldEntry> fields = new ArrayList<HelpFieldEntry>();
    /**
     * @param id
     * @param name
     * @param description
     * @param dependencies
     * @param fields
     */
    public HelpEntry(Class<?> _class) {
        Configurable cfg = _class.getAnnotation(Configurable.class);
        ComponentHelpDescription helpDesc = _class.getAnnotation(ComponentHelpDescription.class);
        if(cfg == null || helpDesc == null){throw new IllegalArgumentException();}
        name = cfg.name();
        description = helpDesc.description();
        dependencies = helpDesc.dependencies().length > 0 ? helpDesc.dependencies() : dependencies;
        categories = helpDesc.categories();
        for(Field f : _class.getDeclaredFields()){
            EditorField ff = f.getAnnotation(EditorField.class);
            ComponentValueDescription v = f.getAnnotation(ComponentValueDescription.class);
            if(v == ff){continue;}
            fields.add(new HelpFieldEntry(ff.alias(), v != null ? v.value() : ""));
        }
        
    }
    
    
}
