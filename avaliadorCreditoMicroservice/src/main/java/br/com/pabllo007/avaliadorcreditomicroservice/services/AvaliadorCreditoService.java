package br.com.pabllo007.avaliadorcreditomicroservice.services;

import br.com.pabllo007.avaliadorcreditomicroservice.dto.DadosClienteDto;
import br.com.pabllo007.avaliadorcreditomicroservice.dto.SituacaoClienteDto;
import br.com.pabllo007.avaliadorcreditomicroservice.feignclients.ClienteFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AvaliadorCreditoService {

    @Autowired
    private ClienteFeign clienteFeign;
    public SituacaoClienteDto obterSituacaoCLiente(String cpf) {
        // obter dados do clinete no microserico MSCLIENTES
        ResponseEntity<DadosClienteDto> dadosClienteResponse = clienteFeign.dadosCliente(cpf);

        //obter cartoes do cliente no microserico MSCARTOES

        return  SituacaoClienteDto
                .builder()
                .cliente(dadosClienteResponse.getBody())
                .build();
    }
}
