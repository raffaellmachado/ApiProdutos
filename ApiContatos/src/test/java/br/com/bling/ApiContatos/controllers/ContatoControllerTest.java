package br.com.bling.ApiContatos.controllers;

import br.com.bling.ApiContatos.controllers.request.*;
import br.com.bling.ApiContatos.controllers.response.*;
import br.com.bling.ApiContatos.exceptions.ApiContatoException;
import br.com.bling.ApiContatos.exceptions.DetalhesErroResponse;
import br.com.bling.ApiContatos.exceptions.ErroResponse;
import br.com.bling.ApiContatos.service.ContatoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        // CONTATO TESTE 01
        RetornoResponse.Contatos contato1 = new RetornoResponse.Contatos();
        contato1.contato = new ContatoResponse();
        contato1.contato.setId(Long.valueOf("01"));
        contato1.contato.setCodigo("01");
        contato1.contato.setNome("Eduardo");
        contato1.contato.setFantasia("RMS");
        contato1.contato.setTipo("F");
        contato1.contato.setCnpj("00.000.000/0000-00");
//        contato1.contato.setCpf_cnpj("000.000.000-00");
        contato1.contato.setIe_rg("00.000.000-0");
        contato1.contato.setEndereco("Rua das Oliveirass");
        contato1.contato.setNumero("1200");
        contato1.contato.setBairro("Centro");
        contato1.contato.setCep("86090-180");
        contato1.contato.setCidade("Londrina");
        contato1.contato.setComplemento("503");
        contato1.contato.setUf("PR");
        contato1.contato.setFone("(43) 99999-9999)");
        contato1.contato.setEmail("teste@teste.com");
        contato1.contato.setSituacao("A");
        contato1.contato.setContribuinte("9");
        contato1.contato.setSite("www.teste.com.br");
        contato1.contato.setCelular("(43) 99620-9999");
        contato1.contato.setDataAlteracao("04/03/2023");
        contato1.contato.setDataInclusao("04/03/2023");
        contato1.contato.setSexo("M");
        contato1.contato.setClienteDesde("01/01/2023");
        contato1.contato.setLimiteCredito("0.00");
        contato1.contato.setDataNascimento("08/08/1990");
        /*-------------`Tipo Contaato`-------------*/
        contato1.contato.setTiposContato(new ArrayList<>()); // Criar lista vazia para evitar NullPointerException
        TiposContatoResponse tiposContatoResponse1 = new TiposContatoResponse();
        tiposContatoResponse1.setTipoContato(new TipoContatoResponse());
        tiposContatoResponse1.tipoContato.setDescricao("Vendedor");
        contato1.contato.tiposContato.add(tiposContatoResponse1);
        /*-----------------------------------------*/

        // CONTATO TESTE 02
        RetornoResponse.Contatos contato2 = new RetornoResponse.Contatos();
        contato2.contato = new ContatoResponse();
        contato2.contato.setId(Long.valueOf("02"));
        contato2.contato.setCodigo("01");
        contato2.contato.setNome("Eduardo");
        contato2.contato.setFantasia("RMS");
        contato2.contato.setTipo("F");
        contato2.contato.setCnpj("00.000.000/0000-00");
//        contato2.contato.setCpf_cnpj("000.000.000-00");
        contato2.contato.setIe_rg("00.000.000-0");
        contato2.contato.setEndereco("Rua das Oliveirass");
        contato2.contato.setNumero("1200");
        contato2.contato.setBairro("Centro");
        contato2.contato.setCep("86090-180");
        contato2.contato.setCidade("Londrina");
        contato2.contato.setComplemento("503");
        contato2.contato.setUf("PR");
        contato2.contato.setFone("(43) 99999-9999)");
        contato2.contato.setEmail("teste@teste.com");
        contato2.contato.setSituacao("A");
        contato2.contato.setContribuinte("9");
        contato2.contato.setSite("www.teste.com.br");
        contato2.contato.setCelular("(43) 99620-9999");
        contato2.contato.setDataAlteracao("04/03/2023");
        contato2.contato.setDataInclusao("04/03/2023");
        contato2.contato.setSexo("M");
        contato2.contato.setClienteDesde("01/01/2023");
        contato2.contato.setLimiteCredito("0.00");
        contato2.contato.setDataNascimento("08/08/1990");
        /*-------------`Tipo Contaato`-------------*/
        contato2.contato.setTiposContato(new ArrayList<>()); // Criar lista vazia para evitar NullPointerException
        TiposContatoResponse tiposContatoResponse2 = new TiposContatoResponse();
        tiposContatoResponse2.setTipoContato(new TipoContatoResponse());
        tiposContatoResponse2.tipoContato.setDescricao("Vendedor");
        contato2.contato.tiposContato.add(tiposContatoResponse2);
        /*-----------------------------------------*/

        RetornoResponse retorno = new RetornoResponse();
        retorno.contatos = new ArrayList<>();
        retorno.contatos.add(contato1);
        retorno.contatos.add(contato2);

        JsonResponse resposta = new JsonResponse();
        resposta.retorno = retorno;

        // Configura o comportamento do serviço simulado
        when(contatosService.getAllContacts()).thenReturn(resposta);

        // Chama o método sendo testado
        JsonResponse result = contatoController.getAllContacts().getBody();

        // Verifica se o serviço simulado foi chamado corretamente e se o resultado foi o esperado
        verify(contatosService).getAllContacts();
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - GET "FORÇA O METODO BUSCAR A LISTA DE CONTATOS A ENTRAR NO EXCEPTION".
     */
    @Test
    void testGetAllContactsException() {
        JsonResponse jsonResponse = new JsonResponse();
        RetornoResponse retornoResponse = new RetornoResponse();

        retornoResponse.setContatos(null);
        retornoResponse.setErros(null);
        jsonResponse.setRetorno(retornoResponse);

        when(contatosService.getAllContacts()).thenReturn(jsonResponse);

        // Act
        assertThrows(ApiContatoException.class, () -> contatoController.getAllContacts());
    }

    /**
     * TESTE CONTROLLER - GET "BUSCA CONTATO PELO CPF_CNPJ".
     */
    @Test
    void testGetContactsById() {
        String idContato = "825";

        JsonResponse resposta = new JsonResponse();
        RetornoResponse retorno = new RetornoResponse();

        ArrayList<RetornoResponse.Contatos> contatos = new ArrayList<>();
        RetornoResponse.Contatos contato = new RetornoResponse.Contatos();

        contato.contato = new ContatoResponse();
        contato.contato.setId(Long.valueOf(idContato));
        contato.contato.setCodigo("01");
        contato.contato.setNome("Eduardo");
        contato.contato.setFantasia("RMS");
        contato.contato.setTipo("F");
        contato.contato.setCnpj("00.000.000/0000-00");
//        contato.contato.setCpf_cnpj("000.000.000-00");
        contato.contato.setIe_rg("00.000.000-0");
        contato.contato.setEndereco("Rua das Oliveirass");
        contato.contato.setNumero("1200");
        contato.contato.setBairro("Centro");
        contato.contato.setCep("86090-180");
        contato.contato.setCidade("Londrina");
        contato.contato.setComplemento("503");
        contato.contato.setUf("PR");
        contato.contato.setFone("(43) 99999-9999)");
        contato.contato.setEmail("teste@teste.com");
        contato.contato.setSituacao("A");
        contato.contato.setContribuinte("9");
        contato.contato.setSite("www.teste.com.br");
        contato.contato.setCelular("(43) 99620-9999");
        contato.contato.setDataAlteracao("04/03/2023");
        contato.contato.setDataInclusao("04/03/2023");
        contato.contato.setSexo("M");
        contato.contato.setClienteDesde("01/01/2023");
        contato.contato.setLimiteCredito("0.00");
        contato.contato.setDataNascimento("08/08/1990");
        /*-------------`Tipo Contaato`-------------*/
        contato.contato.setTiposContato(new ArrayList<>()); // Criar lista vazia para evitar NullPointerException
        TiposContatoResponse tiposContatoResponseId = new TiposContatoResponse();
        tiposContatoResponseId.setTipoContato(new TipoContatoResponse());
        tiposContatoResponseId.tipoContato.setDescricao("Vendedor");
        contato.contato.tiposContato.add(tiposContatoResponseId);
        /*-----------------------------------------*/

        contatos.add(contato);
        retorno.setContatos(contatos);
        resposta.setRetorno(retorno);

        Mockito.when(contatosService.getContactsById(idContato)).thenReturn(resposta);

        JsonResponse result = contatoController.getContactsById(idContato).getBody();
        Assertions.assertEquals(resposta, result);

    }

    /**
     * TESTE CONTROLLER - GET "FORÇA O METODO BUSCA CONTATO PELO CPF_CNPJ A ENTRAR NO EXCEPTIONv".
     */
    @Test
    public void testGetContactsByIdThrowsException() throws Exception {
        String cpf_cnpj = "1234";
        JsonResponse jsonResponse = new JsonResponse();
        RetornoResponse retornoResponse = new RetornoResponse();

        retornoResponse.setContatos(null);
        retornoResponse.setErros(null);
        jsonResponse.setRetorno(retornoResponse);

        when(contatosService.getContactsById(cpf_cnpj)).thenReturn(jsonResponse);

        // Act
        assertThrows(ApiContatoException.class, () -> contatoController.getContactsById(cpf_cnpj));
    }

    /**
     * TESTE CONTROLLER - POST "CADASTRA UM NOVO CONTATO UTILIZANDO XML/JSON".
     */
    @Test
    void testCreateContact() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<contato>\n" +
                "   <nome>Eduardo</nome>\n" +
                "   <fantasia>RMS</fantasia>\n" +
                "   <tipoPessoa>F</tipoPessoa>\n" +
                "   <contribuinte>9</contribuinte> \n" +
                "   <cpf_cnpj>000.000.000-00</cpf_cnpj>\n" +
                "   <ie_rg>00.000.000-0</ie_rg>\n" +
                "   <endereco>Rua das Oliveirass</endereco>\n" +
                "   <numero>1200</numero>\n" +
                "   <complemento>503</complemento>\n" +
                "   <bairro>Centro</bairro>\n" +
                "   <cep>00000-000</cep>\n" +
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
        JsonRequest resposta = new JsonRequest();
        RetornoRequest retorno = new RetornoRequest();

        ArrayList<ArrayList<RetornoRequest.Contatos>> contatos = new ArrayList<>();
        ArrayList<RetornoRequest.Contatos> contatosList = new ArrayList<>();
        RetornoRequest.Contatos contato = new RetornoRequest.Contatos();

        contato.contato = new ContatoRequest();
        contato.contato.setId(Long.valueOf("159"));
        contato.contato.setNome("Eduardo");
        contato.contato.setFantasia("RMS");
        contato.contato.setTipoPessoa("F");
        contato.contato.setContribuinte(9);
//        contato.contato.setCnpj("00.000.000/0000-00");
        contato.contato.setCpf_cnpj("000.000.000-00");
        contato.contato.setIe_rg("00.000.000-0");
        contato.contato.setEndereco("Rua das Oliveirass");
        contato.contato.setNumero("1200");
        contato.contato.setComplemento("503");
        contato.contato.setBairro("Centro");
        contato.contato.setCep("00000-000");
        contato.contato.setCidade("Londrina");
        contato.contato.setUf("PR");
        contato.contato.setFone("(43) 99999-9999");
        contato.contato.setCelular("(43) 99620-9999");
        contato.contato.setEmail("teste@teste.com");
        contato.contato.setEmailNfe("testeNfe@mail.com.br");
        contato.contato.setInformacaoContato("Informações adicionais do contato");
        contato.contato.setLimiteCredito(BigDecimal.valueOf(0.00));
        contato.contato.setPaisOrigem("Brasil");
        contato.contato.setCodigo("01");
        contato.contato.setSite("http://www.teste.com.br");
        contato.contato.setObs("Observação teste");

        contato.contato.setTiposContato(new ArrayList<>()); // Criar lista vazia para evitar NullPointerException
        TiposContatoRequest tiposContatoRequest = new TiposContatoRequest();
        tiposContatoRequest.setTipoContato(new TipoContatoRequest());
        tiposContatoRequest.tipoContato.setDescricao("Vendedor");
        contato.contato.tiposContato.add(tiposContatoRequest);

        contatosList.add(contato);
        contatos.add(contatosList);
        retorno.setContatos(contatos);
        resposta.setRetorno(retorno);

        when(contatosService.createContact(xml)).thenReturn(resposta);

        JsonRequest result = contatoController.createContact(xml).getBody();
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - POST "FORÇA O METODO DE CADASTRO DE CONTATO A ENTRAR NO EXCEPTION".
     */
    @Test
    void testCreateContactException() {
        // Cria o XML de categoria a ser enviado na requisição
        String xml = "<contato>\n" +
                "   <nome>Eduardo</nome>\n" +
                "   <fantasia>RMS</fantasia>\n" +
                "   <tipoPessoa>F</tipoPessoa>\n" +
                "   <contribuinte>9</contribuinte> \n" +
                "   <cpf_cnpj>000.000.000-00</cpf_cnpj>\n" +
                "   <ie_rg>00.000.000-0</ie_rg>\n" +
                "   <endereco>Rua das Oliveirass</endereco>\n" +
                "   <numero>1200</numero>\n" +
                "   <complemento>503</complemento>\n" +
                "   <bairro>Centro</bairro>\n" +
                "   <cep>00000-000</cep>\n" +
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


        JsonRequest jsonRequest = new JsonRequest();
        RetornoRequest retornoRequest = new RetornoRequest();

        retornoRequest.setContatos(null);
        retornoRequest.setErros(null);
        jsonRequest.setRetorno(retornoRequest);

        when(contatosService.createContact(xml)).thenReturn(jsonRequest);

        assertThrows(ApiContatoException.class, () -> contatoController.createContact(xml));
    }

    /**
     * TESTE CONTROLLER - PUT "CADASTRA UM NOVO CONTATO UTILIZANDO XML".
     */
    @Test
    void testUpdateContact() {
        // Cria o XML de categoria a ser enviado na requisição
        String cpf_cnpj = "000.000.000-00";
        String xml = "<contato>\n" +
                "   <nome>Eduardo</nome>\n" +
                "   <fantasia>RMS</fantasia>\n" +
                "   <tipoPessoa>F</tipoPessoa>\n" +
                "   <contribuinte>9</contribuinte> \n" +
                "   <cpf_cnpj>000.000.000-00</cpf_cnpj>\n" +
                "   <ie_rg>00.000.000-0</ie_rg>\n" +
                "   <endereco>Rua das Oliveirass</endereco>\n" +
                "   <numero>1200</numero>\n" +
                "   <complemento>503</complemento>\n" +
                "   <bairro>Centro</bairro>\n" +
                "   <cep>00000-000</cep>\n" +
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
        JsonRequest resposta = new JsonRequest();
        RetornoRequest retorno = new RetornoRequest();

        ArrayList<ArrayList<RetornoRequest.Contatos>> contatos = new ArrayList<>();
        ArrayList<RetornoRequest.Contatos> contatosList = new ArrayList<>();
        RetornoRequest.Contatos contato = new RetornoRequest.Contatos();

        contato.contato = new ContatoRequest();
        contato.contato.setId(Long.valueOf("159"));
        contato.contato.setNome("Eduardo");
        contato.contato.setFantasia("RMS");
        contato.contato.setTipoPessoa("F");
        contato.contato.setContribuinte(9);
//        contato.contato.setCnpj("00.000.000/0000-00");
        contato.contato.setCpf_cnpj(cpf_cnpj);
        contato.contato.setIe_rg("00.000.000-0");
        contato.contato.setEndereco("Rua das Oliveirass");
        contato.contato.setNumero("1200");
        contato.contato.setComplemento("503");
        contato.contato.setBairro("Centro");
        contato.contato.setCep("00000-000");
        contato.contato.setCidade("Londrina");
        contato.contato.setUf("PR");
        contato.contato.setFone("(43) 99999-9999");
        contato.contato.setCelular("(43) 99620-9999");
        contato.contato.setEmail("teste@teste.com");
        contato.contato.setEmailNfe("testeNfe@mail.com.br");
        contato.contato.setInformacaoContato("Informações adicionais do contato");
        contato.contato.setLimiteCredito(BigDecimal.valueOf(0.00));
        contato.contato.setPaisOrigem("Brasil");
        contato.contato.setCodigo("01");
        contato.contato.setSite("http://www.teste.com.br");
        contato.contato.setObs("Observação teste");

        contato.contato.setTiposContato(new ArrayList<>()); // Criar lista vazia para evitar NullPointerException
        TiposContatoRequest tiposContatoRequest = new TiposContatoRequest();
        tiposContatoRequest.setTipoContato(new TipoContatoRequest());
        tiposContatoRequest.tipoContato.setDescricao("Vendedor");
        contato.contato.tiposContato.add(tiposContatoRequest);

        contatosList.add(contato);
        contatos.add(contatosList);
        retorno.setContatos(contatos);
        resposta.setRetorno(retorno);

        when(contatosService.updateContact(xml, cpf_cnpj)).thenReturn(resposta);

        JsonRequest result = contatoController.updateContact(xml,cpf_cnpj).getBody();
        assertEquals(resposta, result);
    }

    /**
     * TESTE CONTROLLER - PUT "FORÇA O METODO DE CADASTRO DE CONTATO A ENTRAR NO EXCEPTION".
     */
    @Test
    void testUpdateContactException() {
        String cpf_cnpj = "000.000.000-00";
        String xml = "<contato>\n" +
                "   <nome>Eduardo</nome>\n" +
                "   <fantasia>RMS</fantasia>\n" +
                "   <tipoPessoa>F</tipoPessoa>\n" +
                "   <contribuinte>9</contribuinte> \n" +
                "   <cpf_cnpj>000.000.000-00</cpf_cnpj>\n" +
                "   <ie_rg>00.000.000-0</ie_rg>\n" +
                "   <endereco>Rua das Oliveirass</endereco>\n" +
                "   <numero>1200</numero>\n" +
                "   <complemento>503</complemento>\n" +
                "   <bairro>Centro</bairro>\n" +
                "   <cep>00000-000</cep>\n" +
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


        JsonRequest jsonRequest = new JsonRequest();
        RetornoRequest retornoRequest = new RetornoRequest();

        retornoRequest.setContatos(null);
        retornoRequest.setErros(null);
        jsonRequest.setRetorno(retornoRequest);

        when(contatosService.updateContact(xml, cpf_cnpj)).thenReturn(jsonRequest);

        assertThrows(ApiContatoException.class, () -> contatoController.updateContact(xml, cpf_cnpj));
    }

    /**
     * TESTE CONTROLLER - GET "TESTE DO ERROS MAPEADOS QUE RETORNA DA API EXTERNA".
     */
    @Test
    void testErroResponse() throws JsonProcessingException {
        int codigoErro = 404;
        String mensagemErro = "A informação desejada não foi encontrada.";

        DetalhesErroResponse detalhesErro = new DetalhesErroResponse(codigoErro, mensagemErro);
        ErroResponse erroResponse = new ErroResponse();
        erroResponse.setErro(detalhesErro);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(erroResponse);

        ErroResponse erroResponseDeserialized = objectMapper.readValue(json, ErroResponse.class);

        assertEquals(erroResponse, erroResponseDeserialized);
    }

}