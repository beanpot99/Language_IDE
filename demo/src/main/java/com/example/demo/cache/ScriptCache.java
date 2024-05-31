package com.example.demo.cache;

import com.example.demo.model.SavedScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ScriptCache {

    private final Object lockObject = new Object();
    private Map<String, SavedScript> scripts = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(ScriptCache.class);

    public void addScript(String languageName, SavedScript script){
        synchronized (lockObject) {
            scripts.put(languageName, script);
            LOGGER.info("Adding script to cache, size: " + scripts.size());
        }
    }

    public SavedScript getScript(String language){
        return this.scripts.get(language);
    }

    public void clearCache(){
        synchronized (lockObject){
            LOGGER.info("Scripts in cache before clear: " + scripts.size());
            this.scripts = new HashMap<>();
            LOGGER.info("Scripts in cache after clear: " + scripts.size());
        }
    }
}
