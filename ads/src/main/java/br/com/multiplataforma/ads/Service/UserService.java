package br.com.multiplataforma.ads.Service;

import br.com.multiplataforma.ads.DTO.DadosAtualizacaoUser;
import br.com.multiplataforma.ads.DTO.DadosCadastroUserDTO;
import br.com.multiplataforma.ads.DTO.DadosListagemUserDTO;
import br.com.multiplataforma.ads.Model.Login;
import br.com.multiplataforma.ads.Model.User;
import br.com.multiplataforma.ads.Repository.LoginRepository;
import br.com.multiplataforma.ads.Repository.UserRepository;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity savedUser (DadosCadastroUserDTO userDTO){

        if(loginRepository.existsByLogin(userDTO.login().login())){
            throw new RuntimeException("Login ja em uso");
        }
        Login login = new Login(userDTO.login());
        login.setPassword(passwordEncoder.encode(userDTO.login().senha()));
        loginRepository.save(login);

        User user = new User(userDTO, login);
        userRepository.save(user);

        return ResponseEntity.ok().body(userDTO);
    }
    public List<DadosListagemUserDTO> getUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user ->
                new DadosListagemUserDTO(
                        user.getId(),
                        user.getNome(),
                        user.getEmail(),
                        user.getTelefone(),
                        user.getIdade(),
                        user.getLogin() != null && user.getLogin().getLogin() != null ? user.getLogin().getLogin() : null)
        ).toList();
    }

    public ResponseEntity deleteUser (String id){
        Optional<User> userDelete = userRepository.findById(id);
        if(userDelete.isPresent()){
            User deleteUser = userDelete.get();
            deleteUser.delete();
            userRepository.save(deleteUser);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity updateUser(String id, DadosAtualizacaoUser userUpdate){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User newUser = user.get();
        if(userUpdate.nome() != null){
            newUser.setNome(userUpdate.nome());
        }
        if (userUpdate.email() != null){
            newUser.setEmail(userUpdate.email());
        }
        if(userUpdate.telefone() != null){
            newUser.setTelefone(userUpdate.telefone());
        }
        if(userUpdate.idade() != null){
            newUser.setIdade(userUpdate.idade());
        }
        userRepository.save(newUser);
        return ResponseEntity.ok().body("Usuário atulizado");
    }
}
