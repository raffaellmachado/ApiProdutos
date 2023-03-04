package br.com.bling.ApiContatos.service;

import br.com.bling.ApiContatos.controllers.request.RespostaRequest;
import br.com.bling.ApiContatos.controllers.response.RespostaResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

class ContatoServiceImplTest {
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    ContatoServiceImpl contatoServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllContacts() throws Exception {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\": {\"contatos\": [{\"contato\": {\"id\": \"01\", \"nome\": \"Nome do contato\", \"fantasia\": \"Contato Corp\", \"tipo\": \"F\", \"contribuinte\": \"02\", \"cpf_cnpj\": \"11.111.111/1111-11\", \"ie_rg\": \"146849932111\", \"endereco\": \"Rua João Moura Teste\", \"numero\": \"1251\", \"complemento\": \"null\", \"bairro\": \"Pinheiros\", \"cep\": \"05.412-001\", \"cidade\": \"São Paulo\", \"uf\": \"SP\", \"fone\": \"(11) 999999999\", \"email\": \"contato@teste.com\", \"limiteCredito\": \"0.00\"}}, {\"contato\": {\"id\": \"02\", \"nome\": \"Rafael\", \"fantasia\": \"RMS\", \"tipo\": \"F\", \"contribuinte\": \"02\", \"cpf_cnpj\": \"066.866.529-70\", \"ie_rg\": \"10.419.683-7\", \"endereco\": \"Mato Grosso\", \"numero\": \"1893\", \"complemento\": \"503\", \"bairro\": \"Centro\", \"cep\": \"86010-180\", \"cidade\": \"Londrina\", \"uf\": \"PR\", \"fone\": \"(43)996209269\", \"email\": \"rafael.machado@okeaatecnologia.com.br\", \"limiteCredito\": \"0.00\"}}]}}";
        Mockito.when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaResponse
        RespostaResponse result = contatoServiceImpl.getAllContacts();

        // Verifica se a lista de categorias foi corretamente convertida a partir da resposta da API
        Assertions.assertEquals(2, result.getRetorno().getContatos().size());
        Assertions.assertEquals("01", result.getRetorno().getContatos().get(0).getContato().getId());
        Assertions.assertEquals("Nome do contato", result.getRetorno().getContatos().get(0).getContato().getNome());
        Assertions.assertEquals("Contato Corp", result.getRetorno().getContatos().get(0).getContato().getFantasia());
        Assertions.assertEquals("F", result.getRetorno().getContatos().get(0).getContato().getTipo());
        Assertions.assertEquals("02", result.getRetorno().getContatos().get(0).getContato().getContribuinte());
        Assertions.assertEquals("11.111.111/1111-11", result.getRetorno().getContatos().get(0).getContato().getCpf_cnpj());
        Assertions.assertEquals("146849932111", result.getRetorno().getContatos().get(0).getContato().getIe_rg());
        Assertions.assertEquals("Rua João Moura Teste", result.getRetorno().getContatos().get(0).getContato().getEndereco());
        Assertions.assertEquals("1251", result.getRetorno().getContatos().get(0).getContato().getNumero());
        Assertions.assertEquals("null", result.getRetorno().getContatos().get(0).getContato().getComplemento());
        Assertions.assertEquals("Pinheiros", result.getRetorno().getContatos().get(0).getContato().getBairro());
        Assertions.assertEquals("05.412-001", result.getRetorno().getContatos().get(0).getContato().getCep());
        Assertions.assertEquals("São Paulo", result.getRetorno().getContatos().get(0).getContato().getCidade());
        Assertions.assertEquals("SP", result.getRetorno().getContatos().get(0).getContato().getUf());
        Assertions.assertEquals("(11) 999999999", result.getRetorno().getContatos().get(0).getContato().getFone());
        Assertions.assertEquals("contato@teste.com", result.getRetorno().getContatos().get(0).getContato().getEmail());
        Assertions.assertEquals("0.00", result.getRetorno().getContatos().get(0).getContato().getLimiteCredito());

        // LISTA DE CONTATO 2
        Assertions.assertEquals("02", result.getRetorno().getContatos().get(1).getContato().getId());
        Assertions.assertEquals("Rafael", result.getRetorno().getContatos().get(1).getContato().getNome());
        Assertions.assertEquals("RMS", result.getRetorno().getContatos().get(1).getContato().getFantasia());
        Assertions.assertEquals("F", result.getRetorno().getContatos().get(1).getContato().getTipo());
        Assertions.assertEquals("02", result.getRetorno().getContatos().get(1).getContato().getContribuinte());
        Assertions.assertEquals("066.866.529-70", result.getRetorno().getContatos().get(1).getContato().getCpf_cnpj());
        Assertions.assertEquals("10.419.683-7", result.getRetorno().getContatos().get(1).getContato().getIe_rg());
        Assertions.assertEquals("Mato Grosso", result.getRetorno().getContatos().get(1).getContato().getEndereco());
        Assertions.assertEquals("1893", result.getRetorno().getContatos().get(1).getContato().getNumero());
        Assertions.assertEquals("503", result.getRetorno().getContatos().get(1).getContato().getComplemento());
        Assertions.assertEquals("Centro", result.getRetorno().getContatos().get(1).getContato().getBairro());
        Assertions.assertEquals("86010-180", result.getRetorno().getContatos().get(1).getContato().getCep());
        Assertions.assertEquals("Londrina", result.getRetorno().getContatos().get(1).getContato().getCidade());
        Assertions.assertEquals("PR", result.getRetorno().getContatos().get(1).getContato().getUf());
        Assertions.assertEquals("(43)996209269", result.getRetorno().getContatos().get(1).getContato().getFone());
        Assertions.assertEquals("rafael.machado@okeaatecnologia.com.br", result.getRetorno().getContatos().get(1).getContato().getEmail());
        Assertions.assertEquals("0.00", result.getRetorno().getContatos().get(1).getContato().getLimiteCredito());

        System.out.println("GET LIST: " + result);
    }



    @Test
    void testGetContactsById() throws Exception {

        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\": {\"contatos\": [{\"contato\": {\"id\": \"01\", \"nome\": \"Nome do contato\", \"fantasia\": \"Contato Corp\", \"tipo\": \"F\", \"contribuinte\": \"02\", \"cpf_cnpj\": \"11.111.111/1111-11\", \"ie_rg\": \"146849932111\", \"endereco\": \"Rua João Moura Teste\", \"numero\": \"1251\", \"complemento\": \"null\", \"bairro\": \"Pinheiros\", \"cep\": \"05.412-001\", \"cidade\": \"São Paulo\", \"uf\": \"SP\", \"fone\": \"(11) 999999999\", \"email\": \"contato@teste.com\", \"limiteCredito\": \"0.00\"}}]}}";
        Mockito.when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaResponse
        RespostaResponse result = contatoServiceImpl.getContactsById("11.111.111/1111-11");

        // Verifica se a categoria foi corretamente convertida a partir da resposta da API
        Assertions.assertEquals("01", result.getRetorno().getContatos().get(0).getContato().getId());
        Assertions.assertEquals("Nome do contato", result.getRetorno().getContatos().get(0).getContato().getNome());
        Assertions.assertEquals("Contato Corp", result.getRetorno().getContatos().get(0).getContato().getFantasia());
        Assertions.assertEquals("F", result.getRetorno().getContatos().get(0).getContato().getTipo());
        Assertions.assertEquals("02", result.getRetorno().getContatos().get(0).getContato().getContribuinte());
        Assertions.assertEquals("11.111.111/1111-11", result.getRetorno().getContatos().get(0).getContato().getCpf_cnpj());
        Assertions.assertEquals("146849932111", result.getRetorno().getContatos().get(0).getContato().getIe_rg());
        Assertions.assertEquals("Rua João Moura Teste", result.getRetorno().getContatos().get(0).getContato().getEndereco());
        Assertions.assertEquals("1251", result.getRetorno().getContatos().get(0).getContato().getNumero());
        Assertions.assertEquals("null", result.getRetorno().getContatos().get(0).getContato().getComplemento());
        Assertions.assertEquals("Pinheiros", result.getRetorno().getContatos().get(0).getContato().getBairro());
        Assertions.assertEquals("05.412-001", result.getRetorno().getContatos().get(0).getContato().getCep());
        Assertions.assertEquals("São Paulo", result.getRetorno().getContatos().get(0).getContato().getCidade());
        Assertions.assertEquals("SP", result.getRetorno().getContatos().get(0).getContato().getUf());
        Assertions.assertEquals("(11) 999999999", result.getRetorno().getContatos().get(0).getContato().getFone());
        Assertions.assertEquals("contato@teste.com", result.getRetorno().getContatos().get(0).getContato().getEmail());
        Assertions.assertEquals("0.00", result.getRetorno().getContatos().get(0).getContato().getLimiteCredito());


        System.out.println("GET ID: " + result);
    }

    @Test
    void testCreateContact() throws Exception {
        // Simula a resposta da chamada para a API externa
        String jsonResponse = "{\"retorno\":{\"contatos\":[{\"contato\":{\"id\":\"01\",\"nome\":\"Nome do contato\",\"fantasia\":\"Contato Corp\",\"tipoPessoa\":\"F\",\"contribuinte\":\"9\",\"cpf_cnpj\":\"11.111.111/1111-11\",\"ie_rg\":\"146849932111\",\"endereco\":\"Rua João Moura Teste\",\"numero\":\"1251\",\"complemento\":\"null\",\"bairro\":\"Pinheiros\",\"cep\":\"05.412-001\",\"cidade\":\"São Paulo\",\"uf\":\"SP\",\"fone\":\"(11) 999999999\",\"email\":\"contato@teste.com\",\"limiteCredito\":\"9999.99\"}}]}}";
        Mockito.when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(String.class))).thenReturn(jsonResponse);

        // Chama o método que deve converter a resposta em um objeto RespostaRequest
        RespostaRequest result = contatoServiceImpl.createContact("xml");

        // Verifica se o objeto RespostaRequest foi corretamente criado a partir da resposta da API
        Assertions.assertEquals("01", result.getRetorno().getContatos().get(0).get(0).getContato().getId());
        Assertions.assertEquals("Nome do contato", result.getRetorno().getContatos().get(0).get(0).getContato().getNome());
        Assertions.assertEquals("Contato Corp", result.getRetorno().getContatos().get(0).get(0).getContato().getFantasia());
        Assertions.assertEquals("F", result.getRetorno().getContatos().get(0).get(0).getContato().getTipoPessoa());
        Assertions.assertEquals("9", result.getRetorno().getContatos().get(0).get(0).getContato().getContribuinte());
        Assertions.assertEquals("11.111.111/1111-11", result.getRetorno().getContatos().get(0).get(0).getContato().getCpf_cnpj());
        Assertions.assertEquals("146849932111", result.getRetorno().getContatos().get(0).get(0).getContato().getIe_rg());
        Assertions.assertEquals("Rua João Moura Teste", result.getRetorno().getContatos().get(0).get(0).getContato().getEndereco());
        Assertions.assertEquals("1251", result.getRetorno().getContatos().get(0).get(0).getContato().getNumero());
        Assertions.assertEquals("null", result.getRetorno().getContatos().get(0).get(0).getContato().getComplemento());
        Assertions.assertEquals("Pinheiros", result.getRetorno().getContatos().get(0).get(0).getContato().getBairro());
        Assertions.assertEquals("05.412-001", result.getRetorno().getContatos().get(0).get(0).getContato().getCep());
        Assertions.assertEquals("São Paulo", result.getRetorno().getContatos().get(0).get(0).getContato().getCidade());
        Assertions.assertEquals("SP", result.getRetorno().getContatos().get(0).get(0).getContato().getUf());
        Assertions.assertEquals("(11) 999999999", result.getRetorno().getContatos().get(0).get(0).getContato().getFone());
        Assertions.assertEquals("contato@teste.com", result.getRetorno().getContatos().get(0).get(0).getContato().getEmail());
        Assertions.assertEquals("9999.99", result.getRetorno().getContatos().get(0).get(0).getContato().getLimiteCredito());

        System.out.println("POST: " + result);
    }

//    @Test
//    void testUpdateContact() {
//        RespostaRequest result = contatoServiceImpl.updateContact("xml", "id");
//        Assertions.assertEquals(new RespostaRequest(), result);
//   }
}