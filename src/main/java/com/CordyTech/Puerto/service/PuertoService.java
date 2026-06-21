package com.CordyTech.Puerto.service;

import com.CordyTech.Puerto.dto.PuertoDto;
import com.CordyTech.Puerto.exception.ResourceNotFoundException;
import com.CordyTech.Puerto.model.Puerto;
import com.CordyTech.Puerto.repository.PuertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PuertoService {

    @Autowired
    private PuertoRepository repository;

    public List<PuertoDto> listarDto() {
        return repository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PuertoDto obtenerDtoPorId(int id) {
        return repository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Puerto con id " + id + " no encontrado"));
    }

    public PuertoDto guardarDto(PuertoDto puertoDto) {
        Puerto puerto = convertToEntity(puertoDto);
        return convertToDto(repository.save(puerto));
    }

    public PuertoDto actualizarDto(int id, PuertoDto puertoDto) {
        Puerto existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Puerto con id " + id + " no encontrado"));

        existente.setNombrePuerto(puertoDto.getNombrePuerto());
        existente.setTarifaHora(puertoDto.getTarifaHora());
        existente.setTarifaEslora(puertoDto.getTarifaEslora());
        existente.setDispo(puertoDto.isDispo());
        return convertToDto(repository.save(existente));
    }

    public void eliminar(int id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Puerto con id " + id + " no encontrado");
        }
        repository.deleteById(id);
    }

    private PuertoDto convertToDto(Puerto puerto) {
        PuertoDto dto = new PuertoDto();
        dto.setIdPuerto(puerto.getIdPuerto());
        dto.setNombrePuerto(puerto.getNombrePuerto());
        dto.setTarifaHora(puerto.getTarifaHora());
        dto.setTarifaEslora(puerto.getTarifaEslora());
        dto.setDispo(puerto.isDispo());
        return dto;
    }

    private Puerto convertToEntity(PuertoDto dto) {
        Puerto puerto = new Puerto();
        puerto.setNombrePuerto(dto.getNombrePuerto());
        puerto.setTarifaHora(dto.getTarifaHora());
        puerto.setTarifaEslora(dto.getTarifaEslora());
        puerto.setDispo(dto.isDispo());
        return puerto;
    }
}
