package br.com.pabllo007.cartoesMicroservice.dto;

import br.com.pabllo007.cartoesMicroservice.enuns.BandeiraCartao;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CartaoSaveDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

}



