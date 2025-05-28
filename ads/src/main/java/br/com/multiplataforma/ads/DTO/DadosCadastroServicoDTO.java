package br.com.multiplataforma.ads.DTO;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroServicoDTO(
         @NotBlank
         String nome,
         @NotBlank
         String descricao,
         @NotBlank
         String categoria,
         @NotBlank
         String loginResponsavel
) {
}
