package com.bosch.happdine.dtos;

import java.time.LocalDate;
import java.util.UUID;

public class VotesRecordDto {
    private UUID restauranteId; // ID do restaurante
    private int votos; // Número de votos
    private LocalDate dataRegistro; // Data do registro

    // Getters
    public UUID getRestauranteId() {
        return restauranteId;
    }

    public int getVotos() {
        return votos;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    // Setters
    public void setRestauranteId(UUID restauranteId) {
        this.restauranteId = restauranteId;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
