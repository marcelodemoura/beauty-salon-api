package br.com.beauty_salon_api.beauty_salon_api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AgendamentoResponseDTO {

    private Long id;
    private String cliente;
    private String profissional;
    private LocalDateTime dataHora;
    private LocalDateTime dataFim;
    private String observacao;

}
