package com.hizon.controllers;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.hizon.service.GenericService;

import org.springframework.http.ResponseEntity;

@RestController
public abstract class GenericController<T>{
    private GenericService<T> service;

    protected GenericController(GenericService<T> service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<T>> getAll(){
        return ResponseEntity.ok(service.readAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable("id") final Integer id){
        return ResponseEntity.ok(service.read(id));
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T body){
        return ResponseEntity.ok(service.create(body));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable("id") final Integer id, @RequestBody T body){
        return ResponseEntity.ok(service.update(id,body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<T> deleteBook(@PathVariable("id") final Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}