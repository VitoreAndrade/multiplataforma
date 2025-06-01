package br.com.multiplataforma.ads.Model;

import br.com.multiplataforma.ads.DTO.DadosAgendamentoDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "agendamentos")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Agendamento {

    @Id
    private String id;

    @DBRef
    private Servicos servico;

    @DBRef
    private Login cliente;

    private LocalDate data;
    private LocalTime horario;

    private String status;

    public Agendamento(DadosAgendamentoDTO dto, Servicos servico, Login cliente) {
        this.servico = servico;
        this.cliente = cliente;
        this.data = dto.data();
        this.horario = dto.horario();
        this.status = "AGENDADO";
    }

    public Agendamento (){};

    public Servicos getServico() {
        return servico;
    }

    public void setServico(Servicos servico) {
        this.servico = servico;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Login getCliente() {
        return cliente;
    }

    public void setCliente(Login cliente) {
        this.cliente = cliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}
