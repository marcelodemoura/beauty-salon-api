package br.com.beauty_salon_api.beauty_salon_api.dto;

import lombok.Data;

@Data
public class AgendamentoRequestDTO {

        private String nomeCliente;
        private Long servicoId;
        private String data; // yyyy-MM-dd
        private String hora; // HH:mm
    }