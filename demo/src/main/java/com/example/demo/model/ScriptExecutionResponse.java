package com.example.demo.model;

public class ScriptExecutionResponse {
    private Object result;

    public ScriptExecutionResponse (Object result){
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
