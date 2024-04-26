package com.example.demo.service;

import com.example.demo.cache.ScriptCache;
import com.example.demo.manager.ScriptManager;
import com.example.demo.model.SavedScript;
import com.example.demo.model.Script;
import com.example.demo.model.ScriptExecutionResponse;
import com.example.demo.model.ScriptSaveResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.util.Statuses;

import javax.script.CompiledScript;

@Service
public class LanguagePickerService {
    @Autowired
    private ScriptCache scriptCache;

    @Autowired
    private ScriptManager scriptManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(LanguagePickerService.class);

    public ScriptSaveResponse saveScript(Script script){
        try{
            CompiledScript compiledScript = scriptManager.compileScript(script);
            LOGGER.info("Saving script with language: " + script.getLanguage());
            scriptCache.addScript(script.getLanguage(), new SavedScript(compiledScript, script.getLanguage(), script.getScript()));
            LOGGER.info(Statuses.SAVE_SUCCESS);
            return new ScriptSaveResponse(Statuses.SAVE_SUCCESS);
        } catch (Exception e){
            LOGGER.error(Statuses.SAVE_ERROR);
            return new ScriptSaveResponse(Statuses.SAVE_ERROR);
        }
    }

    public ScriptExecutionResponse runScript(String language){
        Object executionResponse = null;
        try{
            LOGGER.info("Running script with language: " + language);
            SavedScript cachedScript = scriptCache.getScript(language);
            if(cachedScript != null){
                executionResponse = scriptManager.evaluateScript(cachedScript.getCompiledScript(), language);
            }
        } catch (Exception e){
            LOGGER.error(Statuses.RUN_ERROR);
            executionResponse = e.getMessage();
        }
        return new ScriptExecutionResponse(executionResponse);
    }

    public SavedScript getScript(String language){
        LOGGER.info("Retrieving script for language: " + language);
        return scriptCache.getScript(language);
    }
}
