package br.com.multiplataforma.ads.DTO;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroLoginDTO(
        @NotBlank
        String login,
        @NotBlank
        String senha
) {
}
