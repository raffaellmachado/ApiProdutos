package br.com.bling.ApiFormaPagamento.controllers;

import br.com.bling.ApiFormaPagamento.controllers.response.SelecionaLoja;
import br.com.bling.ApiFormaPagamento.controllers.response.SelecionaLojaResponse;
import br.com.bling.ApiFormaPagamento.service.FormaPagamentoService;
import br.com.bling.ApiFormaPagamento.service.SelecionaLojaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")          //Padrão para os métodos /api/...
@Api(value = "API REST FORMA PAGAMENTO")    //Swagger
@CrossOrigin(origins = "*")                 // Liberar os dominios da API
@Validated
public class SelecionaLojaController {

    @Autowired
    public SelecionaLojaService selecionaLojaService;

    @GetMapping("/selecionaLojas")
    public List<SelecionaLoja> getAllLojas() {
        return selecionaLojaService.getAllLojas();
    }

//    @GetMapping("/selecionaLoja/{idLoja}")
//    public SelecionaLoja getLojaById(@PathVariable String idLoja) {
//        return selecionaLojaService.getLojaById(idLoja);
//    }

//    @GetMapping("/selecionaLoja")
//    public SelecionaLoja saveLoja(@RequestBody SelecionaLoja selecionaLoja) {
//        return selecionaLojaService.saveLoja(selecionaLoja);
//    }

//    @PutMapping("/{idLoja}")
//    public SelecionaLoja updateLoja(@PathVariable String idLoja, @RequestBody SelecionaLoja selecionaLoja) {
//        selecionaLoja.setId(idLoja);
//        return selecionaLojaService.updateLoja(selecionaLoja);
//    }
}
