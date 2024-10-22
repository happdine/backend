package com.bosch.happdine.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "informacoesRestaurante")
public class InfoRestauranteModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID idInformacoesRestaurante;
    private String horarioFuncionamento;
    private String refeicoesOferecidas;
    private String localizacao;

    public UUID getIdInformacoesRestaurante() {
        return idInformacoesRestaurante;
    }

    public void setIdInformacoesRestaurante(UUID idInformacoesRestaurante) {
        this.idInformacoesRestaurante = idInformacoesRestaurante;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public String getRefeicoesOferecidas() {
        return refeicoesOferecidas;
    }

    public void setRefeicoesOferecidas(String refeicoesOferecidas) {
        this.refeicoesOferecidas = refeicoesOferecidas;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
