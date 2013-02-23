package me.tehbeard.BeardAch.dataSource.json.editor;

import java.util.ArrayList;
import java.util.List;

public class EditorJSON {

	
	
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
}
