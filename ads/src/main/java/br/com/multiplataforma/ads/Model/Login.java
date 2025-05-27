package br.com.multiplataforma.ads.Model;

import br.com.multiplataforma.ads.DTO.DadosCadastroLoginDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "login")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Login {
    @Id
    private String id;
    @Indexed(unique = true)
    private String login;
    private String senha;
    private boolean ativo;
    private String userId;

public Login (DadosCadastroLoginDTO loginDTO){
    this.login = loginDTO.login();
    this.senha = loginDTO.senha();
    this.ativo = true;
    }

    public Login(){}

    public void deleteLogin(){
     this.ativo = false;
    }
    public String getLogin() {
        return login;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public String getUserId() {
        return userId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setPassword(String password) {
        this.senha = password;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
