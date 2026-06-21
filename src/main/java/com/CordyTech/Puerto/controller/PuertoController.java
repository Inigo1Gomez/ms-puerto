package com.CordyTech.Puerto.controller;

import com.CordyTech.Puerto.dto.PuertoDto;
import com.CordyTech.Puerto.service.PuertoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puertos")
@CrossOrigin(origins = "*")
public class PuertoController {

    @Autowired
    private PuertoService service;

    @GetMapping
    public List<PuertoDto> listar() {
        return service.listarDto();
    }

    @GetMapping("/{id}")
    public PuertoDto obtener(@PathVariable int id) {
        return service.obtenerDtoPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PuertoDto guardar(@Valid @RequestBody PuertoDto puertoDto) {
        return service.guardarDto(puertoDto);
    }

    @PutMapping("/{id}")
    public PuertoDto actualizar(@PathVariable int id, @Valid @RequestBody PuertoDto puertoDto) {
        return service.actualizarDto(id, puertoDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable int id) {
        service.eliminar(id);
    }
}
