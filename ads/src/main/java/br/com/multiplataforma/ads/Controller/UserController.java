package br.com.multiplataforma.ads.Controller;

import br.com.multiplataforma.ads.DTO.DadosAtualizacaoUser;
import br.com.multiplataforma.ads.DTO.DadosCadastroUserDTO;
import br.com.multiplataforma.ads.DTO.DadosListagemUserDTO;
import br.com.multiplataforma.ads.Model.User;
import br.com.multiplataforma.ads.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid DadosCadastroUserDTO userDTO){
        System.out.println("chegou");
        return userService.savedUser(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemUserDTO>> listUser() {
        List<DadosListagemUserDTO> usuarios = userService.getUser();
        return ResponseEntity.ok(usuarios);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser (@PathVariable String id){
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody DadosAtualizacaoUser updateUser){
        return userService.updateUser(id, updateUser);
    }
}
