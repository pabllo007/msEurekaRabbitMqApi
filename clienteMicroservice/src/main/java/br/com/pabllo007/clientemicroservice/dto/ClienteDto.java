package br.com.pabllo007.clientemicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto implements Serializable {

    private Long id;
    private String cpf;
    private String nome;
    private Integer idade;

}
