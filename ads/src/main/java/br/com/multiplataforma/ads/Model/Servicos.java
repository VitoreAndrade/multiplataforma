package br.com.multiplataforma.ads.Model;

import br.com.multiplataforma.ads.DTO.DadosCadastroServicoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "servicos")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Servicos {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private String categoria;
    @DBRef
    private Login login;

    public Servicos (DadosCadastroServicoDTO cadastroServico, Login login){
        this.nome = cadastroServico.nome();
        this.descricao = cadastroServico.descricao();
        this.categoria = cadastroServico.categoria();
        this.login = login;
    }

}
