package com.CordyTech.Puerto.controller;

import com.CordyTech.Puerto.dto.PuertoDto;
import com.CordyTech.Puerto.service.PuertoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/puertos")
@CrossOrigin(origins = "*")
public class PuertoController {

    @Autowired
    private PuertoService service;

    // GET ALL
    @GetMapping
    public List<PuertoDto> listar() {
        return service.listarDto();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Optional<PuertoDto> obtener(@PathVariable int id) {
        return service.obtenerDtoPorId(id);
    }

    // POST
    @PostMapping
    public PuertoDto guardar(@Valid @RequestBody PuertoDto puertoDto) {
        return service.guardarDto(puertoDto);
    }

    // PUT
    @PutMapping("/{id}")
    public PuertoDto actualizar(@PathVariable int id, @Valid @RequestBody PuertoDto puertoDto) {
        return service.actualizarDto(id, puertoDto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        service.eliminar(id);
    }
}