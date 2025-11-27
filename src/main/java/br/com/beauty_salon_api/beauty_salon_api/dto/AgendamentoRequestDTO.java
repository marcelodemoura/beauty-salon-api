package br.com.beauty_salon_api.beauty_salon_api.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AgendamentoRequestDTO {

    private Long clienteId;
    private Long profissionalId;
    private List<Long> servicosIds; // lista de serviços!
    private LocalDateTime dataHora;
    private String observacao;
}