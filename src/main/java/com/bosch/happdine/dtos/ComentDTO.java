package com.bosch.happdine.dtos;

import com.bosch.happdine.models.Opcao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComentDTO(
        @NotNull @NotBlank String nome,
        @NotNull Opcao opcao,
        @NotNull @NotBlank String comentario,
        @NotNull @NotBlank String timestampp,
        @NotNull Integer estrela
) {
}
