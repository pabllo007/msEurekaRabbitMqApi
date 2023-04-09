package br.com.pabllo007.cartoesMicroservice.dto;

import br.com.pabllo007.cartoesMicroservice.enuns.BandeiraCartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;


}



