package com.tehbeard.beardach.datasource.json.help;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;

public class HelpEntry {
    
    public String name;
    public String description;
    public String[] dependencies = {"none"};
    public String[] categories= {};
    public List<HelpFieldEntry> fields = new ArrayList<HelpFieldEntry>();

    public HelpEntry(Class<?> _class) {
        Configurable cfg = _class.getAnnotation(Configurable.class);
        ComponentDescription helpDesc = _class.getAnnotation(ComponentDescription.class);
        if(cfg == null || helpDesc == null){throw new IllegalArgumentException();}
        name = cfg.name();
        description = helpDesc.description();
        dependencies = helpDesc.dependencies().length > 0 ? helpDesc.dependencies() : dependencies;
        categories = helpDesc.categories();
        for(Field f : _class.getDeclaredFields()){
            EditorField ff = f.getAnnotation(EditorField.class);
            ComponentFieldDescription v = f.getAnnotation(ComponentFieldDescription.class);
            if(v == ff){continue;}
            fields.add(new HelpFieldEntry(ff.alias(), v != null ? v.value() : ""));
        }
        
    }
    
    
}
