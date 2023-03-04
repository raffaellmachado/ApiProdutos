package br.com.bling.ApiContatos.controllers;

import br.com.bling.ApiContatos.controllers.request.*;
import br.com.bling.ApiContatos.controllers.response.*;
import br.com.bling.ApiContatos.exceptions.ContatoIdNaoEncontradoException;
import br.com.bling.ApiContatos.exceptions.ContatoListaNaoEncontradoException;
import br.com.bling.ApiContatos.exceptions.ContatoCadastroException;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ContatoControllerTest {

    @Mock
    ContatoService contatosService;
    @InjectMocks
    ContatoController contatoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * TESTE CONTROLLER - GET "BUSCAR A LISTA DE CONTATOS CADASTRADOS NO BLING".
     */
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
        contato1.contato.situacao = "A";
        contato1.contato.contribuinte = "9";
        contato1.contato.site = "www.teste.com.br";
        contato1.contato.celular = "(43) 99620-9999";
        contato1.contato.dataAlteracao = "08/07/1990";
        contato1.contato.dataInclusao = "08/07/1991";
        contato1.contato.sexo = "M";
        contato1.contato.clienteDesde = "01/01/2023";
        contato1.contato.limiteCredito = "0.00";
        contato1.contato.dataNascimento = "08/08/1998";

        contato1.contato.tiposContato = new ArrayList<>(); // Criar lista vazia para evitar NullPointerException
        TiposContatoResponse tiposContatoResponse1 = new TiposContatoResponse();

        tiposContatoResponse1.tipoContato = new TipoContatoResponse();
        tiposContatoResponse1.tipoContato.descricao = "Vendedor";
        contato1.contato.tiposContato.add(tiposContatoResponse1);

        // DEPOSITO TESTE 02
        RetornoResponse.Contatos contato2 = new RetornoResponse.Contatos();
        contato2.contato = new ContatoResponse();
        contato1.contato.id = "2";
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
        contato2.contato.situacao = "A";
        contato2.contato.contribuinte = "9";
        contato2.contato.site = "www.teste.com.br";
        contato2.contato.celular = "(43) 99620-9999";
        contato2.contato.dataAlteracao = "08/07/1990";
        contato2.contato.dataInclusao = "08/07/1991";
        contato2.contato.sexo = "M";
        contato2.contato.clienteDesde = "01/01/2023";
        contato2.contato.limiteCredito = "0.00";
        contato2.contato.dataNascimento = "08/08/1998";

        contato2.contato.tiposContato = new ArrayList<>(); // Criar lista vazia para evitar NullPointerException
        TiposContatoResponse tiposContatoResponse2 = new TiposContatoResponse();

        tiposContatoResponse2.tipoContato = new TipoContatoResponse();
        tiposContatoResponse2.tipoContato.descricao = "Vendedor";
        contato2.contato.tiposContato.add(tiposContatoResponse2);

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
    /**
     * TESTE CONTROLLER - GET "FORÇA O METODO BUSCAR A LISTA DE CONTATOS A ENTRAR NO EXCEPTION".
     */
    @Test
    void testGetAllContactsException() {

        when(contatosService.getAllContacts()).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(ContatoListaNaoEncontradoException.class, () -> {
            contatoController.getAllContacts();
        });

        // Verifica se o serviço foi chamado
        verify(contatosService).getAllContacts();
    }

    /**
     * TESTE CONTROLLER - GET "BUSCA CONTATO PELO CPF_CNPJ".
     */
    @Test
    void testGetContactsById() {
            String idContato = "825";

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
            contato.contato.situacao = "A";
            contato.contato.contribuinte = "9";
            contato.contato.site = "www.teste.com.br";
            contato.contato.celular = "(43) 99620-9999";
            contato.contato.dataAlteracao = "08/07/1990";
            contato.contato.dataInclusao = "08/07/1991";
            contato.contato.sexo = "M";
            contato.contato.clienteDesde = "01/01/2023";
            contato.contato.limiteCredito = "0.00";
            contato.contato.dataNascimento = "08/08/1998";
            contato.contato.tiposContato = new ArrayList<>(); // Criar lista vazia para evitar NullPointerException
            TiposContatoResponse tiposContatoResponse = new TiposContatoResponse();
            tiposContatoResponse.tipoContato = new TipoContatoResponse();
            tiposContatoResponse.tipoContato.descricao = "Vendedor";
            contato.contato.tiposContato.add(tiposContatoResponse);

            contatos.add(contato);
            retorno.setContatos(contatos);
            resposta.setRetorno(retorno);

            Mockito.when(contatosService.getContactsById(idContato)).thenReturn(resposta);

            RespostaResponse result = contatoController.getContactsById(idContato);
            Assertions.assertEquals(resposta, result);

    }

    /**
     * TESTE CONTROLLER - GET "FORÇA O METODO BUSCA CONTATO PELO CPF_CNPJ A ENTRAR NO EXCEPTION".
     */
    @Test
    void testGetContactsByIdException() {
        String cpf_cnpj = "066.852.259-70";
        when(contatosService.getContactsById(cpf_cnpj)).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(ContatoIdNaoEncontradoException.class, () -> {
            contatoController.getContactsById(cpf_cnpj);
        });

        // Verifica se o serviço foi chamado
        verify(contatosService).getContactsById(cpf_cnpj);
    }

    /**
     * TESTE CONTROLLER - POST "CADASTRA UM NOVO CONTATO UTILIZANDO XML/JSON".
     */
    @Test
    void testCreateContact() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<contato>\n" +
                "   <nome>Rafael</nome>\n" +
                "   <fantasia>RMS</fantasia>\n" +
                "   <tipoPessoa>F</tipoPessoa>\n" +
                "   <contribuinte>9</contribuinte> \n" +
                "   <cpf_cnpj>000.000.000-00</cpf_cnpj>\n" +
                "   <ie_rg>00.000.000-0</ie_rg>\n" +
                "   <endereco>Rua Borba Gato</endereco>\n" +
                "   <numero>1000</numero>\n" +
                "   <complemento>503</complemento>\n" +
                "   <bairro>Centro</bairro>\n" +
                "   <cep>86000-100</cep>\n" +
                "   <cidade>Londrina</cidade>\n" +
                "   <uf>PR</uf>\n" +
                "   <fone>(99) 9999-9999</fone>\n" +
                "   <celular>(43) 99620-9999</celular>\n" +
                "   <email>teste@teste.com</email>\n" +
                "   <emailNfe>testeNfe@mail.com.br</emailNfe>\n" +
                "   <informacaoContato>Informações adicionais do contato</informacaoContato>\n" +
                "   <limiteCredito>0.00</limiteCredito>\n" +
                "   <site>http://www.teste.com.br</site>\n" +
                "</contato>";

        // Simula a resposta da chamada para o serviço de categoria
        RespostaRequest resposta = new RespostaRequest();
        RetornoRequest retorno = new RetornoRequest();

        ArrayList<ArrayList<RetornoRequest.Contatos>> contatos = new ArrayList<>();
        ArrayList<RetornoRequest.Contatos> contatosList = new ArrayList<>();
        RetornoRequest.Contatos contato = new RetornoRequest.Contatos();

        contato.contato = new ContatoRequest();
        contato.contato.id = "01";
        contato.contato.nome = "Rafael";
        contato.contato.fantasia = "RMS";
        contato.contato.tipoPessoa = "F";
        contato.contato.contribuinte = "1";
        contato.contato.cnpj = "00.000.000/0000-00";
        contato.contato.cpf_cnpj = "000.000.000-00";
        contato.contato.ie_rg = "00.000.000-0";
        contato.contato.endereco = "Rua Borba Gato";
        contato.contato.numero = "1000";
        contato.contato.complemento = "503";
        contato.contato.bairro = "Centro";
        contato.contato.cep = "86000-100";
        contato.contato.cidade = "Londrina";
        contato.contato.uf = "PR";
        contato.contato.fone = "Londrina";
        contato.contato.celular = "(43) 99620-9999";
        contato.contato.email = "teste@teste.com";
        contato.contato.emailNfe = "testeNfe@mail.com.br";
        contato.contato.informacaoContato = "Informação teste";
        contato.contato.limiteCredito = "0.00";
        contato.contato.paisOrigem = "Campo apenas para estrangeiro";
        contato.contato.codigo = "01";
        contato.contato.site = "http://www.teste.com.br";
        contato.contato.obs = "Observações";

        contatosList.add(contato);
        contatos.add(contatosList);
        retorno.setContatos(contatos);
        resposta.setRetorno(retorno);

        when(contatosService.createContact(xml)).thenReturn(resposta);

        RespostaRequest result = contatoController.createContact(xml);
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - POST "FORÇA O METODO DE CADASTRO DE CONTATO A ENTRAR NO EXCEPTION".
     */
    @Test
    void testCreateContactException() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<categorias>\n" +
                "     <categoria>\n" +
                "          <descricao>Calçado</descricao>\n" +
                "          <idCategoriaPai>0</idCategoriaPai>\n" +
                "      </categoria>\n" +
                "   </categorias>";


        // Cria um mock do serviço que retorna null
        when(contatosService.createContact(xml)).thenReturn(null);

        // Chama o método sendo testado
        assertThrows(ContatoCadastroException.class, () -> {
            contatoController.createContact(xml);
        });

        // Verifica se o serviço foi chamado
        verify(contatosService).createContact(xml);
    }


    @Test
    void testUpdateContact() {
//        when(contatosService.updateContact(anyString(), anyString())).thenReturn(new RespostaRequest());
//
//        RespostaRequest result = contatoController.updateContact("xml", "id");
//        Assertions.assertEquals(new RespostaRequest(), result);
    }
}