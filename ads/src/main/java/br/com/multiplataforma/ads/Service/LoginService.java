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
        Optional<Login> loginOptional = loginRepository.findByLogin(dadosLogin.login());

        if (loginOptional.isPresent()) {
            Login login = loginOptional.get();
            if (passwordEncoder.matches(dadosLogin.senha(), login.getSenha())) {
                return ResponseEntity.ok("Logado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login não encontrado.");
        }
    }
    public ResponseEntity updateLogin(DadosAtualizacaoLoginDTO dadosLogin) {
        Optional<Login> loginOptional = loginRepository.findByLogin(dadosLogin.login());

        if (loginOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Login login = loginOptional.get();

        if (dadosLogin.senha() == null || dadosLogin.senha().isEmpty()) {
            return ResponseEntity.badRequest().body("A senha não pode ser nula!");
        }
        if (passwordEncoder.matches(dadosLogin.senha(), login.getSenha())) {
            return ResponseEntity.badRequest().body("Não é permitido cadastrar uma senha igual à anterior!");
        }

        login.setSenha(passwordEncoder.encode(dadosLogin.senha()));
        loginRepository.save(login);

        return ResponseEntity.ok("Senha alterada com sucesso");
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
