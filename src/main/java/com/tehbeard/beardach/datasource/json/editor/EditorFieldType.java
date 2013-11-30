package com.tehbeard.beardach.datasource.json.editor;

/**
 * Declares the type of interface to use in the editor for a plugin's parameter
 * 
 * @author James
 * 
 */
public enum EditorFieldType {
    text("Text"), bool("Boolean"), location("Location"), cuboid("Cuboid"), trigger("Trigger list"), selection("Selection"),number("Number");

    public final String label;

    private EditorFieldType(String label) {
        this.label = label;
    }
}
