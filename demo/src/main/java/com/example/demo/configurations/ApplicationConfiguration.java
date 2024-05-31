package com.example.demo.configurations;

import com.example.demo.cache.ScriptCache;
import com.example.demo.logging.advice.FeatureFlagAspect;
import com.example.demo.manager.ScriptManager;
import com.example.demo.model.Unleash;
import org.springframework.context.annotation.*;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;

import javax.script.ScriptEngineManager;

@Configuration
public class ApplicationConfiguration {

    @Bean(name="scriptCache")
    public ScriptCache scriptCache(){
        return new ScriptCache();
    }

    @Bean(name="engineManager")
    public ScriptEngineManager engineManager(){
        return new ScriptEngineManager();
    }

    @Bean(name="scriptManager")
    public ScriptManager scriptManager(){
        return new ScriptManager();
    }

    @Bean
    public Unleash unleash(){
        return new Unleash();
    }
}
