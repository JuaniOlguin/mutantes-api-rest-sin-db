package com.example.mutantes.controllers;

import com.example.mutantes.entities.Persona;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface MutantController {
    public ResponseEntity<?> detect(@RequestBody Persona persona);
    public ResponseEntity<?> getAll();
    public ResponseEntity<?> getOne(@PathVariable Long id);
    public ResponseEntity<?> save(@RequestBody Persona entity);
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Persona entity);
    public ResponseEntity<?> delete(@PathVariable Long id);
}
