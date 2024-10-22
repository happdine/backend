package com.bosch.happdine.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "movimentacao")
public class MonitoramentoModel implements Serializable {
    private static final long serializerVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_movimentacao;

    private LocalDate dataRegistro;

    private LocalTime horarioRegistro;  // Alterado para LocalTime

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    private int quantidade;

    //chave estrangeira fila
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_fila", nullable = false)
    private FilaModel filaModel;

    // getters e setters
    public UUID getId_movimentacao() {
        return id_movimentacao;
    }

    public void setId_movimentacao(UUID id_movimentacao) {
        this.id_movimentacao = id_movimentacao;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public LocalTime getHorarioRegistro() {
        return horarioRegistro;
    }

    public void setHorarioRegistro(LocalTime horarioRegistro) {
        this.horarioRegistro = horarioRegistro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public FilaModel getFilaModel() {
        return filaModel;
    }

    public void setFilaModel(FilaModel filaModel) {
        this.filaModel = filaModel;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public enum Situacao {
        VERMELHO("Vermelho"),
        AMARELO("Amarelo"),
        VERDE("Verde");

        private final String label;

        Situacao(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}

