package br.com.multiplataforma.ads.Repository;

import br.com.multiplataforma.ads.Model.Agendamento;
import br.com.multiplataforma.ads.Model.Servicos;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface AgendamentoRepository extends MongoRepository<Agendamento, String> {

    Optional<Agendamento> findByServicoAndDataAndHorario(Servicos servico, LocalDate data, LocalTime horario);

}
