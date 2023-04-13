package br.com.pabllo007.avaliadorcreditomicroservice.services;

import br.com.pabllo007.avaliadorcreditomicroservice.dto.*;
import br.com.pabllo007.avaliadorcreditomicroservice.exception.DadosClienteNotFoundException;
import br.com.pabllo007.avaliadorcreditomicroservice.exception.ErroComunicacaoMicroserviceException;
import br.com.pabllo007.avaliadorcreditomicroservice.exception.ErroSolicitacaoCartaoException;
import br.com.pabllo007.avaliadorcreditomicroservice.feignclients.CartoesFeign;
import br.com.pabllo007.avaliadorcreditomicroservice.feignclients.ClienteFeign;
import br.com.pabllo007.avaliadorcreditomicroservice.mqueue.SolicitacaoEmissaoCataoPublisher;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AvaliadorCreditoService {
    @Autowired
    private ClienteFeign clienteFeign;
    @Autowired
    private CartoesFeign cartoesFeign;

    @Autowired
    private SolicitacaoEmissaoCataoPublisher emissaoCataoPublisher;

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

    public RetornoAvaliacaoClienteDto realizarAvaliacao(String cpf, Long renda)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroserviceException {
        try {
            // obter dados do clinete no microserico MSCLIENTES
            ResponseEntity<DadosClienteDto> dadosClienteResponse = clienteFeign.dadosCliente(cpf);
            ResponseEntity<List<CartaoDto>> cartoesResponse = cartoesFeign.getCartoesRendaAteh(renda);

            List<CartaoDto> cartoes = cartoesResponse.getBody();
            var listaCartoesAprovados = cartoes.stream().map(cartao -> {
                DadosClienteDto dadosCliente = dadosClienteResponse.getBody();

                BigDecimal limiteBasico = cartao.getLimiteBasico();
                BigDecimal idadeDB = BigDecimal.valueOf(dadosCliente.getIdade());
                var fator = idadeDB.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovadoDto aprovado = new CartaoAprovadoDto();
                aprovado.setCartao(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(limiteAprovado);

                return aprovado;

            }).collect(Collectors.toList());
            return new RetornoAvaliacaoClienteDto(listaCartoesAprovados);

        } catch (FeignException.FeignClientException e) {
            //feign de cartoes retorna uma lista vazio caso não encontre. por isso não retorna 404
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroserviceException(e.getMessage(), status);
        }
    }

    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) {
        try{
            emissaoCataoPublisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);

        } catch (Exception e) {
            throw new ErroSolicitacaoCartaoException(e.getMessage());
        }

    }
}
