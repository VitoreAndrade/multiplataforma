package br.com.multiplataforma.ads.Service;

import br.com.multiplataforma.ads.DTO.DadosAtualizacaoLoginDTO;
import br.com.multiplataforma.ads.DTO.DadosCadastroLoginDTO;
import br.com.multiplataforma.ads.Model.Login;
import br.com.multiplataforma.ads.Model.User;
import br.com.multiplataforma.ads.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity startSession(DadosCadastroLoginDTO dadosLogin) {
        Optional<Login> loginOptional = loginRepository.findByLoginAndSenha(dadosLogin.login(), dadosLogin.senha());
        if (loginOptional.isPresent()) {
            Login login = loginOptional.get();
            if (passwordEncoder.matches(dadosLogin.senha(), login.getPassword())) {
                return ResponseEntity.ok("Logado com sucesso!");
            }
            return ResponseEntity.ok("Logado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senha inválidos.");
        }
    }

    public ResponseEntity updateLogin (String id, DadosAtualizacaoLoginDTO dadosLogin){
        Optional<Login> loginOptional = loginRepository.findById(id);

        if(loginOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Login login = loginOptional.get();
        if(dadosLogin.senha().isEmpty()){
            return ResponseEntity.badRequest().body("A senha não pode ser nula!");
        }
        if(passwordEncoder.matches(dadosLogin.senha(), login.getSenha())){
            return ResponseEntity.badRequest().body("Não é permitido cadastrar uma senha igual a anterior!");
        }
            login.setSenha(passwordEncoder.encode(dadosLogin.senha()));

        loginRepository.save(login);
        return ResponseEntity.ok().body("Senha alterado com sucesso");

    }

    public ResponseEntity deleteLogin (String id){
        Optional<Login> loginOptional = loginRepository.findById(id);
        if(loginOptional.isPresent()){
            Login deleteLogin = loginOptional.get();
            deleteLogin.deleteLogin();
            loginRepository.save(deleteLogin);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
