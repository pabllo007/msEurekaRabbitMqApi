package br.com.pabllo007.cartoesMicroservice.dto;

import br.com.pabllo007.cartoesMicroservice.entities.Cartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoClienteDto {
    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

}