package me.tehbeard.BeardAch.dataSource.configurable;

import java.util.HashMap;
import java.util.Map;

import me.tehbeard.BeardAch.achievement.IConfigurable;

public class ConfigurableFactory {
	private Map<String,Class<? extends IConfigurable>> parts;

	public ConfigurableFactory(){
		parts = new HashMap<String, Class<? extends IConfigurable>>();
	}

	public void addPart(Class<? extends IConfigurable> part){
		Configurable tag = part.getAnnotation(Configurable.class);
		if(tag!=null){
			parts.put(tag.tag(),part);
		}
	}

	public IConfigurable getPart(String tag,String config){
		if(parts.containsKey(tag)){
			try {
				IConfigurable object = parts.get(tag).newInstance();
				object.configure(config);
				return object;
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return null;
	}

}
