package com.example.demo.controller;

import com.example.demo.model.SavedScript;
import com.example.demo.model.Script;
import com.example.demo.model.ScriptExecutionResponse;
import com.example.demo.model.ScriptSaveResponse;
import com.example.demo.service.LanguagePickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LanguagePickerController {

    @Autowired
    LanguagePickerService languageService;

    @RequestMapping(produces = "application/json", value={"/v1/saveScript"}, method= RequestMethod.POST)
    public ResponseEntity<ScriptSaveResponse> saveScript(@RequestBody Script script){
        return ResponseEntity.ok(languageService.saveScript(script));
    }

    @GetMapping(value={"/v1/runScript"})
    public ResponseEntity<ScriptExecutionResponse> runScript(@RequestParam(name="language") String language){
        return ResponseEntity.ok(languageService.runScript(language));
    }

    @GetMapping(value={"/v1/getScript"})
    public ResponseEntity<SavedScript> getScript(@RequestParam(name="language") String language){
        return ResponseEntity.ok(languageService.getScript(language));
    }
}
