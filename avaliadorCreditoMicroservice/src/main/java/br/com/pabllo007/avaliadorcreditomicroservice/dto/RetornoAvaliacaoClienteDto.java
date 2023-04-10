package br.com.pabllo007.avaliadorcreditomicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoClienteDto {

    List<CartaoAprovadoDto> cartoes;
}
