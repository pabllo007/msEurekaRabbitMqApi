package br.com.pabllo007.cartoesMicroservice.mqueue;

import br.com.pabllo007.cartoesMicroservice.dto.DadosSolicitacaoEmissaoCartao;
import br.com.pabllo007.cartoesMicroservice.entities.Cartao;
import br.com.pabllo007.cartoesMicroservice.entities.CartaoCliente;
import br.com.pabllo007.cartoesMicroservice.repositories.CartaoRepository;
import br.com.pabllo007.cartoesMicroservice.repositories.ClienteCartaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmissaoCartaoSubscriber {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "emissao-cartoes")
    public void receberSolicitacaoEmissao(@Payload String paylod) {
        try {
            var mapper = new ObjectMapper();
            DadosSolicitacaoEmissaoCartao dados = mapper.readValue(paylod, DadosSolicitacaoEmissaoCartao.class);
            Cartao cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();
            CartaoCliente cartaoCliente = new CartaoCliente();
            cartaoCliente.setCartao(cartao);
            cartaoCliente.setCpf(dados.getCpf());
            cartaoCliente.setLimite(dados.getLimiteLiberado());

            clienteCartaoRepository.save(cartaoCliente);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
