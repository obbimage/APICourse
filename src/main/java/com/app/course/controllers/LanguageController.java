package com.app.course.controllers;


import com.app.course.models.Language;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/language")
public class LanguageController {
    @Autowired
    LanguageService service;
    @GetMapping("")
    public List<Language> getAll(){
        return service.getAllLanguage();
    }

    @GetMapping("/{id}")
    ResponseEntity<RepositoryObject> findById(@PathVariable int id){
        return service.getLanguageById(id);
    }

    @PostMapping("")
    ResponseEntity<RepositoryObject> insertLanguage(@RequestBody Language language){
        return service.insertLanguage(language);
    }

    @PutMapping("/{id}")
    ResponseEntity<RepositoryObject> updateLanguage(@RequestBody Language language, @PathVariable int id){
        return service.updateLanguage(language,id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<RepositoryObject> deleteLanguageById(@PathVariable int id){
        return service.deleteLanguageById(id);
    }
}
