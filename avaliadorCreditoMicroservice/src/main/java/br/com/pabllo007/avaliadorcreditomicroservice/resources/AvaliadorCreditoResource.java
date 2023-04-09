package br.com.pabllo007.avaliadorcreditomicroservice.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes-credito")
public class AvaliadorCreditoResource {

    @GetMapping("/status")
    public String status(){
        return "ok";
    }
}
