package com.bosch.happdine.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.TimeZone;

public record MonitoramentoRecordDto(@NotNull TimeZone horarioRegistro, @NotNull int quantidadade) {
}
