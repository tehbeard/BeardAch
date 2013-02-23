package me.tehbeard.BeardAch.dataSource.json;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

/**
 * Provides a string -> class mapping
 * @author James
 *
 * @param <T>
 */
public class ClassCatalogue<T> {
    
    private Map<String,Class<? extends T>> catalogue = new HashMap<String, Class<? extends T>>();
    
    public void addProduct(Class<? extends T> _class){
        catalogue.put(_class.getAnnotation(Configurable.class).tag(), _class);
    }
    
    public Class<? extends T> get(String tag){
        return catalogue.get(tag);
    }
    
    public Collection<String> getTags(){
        return catalogue.keySet();
    }
    
    public Collection<Class<? extends T>> getClasses(){
    	return catalogue.values();
    }
}
