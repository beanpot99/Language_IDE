package com.example.demo.model;

public class Script {
    private String script;
    private String language;

    public Script(String script, String language){
        this.language = language;
        this.script = script;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
