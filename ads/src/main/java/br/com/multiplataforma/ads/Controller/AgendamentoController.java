package br.com.multiplataforma.ads.Controller;

import br.com.multiplataforma.ads.DTO.DadosAgendamentoDTO;
import br.com.multiplataforma.ads.Service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<?> agendar(@RequestBody DadosAgendamentoDTO dto) {
        return agendamentoService.agendarServico(dto);
    }

    @PutMapping("/{id}/reagendar")
    public ResponseEntity<?> reagendar(@PathVariable String id, @RequestBody DadosAgendamentoDTO dto) {
        return agendamentoService.reagendarServico(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelar(@PathVariable String id) {
        return agendamentoService.cancelarAgendamento(id);
    }
}
