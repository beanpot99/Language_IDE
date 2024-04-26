package com.example.demo.model;

import javax.script.CompiledScript;

public class SavedScript {
    private CompiledScript compiledScript;
    private String language;

    private String rawScript;

    public SavedScript(CompiledScript compiledScript, String language, String rawScript){
        this.compiledScript = compiledScript;
        this.language = language;
        this.rawScript = rawScript;
    }

    public CompiledScript getCompiledScript() {
        return compiledScript;
    }

    public void setCompiledScript(CompiledScript compiledScript) {
        this.compiledScript = compiledScript;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRawScript() {
        return rawScript;
    }

    public void setRawScript(String rawScript) {
        this.rawScript = rawScript;
    }
}
