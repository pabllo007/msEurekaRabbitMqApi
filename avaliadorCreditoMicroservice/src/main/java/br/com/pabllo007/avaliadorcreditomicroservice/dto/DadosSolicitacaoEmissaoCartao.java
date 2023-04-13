package br.com.pabllo007.avaliadorcreditomicroservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosSolicitacaoEmissaoCartao {

    private Long idCartao;
    private String cpf;
    private String endereço;
    private BigDecimal limiteLiberado;
}
