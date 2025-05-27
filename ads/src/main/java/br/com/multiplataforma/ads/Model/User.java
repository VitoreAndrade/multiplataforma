package br.com.multiplataforma.ads.Model;

import br.com.multiplataforma.ads.DTO.DadosCadastroUserDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {
    @Id
    private  String id;
    private boolean ativo;
    private String nome;
    @Indexed(unique = true)
    private String email;
    private String telefone;
    private int idade;
    @DBRef
    private Login login;

    public User (DadosCadastroUserDTO userDTO, Login login){
        this.ativo = true;
        this.nome = userDTO.nome();
        this.email = userDTO.email();
        this.telefone = userDTO.telefone();
        this.idade = userDTO.idade();
        this.login = login;
    }

    public User (){

    }
    public void delete (){
        this.ativo = false;
    }
    public String getId() {
        return this.id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public Login getLogin() {
        return login;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
