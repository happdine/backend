package com.bosch.happdine.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;


@Entity
@Table(name = "cardapio")
public class CardapioModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_cardapio;
    private String prato_principal;
    private String guarnicao;
    private String sobremesa;
    private String salada;
    private String data;

    //colocar chave estrangeira id restaurante
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurante_id", referencedColumnName = "id")
    private RestauranteModel restauranteModel;

    public UUID getId_cardapio() {
        return id_cardapio;
    }

    public void setId_cardapio(UUID id_cardapio) {
        this.id_cardapio = id_cardapio;
    }

    public String getPrato_principal() {
        return prato_principal;
    }

    public void setPrato_principal(String prato_principal) {
        this.prato_principal = prato_principal;
    }

    public String getGuarnicao() {
        return guarnicao;
    }

    public void setGuarnicao(String guarnicao) {
        this.guarnicao = guarnicao;
    }

    public String getSobremesa() {
        return sobremesa;
    }

    public void setSobremesa(String sobremesa) {
        this.sobremesa = sobremesa;
    }

    public String getSalada() {
        return salada;
    }

    public void setSalada(String salada) {
        this.salada = salada;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public RestauranteModel getRestauranteModel() {
        return restauranteModel;
    }

    public void setRestauranteModel(RestauranteModel restauranteModel) {
        this.restauranteModel = restauranteModel;
    }
}
