package br.com.beauty_salon_api.beauty_salon_api.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AgendamentoResponseDTO {

    private Long id;
    private String cliente;
    private String profissional;
    private List<String> servicos;
    private LocalDateTime dataHora;
    private LocalDateTime dataFim;
    private String observacao;

}
