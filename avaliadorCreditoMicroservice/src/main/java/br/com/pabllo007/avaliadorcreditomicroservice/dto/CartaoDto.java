package br.com.pabllo007.avaliadorcreditomicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartaoDto {
    private Long id;
    private String nome;
    private String bandeira;
    private BigDecimal limiteBasico;
}
