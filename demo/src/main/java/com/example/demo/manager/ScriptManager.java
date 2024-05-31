package com.example.demo.manager;

import com.example.demo.model.Script;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.script.*;
import java.util.HashMap;
import java.util.Map;

public class ScriptManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScriptManager.class);

    private Map<String, ScriptEngine> engines = new HashMap<>();
    @Autowired
    ScriptEngineManager engineManager;

    public CompiledScript compileScript(Script script){
        if(Strings.isBlank(script.getScript())){
            return null;
        }

        ScriptEngine engine = this.getScriptEngine(script.getLanguage());

        if(engine == null){
            LOGGER.error("Unable to find script engine for corresponding language");
        }

        CompiledScript compiledScript = null;
        Compilable compilableEngine = (Compilable) engine;

        try {
            compiledScript = compilableEngine.compile(script.getScript());
        } catch(Exception e){
            LOGGER.error("Error occurred while compiling script: " + e.getMessage());
        }
            return compiledScript;
    }

    public Object evaluateScript(CompiledScript compiledScript, String language){
        Object response = null;
        try{
            SimpleBindings bindings = new SimpleBindings();
            response = compiledScript.eval(bindings);
        } catch(Exception e){
            LOGGER.error("Error occurred while evaluating script: " + e.getMessage());
            response = e.getMessage();
        }
        return response;
    }

    public ScriptEngine getScriptEngine(String language){
        ScriptEngine scriptEngine = null;
        LOGGER.info(String.valueOf(this.engineManager.getEngineFactories().size()) + ": SIZE");
        for( ScriptEngineFactory factory : this.engineManager.getEngineFactories()){
            LOGGER.info("Engines available: " + factory.getEngineName());
        }
        if(Strings.isNotBlank(language)){
            if(engines.get(language) != null){
                scriptEngine = engines.get(language);
            } else {
                scriptEngine = this.engineManager.getEngineByName(language);
                if(scriptEngine != null){
                    this.engines.put(language,scriptEngine);
                }
            }
        }
        return scriptEngine;
    }
}
