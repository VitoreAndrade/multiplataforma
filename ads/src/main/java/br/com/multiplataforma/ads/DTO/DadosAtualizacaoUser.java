package br.com.multiplataforma.ads.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUser(
        String nome,
        String email,
        String telefone,
        Integer idade
) {
}
