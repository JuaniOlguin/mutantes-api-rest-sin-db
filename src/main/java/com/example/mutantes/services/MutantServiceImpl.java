package com.example.mutantes.services;

import com.example.mutantes.entities.Persona;
import com.example.mutantes.matriz.HintSearch;
import com.example.mutantes.matriz.MatrixCreator;
import com.example.mutantes.matriz.MutantDetector;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class MutantServiceImpl implements MutantService{

    public MatrixCreator matrixCreator = new MatrixCreator();
    public HintSearch mutantSearch = new HintSearch();
    public MutantDetector mutantDetector = new MutantDetector(mutantSearch);

    List<Persona> personas = new ArrayList<>();

    @Override
    @Transactional
    public boolean isMutant(Persona persona) throws Exception {
        try{
            char [][] dnaMatriz = matrixCreator.hacerMatriz(persona.getDna());
            if(mutantDetector.isMutant(dnaMatriz)){
                for (Persona p:personas) {
                    if(p.getId().equals(persona.getId())){
                        p.setMutant(true);
                    }
                }

                return true;
            } else {
                persona.setMutant(false);
                throw new Exception("Mutante no encontrado");
            }

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Persona> findAll() throws Exception {
        return personas;
    }

    @Override
    @Transactional
    public Persona findById(Long id) throws Exception {
        try {
            for (Persona p: personas) {
                if(p.getId().equals(id)){
                    return p;
                }
            }
            throw new Exception("Persona con id "+id+" no encontrada");
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona save(Persona persona) throws Exception {
        try {
            personas.add(persona);
            return persona;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona update(Long id, Persona entity) throws Exception {
        try {
            for (Persona p: personas) {
                if(p.getId().equals(id)){
                    p = entity;
                    return p;
                }
            }
            throw new Exception("Persona con id "+id+" no encontrada");
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try {
            for (Persona p: personas) {
                if(p.getId().equals(id)){
                    return personas.remove(p);
                }
            }
            throw new Exception("Persona con id "+id+" no encontrada");
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
