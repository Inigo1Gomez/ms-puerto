package com.CordyTech.Puerto.controller;

import com.CordyTech.Puerto.model.Puerto;
import com.CordyTech.Puerto.service.PuertoService;
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
    public List<Puerto> listar() {
        return service.listar();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Optional<Puerto> obtener(@PathVariable int id) {
        return service.obtenerPorId(id);
    }

    // POST
    @PostMapping
    public Puerto guardar(@RequestBody Puerto puerto) {
        return service.guardar(puerto);
    }

    // PUT
    @PutMapping("/{id}")
    public Puerto actualizar(@PathVariable int id, @RequestBody Puerto puerto) {
        return service.actualizar(id, puerto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        service.eliminar(id);
    }
}