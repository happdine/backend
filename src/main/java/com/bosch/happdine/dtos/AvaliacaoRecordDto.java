package com.bosch.happdine.dtos;

import com.bosch.happdine.models.AvaliacaoChatModel;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AvaliacaoRecordDto(@NotNull AvaliacaoChatModel.Avaliacao Avaliacao, @NotNull LocalDate data) {
}
