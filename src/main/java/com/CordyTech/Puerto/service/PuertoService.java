package com.CordyTech.Puerto.service;

import com.CordyTech.Puerto.model.Puerto;
import com.CordyTech.Puerto.repository.PuertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PuertoService {

    @Autowired
    private PuertoRepository repository;

    public List<Puerto> listar() {
        return repository.findAll();
    }

    public Optional<Puerto> obtenerPorId(int id) {
        return repository.findById(id);
    }

    public Puerto guardar(Puerto puerto) {
        return repository.save(puerto);
    }

    public Puerto actualizar(int id, Puerto puerto) {
        Puerto existente = repository.findById(id).orElse(null);

        if (existente != null) {
            existente.setNombrePuerto(puerto.getNombrePuerto());
            existente.setTarifaHora(puerto.getTarifaHora());
            existente.setTarifaEslora(puerto.getTarifaEslora());
            existente.setDispo(puerto.isDispo());
            return repository.save(existente);
        }
        return null;
    }

    public void eliminar(int id) {
        repository.deleteById(id);
    }
}