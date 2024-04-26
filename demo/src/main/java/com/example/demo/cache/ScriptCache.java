package com.example.demo.cache;

import com.example.demo.model.SavedScript;
import java.util.HashMap;
import java.util.Map;

public class ScriptCache {

    private final Object lockObject = new Object();
    private Map<String, SavedScript> scripts = new HashMap<>();

    public void addScript(String languageName, SavedScript script){
        synchronized (lockObject) {
            scripts.put(languageName, script);
        }
    }

    public SavedScript getScript(String language){
        return this.scripts.get(language);
    }

    public void clearCache(){
        synchronized (lockObject){
            this.scripts = new HashMap<>();
        }
    }
}
