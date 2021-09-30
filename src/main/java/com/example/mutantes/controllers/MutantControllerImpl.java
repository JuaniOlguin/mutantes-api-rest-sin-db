package com.example.mutantes.controllers;

import com.example.mutantes.entities.Persona;
import com.example.mutantes.services.MutantService;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") //permitir el acceso a la api desde distintos clientes
@RequestMapping(path = "api/mutants") //la uri que necesitamos para acceder a los recursos, personas es el recurso en este caso
public class MutantControllerImpl implements MutantController {

    @Autowired
    protected MutantService servicio;

    @PostMapping("/isMutant")
    public ResponseEntity<?> detect(@RequestBody Persona persona){
        try{
            if(servicio.isMutant(persona)){
                return ResponseEntity.status(HttpStatus.OK).body("{\"mutant\":\"Mutante encontrado.\"}");
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"mutant\":\"Mutante no encontrado.\"}"); //msj de error en JSON
        }
    }

    @GetMapping("") //esta notacion define el tipo de request, GET en este caso
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll()); //contiene el status de la respuesta (codigos 400, etc)
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}"); //msj de error en JSON
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findById(id)); //contiene el status de la respuesta (codigos 400, etc)
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}"); //msj de error en JSON
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Persona entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.save(entity)); //contiene el status de la respuesta (codigos 400, etc)
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}"); //msj de error en JSON
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Persona entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.update(id, entity)); //contiene el status de la respuesta (codigos 400, etc)
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}"); //msj de error en JSON
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicio.delete(id)); //contiene el status de la respuesta (codigos 400, etc)
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}"); //msj de error en JSON
        }
    }
}
