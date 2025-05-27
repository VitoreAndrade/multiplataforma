package br.com.multiplataforma.ads.DTO;

import br.com.multiplataforma.ads.Model.User;

public record DadosListagemUserDTO(
        String id,
        String nome,
        String email,
        String telefone,
        int idade,
        String login // apenas o login, e não o objeto inteiro

) {

}
