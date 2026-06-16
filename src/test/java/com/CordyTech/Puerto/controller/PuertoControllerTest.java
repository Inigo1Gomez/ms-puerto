package com.CordyTech.Puerto.controller;

import com.CordyTech.Puerto.dto.PuertoDto;
import com.CordyTech.Puerto.service.PuertoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PuertoController.class)
class PuertoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PuertoService service;

    @Autowired
    private ObjectMapper objectMapper;

    private PuertoDto puertoDto;

    @BeforeEach
    void setUp() {
        puertoDto = new PuertoDto();
        puertoDto.setIdPuerto(1);
        puertoDto.setNombrePuerto("Puerto Montt");
        puertoDto.setTarifaHora(15.0f);
        puertoDto.setTarifaEslora(5.0f);
        puertoDto.setDispo(true);
    }

    @Test
    void listar_debeRetornar200ConListaDePuertos() throws Exception {
        when(service.listarDto()).thenReturn(List.of(puertoDto));

        mockMvc.perform(get("/puertos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombrePuerto").value("Puerto Montt"))
                .andExpect(jsonPath("$[0].tarifaHora").value(15.0))
                .andExpect(jsonPath("$[0].dispo").value(true));
    }

    @Test
    void obtener_cuandoExiste_debeRetornar200ConPuerto() throws Exception {
        when(service.obtenerDtoPorId(1)).thenReturn(Optional.of(puertoDto));

        mockMvc.perform(get("/puertos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombrePuerto").value("Puerto Montt"))
                .andExpect(jsonPath("$.idPuerto").value(1));
    }

    @Test
    void obtener_cuandoNoExiste_debeRetornar200Vacio() throws Exception {
        when(service.obtenerDtoPorId(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/puertos/99"))
                .andExpect(status().isOk());
    }

    @Test
    void guardar_conDatosValidos_debeRetornar200ConPuertoCreado() throws Exception {
        PuertoDto input = new PuertoDto();
        input.setNombrePuerto("Puerto Montt");
        input.setTarifaHora(15.0f);
        input.setTarifaEslora(5.0f);
        input.setDispo(true);

        when(service.guardarDto(any(PuertoDto.class))).thenReturn(puertoDto);

        mockMvc.perform(post("/puertos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPuerto").value(1))
                .andExpect(jsonPath("$.nombrePuerto").value("Puerto Montt"));
    }

    @Test
    void guardar_sinNombre_debeRetornar400() throws Exception {
        PuertoDto inputInvalido = new PuertoDto();
        inputInvalido.setTarifaHora(15.0f);
        inputInvalido.setDispo(true);

        mockMvc.perform(post("/puertos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputInvalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void actualizar_conDatosValidos_debeRetornar200() throws Exception {
        when(service.actualizarDto(eq(1), any(PuertoDto.class))).thenReturn(puertoDto);

        PuertoDto input = new PuertoDto();
        input.setNombrePuerto("Puerto Varas");
        input.setTarifaHora(20.0f);
        input.setDispo(false);

        mockMvc.perform(put("/puertos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombrePuerto").value("Puerto Montt"));
    }

    @Test
    void eliminar_debeRetornar200() throws Exception {
        doNothing().when(service).eliminar(1);

        mockMvc.perform(delete("/puertos/1"))
                .andExpect(status().isOk());
    }
}
