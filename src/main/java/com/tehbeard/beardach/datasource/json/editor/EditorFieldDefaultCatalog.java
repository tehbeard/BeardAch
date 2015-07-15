package com.tehbeard.beardach.datasource.json.editor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.spongepowered.api.CatalogType;

@Retention(RetentionPolicy.RUNTIME)
public @interface EditorFieldDefaultCatalog {
    Class<? extends CatalogType> value();
}
