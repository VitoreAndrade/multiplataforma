package br.com.multiplataforma.ads.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public record DadosAgendamentoDTO (
        String idServico,
        String loginCliente,
        LocalDate data,
        LocalTime horario
){
}
