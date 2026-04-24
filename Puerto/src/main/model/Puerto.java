package com.CordyTech.Puerto.model;

import jakarta.persistence.*;

@Entity
public class Puerto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPuerto;

    @Column(nullable = false)
    private String nombrePuerto;

    @Column(nullable = false)
    private float tarifaHora;

    private float tarifaEslora;

    @Column(nullable = false)
    private boolean dispo;

    // GETTERS Y SETTERS

    public int getIdPuerto() {
        return idPuerto;
    }

    public void setIdPuerto(int idPuerto) {
        this.idPuerto = idPuerto;
    }

    public String getNombrePuerto() {
        return nombrePuerto;
    }

    public void setNombrePuerto(String nombrePuerto) {
        this.nombrePuerto = nombrePuerto;
    }

    public float getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(float tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    public float getTarifaEslora() {
        return tarifaEslora;
    }

    public void setTarifaEslora(float tarifaEslora) {
        this.tarifaEslora = tarifaEslora;
    }

    public boolean isDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }
}