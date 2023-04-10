package br.com.pabllo007.avaliadorcreditomicroservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoAprovadoDto {
    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;

}
