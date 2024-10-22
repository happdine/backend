package com.bosch.happdine.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

//inicializa todos os argumentos que serão salvos na base de dados
public record CardapioRecordDto(@NotNull String data, String prato_principal, String guarnicao, String salada, String sobremesa, @NotNull UUID fk_restaurante) {
}
