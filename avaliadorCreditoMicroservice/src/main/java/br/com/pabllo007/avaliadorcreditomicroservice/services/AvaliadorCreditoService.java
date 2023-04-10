package br.com.pabllo007.avaliadorcreditomicroservice.services;

import br.com.pabllo007.avaliadorcreditomicroservice.dto.CartaoClienteDto;
import br.com.pabllo007.avaliadorcreditomicroservice.dto.DadosClienteDto;
import br.com.pabllo007.avaliadorcreditomicroservice.dto.SituacaoClienteDto;
import br.com.pabllo007.avaliadorcreditomicroservice.exception.DadosClienteNotFoundException;
import br.com.pabllo007.avaliadorcreditomicroservice.exception.ErroComunicacaoMicroserviceException;
import br.com.pabllo007.avaliadorcreditomicroservice.feignclients.CartoesFeign;
import br.com.pabllo007.avaliadorcreditomicroservice.feignclients.ClienteFeign;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliadorCreditoService {
    @Autowired
    private ClienteFeign clienteFeign;
    @Autowired
    private CartoesFeign cartoesFeign;
    public SituacaoClienteDto obterSituacaoCLiente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroserviceException {
        try{
            // obter dados do clinete no microserico MSCLIENTES
            ResponseEntity<DadosClienteDto> dadosClienteResponse = clienteFeign.dadosCliente(cpf);

            //obter cartoes do cliente no microserico MSCARTOES
            ResponseEntity<List<CartaoClienteDto>> cartaoClienteResponse = cartoesFeign.getCartoesByCliente(cpf);

            return  SituacaoClienteDto
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartaoClienteResponse.getBody())
                    .build();

        } catch (FeignException.FeignClientException e) {
            //feign de cartoes retorna uma lista vazio caso não encontre. por isso não retorna 404
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status)  {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroserviceException(e.getMessage(), status);
        }
    }
}
