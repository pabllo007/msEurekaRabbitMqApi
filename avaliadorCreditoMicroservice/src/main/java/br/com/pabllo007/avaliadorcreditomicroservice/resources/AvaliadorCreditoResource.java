package br.com.pabllo007.avaliadorcreditomicroservice.resources;

import br.com.pabllo007.avaliadorcreditomicroservice.dto.SituacaoClienteDto;
import br.com.pabllo007.avaliadorcreditomicroservice.exception.DadosClienteNotFoundException;
import br.com.pabllo007.avaliadorcreditomicroservice.exception.ErroComunicacaoMicroserviceException;
import br.com.pabllo007.avaliadorcreditomicroservice.services.AvaliadorCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes-credito")
public class AvaliadorCreditoResource {

    @Autowired
    private AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping("/status")
    public String status(){
        return "ok";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf) {
        SituacaoClienteDto situacaoClienteDto = null;
        try {
            situacaoClienteDto = avaliadorCreditoService.obterSituacaoCLiente(cpf);
            return ResponseEntity.ok(situacaoClienteDto);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroserviceException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
