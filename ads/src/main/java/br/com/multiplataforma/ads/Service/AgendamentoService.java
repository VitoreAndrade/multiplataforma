package br.com.multiplataforma.ads.Service;

import br.com.multiplataforma.ads.DTO.DadosAgendamentoDTO;
import br.com.multiplataforma.ads.Model.Agendamento;
import br.com.multiplataforma.ads.Model.Login;
import br.com.multiplataforma.ads.Model.Servicos;
import br.com.multiplataforma.ads.Repository.AgendamentoRepository;
import br.com.multiplataforma.ads.Repository.LoginRepository;
import br.com.multiplataforma.ads.Repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private LoginRepository loginRepository;

    public ResponseEntity<?> agendarServico(DadosAgendamentoDTO dto) {
        Optional<Servicos> servicoOpt = servicoRepository.findById(dto.idServico());
        Optional<Login> clienteOpt = loginRepository.findByLogin(dto.loginCliente());

        if (servicoOpt.isEmpty() || clienteOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Serviço ou cliente não encontrado.");
        }

        boolean ocupado = agendamentoRepository
                .findByServicoAndDataAndHorario(servicoOpt.get(), dto.data(), dto.horario())
                .isPresent();

        if (ocupado) {
            return ResponseEntity.badRequest().body("Horário já ocupado para este serviço.");
        }

        Agendamento agendamento = new Agendamento(dto, servicoOpt.get(), clienteOpt.get());

        agendamentoRepository.save(agendamento);
        return ResponseEntity.ok("Agendamento realizado com sucesso.");
    }

    public ResponseEntity<?> cancelarAgendamento(String idAgendamento) {
        Optional<Agendamento> agendamentoOpt = agendamentoRepository.findById(idAgendamento);

        if (agendamentoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado.");
        }

        Agendamento agendamento = agendamentoOpt.get();
        agendamento.setStatus("CANCELADO");

        agendamentoRepository.save(agendamento);

        return ResponseEntity.ok("Agendamento cancelado com sucesso.");
    }

    public ResponseEntity<?> reagendarServico(String idAgendamento, DadosAgendamentoDTO novoAgendamento) {
        Optional<Agendamento> agendamentoOpt = agendamentoRepository.findById(idAgendamento);
        Optional<Servicos> servicoOpt = servicoRepository.findById(novoAgendamento.idServico());
        Optional<Login> clienteOpt = loginRepository.findByLogin(novoAgendamento.loginCliente());

        if (agendamentoOpt.isEmpty() || servicoOpt.isEmpty() || clienteOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Agendamento, serviço ou cliente não encontrado.");
        }

        Agendamento agendamentoExistente = agendamentoOpt.get();

        // Verifica se o novo horário já está ocupado
        boolean ocupado = agendamentoRepository
                .findByServicoAndDataAndHorario(servicoOpt.get(), novoAgendamento.data(), novoAgendamento.horario())
                .isPresent();

        if (ocupado) {
            return ResponseEntity.badRequest().body("Novo horário indisponível.");
        }

        // Atualiza os dados
        agendamentoExistente.setData(novoAgendamento.data());
        agendamentoExistente.setHorario(novoAgendamento.horario());
        agendamentoExistente.setStatus("REAGENDADO");

        agendamentoRepository.save(agendamentoExistente);

        return ResponseEntity.ok("Agendamento reagendado com sucesso.");
    }

}

