package com.example.demo.model;

public class ScriptSaveResponse {
    private String status;

    public ScriptSaveResponse(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
