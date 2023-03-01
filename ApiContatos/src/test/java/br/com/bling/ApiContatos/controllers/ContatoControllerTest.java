package br.com.bling.ApiContatos.controllers;

import br.com.bling.ApiContatos.controllers.request.RespostaRequest;
import br.com.bling.ApiContatos.controllers.response.ContatoResponse;
import br.com.bling.ApiContatos.controllers.response.RespostaResponse;
import br.com.bling.ApiContatos.controllers.response.RetornoResponse;
import br.com.bling.ApiContatos.service.ContatoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ContatoControllerTest {
    @Mock
    RestTemplate restTemplate;
    @Mock
    ContatoService contatosService;
    @InjectMocks
    ContatoController contatoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllContacts() {

        // DEPOSITO TESTE 01
        RetornoResponse.Contatos contato1 = new RetornoResponse.Contatos();
        contato1.contato = new ContatoResponse();
        contato1.contato.id = "1";
        contato1.contato.codigo = "01";
        contato1.contato.nome = "Rafael";
        contato1.contato.fantasia = "RMS";
        contato1.contato.tipo = "F";
        contato1.contato.cnpj = "00.000.000/0000-00";
        contato1.contato.cpf_cnpj = "000.000.000-00";
        contato1.contato.ie_rg = "00.000.000-0";
        contato1.contato.endereco = "Rua Mato Grosso";
        contato1.contato.numero = "1893";
        contato1.contato.bairro = "Centro";
        contato1.contato.cep = "86010-180";
        contato1.contato.cidade = "Londrina";
        contato1.contato.complemento = "503";
        contato1.contato.uf = "PR";
        contato1.contato.fone = "Londrina";
        contato1.contato.email = "teste@teste.com";
        contato1.contato.situacao = "teste";
        contato1.contato.contribuinte = "1";
        contato1.contato.site = "www.teste.com.br";
        contato1.contato.celular = "(43) 99620-9999";
        contato1.contato.dataAlteracao = "08/07/1990";
        contato1.contato.dataInclusao = "08/07/1991";
        contato1.contato.sexo = "M";
        contato1.contato.clienteDesde = "01/01/2023";
        contato1.contato.limiteCredito = "0.00";
        contato1.contato.dataNascimento = "08/08/1998";

        // DEPOSITO TESTE 02
        RetornoResponse.Contatos contato2 = new RetornoResponse.Contatos();
        contato2.contato = new ContatoResponse();
        contato2.contato.id = "1";
        contato2.contato.codigo = "01";
        contato2.contato.nome = "Rafael";
        contato2.contato.fantasia = "RMS";
        contato2.contato.tipo = "F";
        contato2.contato.cnpj = "00.000.000/0000-00";
        contato2.contato.cpf_cnpj = "000.000.000-00";
        contato2.contato.ie_rg = "00.000.000-0";
        contato2.contato.endereco = "Rua Mato Grosso";
        contato2.contato.numero = "1893";
        contato2.contato.bairro = "Centro";
        contato2.contato.cep = "86010-180";
        contato2.contato.cidade = "Londrina";
        contato2.contato.complemento = "503";
        contato2.contato.uf = "PR";
        contato2.contato.fone = "Londrina";
        contato2.contato.email = "teste@teste.com";
        contato2.contato.situacao = "teste";
        contato2.contato.contribuinte = "1";
        contato2.contato.site = "www.teste.com.br";
        contato2.contato.celular = "(43) 99620-9999";
        contato2.contato.dataAlteracao = "08/07/1990";
        contato2.contato.dataInclusao = "08/07/1991";
        contato2.contato.sexo = "M";
        contato2.contato.clienteDesde = "01/01/2023";
        contato2.contato.limiteCredito = "0.00";
        contato2.contato.dataNascimento = "08/08/1998";

        RetornoResponse retorno = new RetornoResponse();
        retorno.contatos = new ArrayList<>();
        retorno.contatos.add(contato1);
        retorno.contatos.add(contato2);

        RespostaResponse resposta = new RespostaResponse();
        resposta.retorno = retorno;

        // Configura o comportamento do serviço simulado
        when(contatosService.getAllContacts()).thenReturn(resposta);

        // Chama o método sendo testado
        RespostaResponse result = contatoController.getAllContacts();

        // Verifica se o serviço simulado foi chamado corretamente e se o resultado foi o esperado
        verify(contatosService).getAllContacts();
        assertEquals(resposta, result);
    }

    @Test
    void testGetContactsById() {
        String idContato = "123";

        RespostaResponse resposta = new RespostaResponse();
        RetornoResponse retorno = new RetornoResponse();

        ArrayList<RetornoResponse.Contatos> contatos = new ArrayList<>();
        RetornoResponse.Contatos contato = new RetornoResponse.Contatos();

        contato.contato = new ContatoResponse();
        contato.contato.id = idContato;
        contato.contato.codigo = "01";
        contato.contato.nome = "Rafael";
        contato.contato.fantasia = "RMS";
        contato.contato.tipo = "F";
        contato.contato.cnpj = "00.000.000/0000-00";
        contato.contato.cpf_cnpj = "000.000.000-00";
        contato.contato.ie_rg = "00.000.000-0";
        contato.contato.endereco = "Rua Mato Grosso";
        contato.contato.numero = "1893";
        contato.contato.bairro = "Centro";
        contato.contato.cep = "86010-180";
        contato.contato.cidade = "Londrina";
        contato.contato.complemento = "503";
        contato.contato.uf = "PR";
        contato.contato.fone = "Londrina";
        contato.contato.email = "teste@teste.com";
        contato.contato.situacao = "teste";
        contato.contato.contribuinte = "1";
        contato.contato.site = "www.teste.com.br";
        contato.contato.celular = "(43) 99620-9999";
        contato.contato.dataAlteracao = "08/07/1990";
        contato.contato.dataInclusao = "08/07/1991";
        contato.contato.sexo = "M";
        contato.contato.clienteDesde = "01/01/2023";
        contato.contato.limiteCredito = "0.00";
        contato.contato.dataNascimento = "08/08/1998";

        contatos.add(contato);
        retorno.setContatos(contatos);
        resposta.setRetorno(retorno);

        Mockito.when(contatosService.getContactsById(idContato)).thenReturn(resposta);

        RespostaResponse result = contatoController.getContactsById(idContato);
        Assertions.assertEquals(resposta, result);
    }

    @Test
    void testCreateContact() {
//        when(contatosService.createContact(anyString())).thenReturn(new RespostaRequest());
//
//        RespostaRequest result = contatoController.createContact("xml");
//        Assertions.assertEquals(new RespostaRequest(), result);
    }

    @Test
    void testUpdateContact() {
//        when(contatosService.updateContact(anyString(), anyString())).thenReturn(new RespostaRequest());
//
//        RespostaRequest result = contatoController.updateContact("xml", "id");
//        Assertions.assertEquals(new RespostaRequest(), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme