package com.bosch.happdine.dtos;

import jakarta.validation.constraints.NotNull;

public record RestauranteRecordDto(@NotNull String descricao, @NotNull String nome) {
}
