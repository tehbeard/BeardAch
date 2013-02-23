package me.tehbeard.BeardAch.dataSource.json.editor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;


import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

public class EditorJSON {

	public List<EditorElement> triggers = new ArrayList<EditorElement>();
	public List<EditorElement> rewards = new ArrayList<EditorElement>();

	public class EditorElement{

		public List<EditorFormElement> fields = new ArrayList<EditorFormElement>();
		public String type;
		public String name;
	}

	public class EditorFormElement{
		public String key;
		public String name;
		public String type;
	}

	public void addTrigger(Class<? extends ITrigger> t){
		addItem(t,triggers);
	}
	public void addReward(Class<? extends IReward> r){
		addItem(r,rewards);
	}
	private void addItem(Class<?> c,List<EditorElement> list){
		EditorElement ee = new EditorElement();
		ee.name = c.getAnnotation(Configurable.class).tag();
		ee.type = ee.name;
		for(Field f : c.getFields()){
			EditorFormElement efe = new EditorFormElement();
			efe.key = f.getName();
			efe.name = efe.key;
			efe.type = "text";
			EditorField a = f.getAnnotation(EditorField.class);
			if(a != null){
				efe.name = a.alias();
				efe.type = a.type().toString().toLowerCase();
			}
			ee.fields.add(efe);
		}

		list.add(ee);
	}

	public void write(File file) throws IOException{
		JsonWriter writer = new JsonWriter(new FileWriter(file));
		Gson gson = new Gson();
		gson.toJson(this,new TypeToken<List<EditorJSON>>(){}.getType(),writer);
		writer.flush();
		writer.close();
	}

}
