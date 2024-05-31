package com.example.demo.service;

import com.example.demo.cache.ScriptCache;
import com.example.demo.logging.annotations.FeatureFlag;
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
    private ScriptManager scriptManager = new ScriptManager();
    private static final Logger LOGGER = LoggerFactory.getLogger(LanguagePickerService.class);

    public ScriptSaveResponse saveScript(Script script){
        try{
            LOGGER.info("Saving script: " + script.getScript());
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

    public ScriptExecutionResponse runScript(Script script){
        Object executionResponse = null;
        try{
            this.saveScript(script);
            LOGGER.info("Running script with language: " + script.getLanguage());
            SavedScript cachedScript = scriptCache.getScript(script.getLanguage());
            if(cachedScript != null){
                executionResponse = scriptManager.evaluateScript(cachedScript.getCompiledScript(), script.getLanguage());
            }
        } catch (Exception e){
            LOGGER.error(Statuses.RUN_ERROR);
            executionResponse = e.getMessage();
        }
        return new ScriptExecutionResponse(executionResponse);
    }

    public SavedScript getScript(String language){
        LOGGER.info("Script found: " + scriptCache.getScript(language).getRawScript());
        LOGGER.info("Retrieving script for language: " + language);
        return scriptCache.getScript(language);
    }

    public Object testMethod(){
        testMethodBAU();
        testMethodNew();
        return "";
    }
    @FeatureFlag(flagName = "my-feature-flag", execute = true)
    //@FeatureFlag
    public Object testMethodBAU(){
        System.out.println("BAU METHOD RAN!!!!!!!!!!!!!");
        return "";
    }

    @FeatureFlag(flagName = "my-feature-flag", execute = false)
    //@FeatureFlag
    public Object testMethodNew(){
        System.out.println("NEWWWWWW METHOD RAN!!!!!!!!!!!!!");
        return "";
    }
}
