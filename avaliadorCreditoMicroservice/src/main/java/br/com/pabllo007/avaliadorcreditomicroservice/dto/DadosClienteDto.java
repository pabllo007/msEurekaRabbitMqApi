package br.com.pabllo007.avaliadorcreditomicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosClienteDto {
    private Long id;
    private String nome;
    private Integer idade;
}
