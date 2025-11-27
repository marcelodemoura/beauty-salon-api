package br.com.beauty_salon_api.beauty_salon_api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendamentoRequestDTO {

    private Long clienteId;
    private Long profissionalId;
    private LocalDateTime dataHora;
    private LocalDateTime dataFim;
    private String observacao;


}
