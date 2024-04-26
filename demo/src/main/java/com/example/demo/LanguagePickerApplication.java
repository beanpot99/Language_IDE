package com.example.demo;

import com.example.demo.cache.ScriptCache;
import com.example.demo.manager.ScriptManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.script.ScriptEngineManager;

@SpringBootApplication
public class LanguagePickerApplication {

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

	public static void main(String[] args) {

		SpringApplication.run(LanguagePickerApplication.class, args);
	}

}
