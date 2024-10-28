package com.bosch.happdine.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FilaRecordDto(@NotNull boolean isAtivado, @NotNull String localizacao, @NotNull UUID restauranteId) {
}
