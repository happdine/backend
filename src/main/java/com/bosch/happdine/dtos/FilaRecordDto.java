package com.bosch.happdine.dtos;

import jakarta.validation.constraints.NotNull;

public record FilaRecordDto(@NotNull boolean isAtivado, @NotNull boolean localizacao) {
}
