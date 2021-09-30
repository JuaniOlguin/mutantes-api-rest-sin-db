package com.example.mutantes.services;

import com.example.mutantes.entities.Persona;

import java.util.List;

public interface MutantService {
    public boolean isMutant(Persona persona) throws Exception;
    public List<Persona> findAll() throws Exception;
    public Persona findById(Long id) throws Exception;
    public Persona save(Persona persona) throws Exception;
    public Persona update(Long id, Persona entity) throws Exception;
    public boolean delete(Long id) throws Exception;
}
