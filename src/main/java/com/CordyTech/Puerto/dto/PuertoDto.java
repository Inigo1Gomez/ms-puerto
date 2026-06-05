package com.CordyTech.Puerto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class PuertoDto {

    private Integer idPuerto;

    @NotBlank(message = "El nombre del puerto es obligatorio")
    private String nombrePuerto;

    @NotNull(message = "La tarifa por hora es obligatoria")
    @PositiveOrZero(message = "La tarifa por hora debe ser cero o mayor")
    private Float tarifaHora;

    @PositiveOrZero(message = "La tarifa por eslora debe ser cero o mayor")
    private Float tarifaEslora;

    @NotNull(message = "El estado de disponibilidad es obligatorio")
    private Boolean dispo;

    public Integer getIdPuerto() {
        return idPuerto;
    }

    public void setIdPuerto(Integer idPuerto) {
        this.idPuerto = idPuerto;
    }

    public String getNombrePuerto() {
        return nombrePuerto;
    }

    public void setNombrePuerto(String nombrePuerto) {
        this.nombrePuerto = nombrePuerto;
    }

    public Float getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(Float tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    public Float getTarifaEslora() {
        return tarifaEslora;
    }

    public void setTarifaEslora(Float tarifaEslora) {
        this.tarifaEslora = tarifaEslora;
    }

    public Boolean isDispo() {
        return dispo;
    }

    public void setDispo(Boolean dispo) {
        this.dispo = dispo;
    }
}
