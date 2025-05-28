package br.com.multiplataforma.ads.Controller;

import br.com.multiplataforma.ads.DTO.DadosCadastroServicoDTO;
import br.com.multiplataforma.ads.Service.ServicosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicosService servicosService;

    @PostMapping
    @Transactional
    public ResponseEntity savedService(@RequestBody @Valid DadosCadastroServicoDTO cadastroServico){
        return servicosService.savedService(cadastroServico);
    }
}
