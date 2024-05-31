package com.example.demo.controller;

import com.example.demo.logging.annotations.FeatureFlag;
import com.example.demo.model.SavedScript;
import com.example.demo.model.Script;
import com.example.demo.model.ScriptExecutionResponse;
import com.example.demo.model.ScriptSaveResponse;
import com.example.demo.service.LanguagePickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class LanguagePickerController {

    @Autowired
    LanguagePickerService languageService;

    @RequestMapping(produces = "application/json", value={"/v1/saveScript"}, method= RequestMethod.POST)
    public ResponseEntity<ScriptSaveResponse> saveScript(@RequestBody Script script){
        return ResponseEntity.ok(languageService.saveScript(script));
    }

    @RequestMapping(produces = "application/json", value={"/v1/runScript"}, method= RequestMethod.POST)
    public ResponseEntity<ScriptExecutionResponse> runScript(@RequestBody Script script){
        ScriptExecutionResponse response = languageService.runScript(script);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value={"/v1/getScript"})
    public ResponseEntity<SavedScript> getScript(@RequestParam(name="language") String language){
        return ResponseEntity.ok(languageService.getScript(language));
    }
    @GetMapping(value={"/v1/runTestMethod"})
    public ResponseEntity<Object> testMethod(){
        testPrivate();
        return ResponseEntity.ok(languageService.testMethod());
    }

    @FeatureFlag(flagName = "flag-2", execute = true)
    private void testPrivate(){
        System.out.println("The private method ran!");
    }
}
