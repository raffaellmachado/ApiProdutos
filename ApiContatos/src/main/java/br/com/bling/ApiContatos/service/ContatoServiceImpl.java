package br.com.bling.ApiContatos.service;

import br.com.bling.ApiContatos.controllers.request.*;
import br.com.bling.ApiContatos.controllers.response.*;
import br.com.bling.ApiContatos.exceptions.ApiContatoException;
//import br.com.bling.ApiContatos.repositories.ContatoRequestRepository;
//import br.com.bling.ApiContatos.repositories.ContatoResponseRepository;
//import br.com.bling.ApiContatos.repositories.TipoContatoRequestRepository;
//import br.com.bling.ApiContatos.repositories.TipoContatoResponseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ContatoServiceImpl implements ContatoService {

    @Value("${external.api.url}")
    public String apiBaseUrl;

    @Value("${external.api.apikey}")
    public String apiKey;

    @Value("${external.api.apikeyparam}")
    public String apikeyparam;

    @Autowired
    public RestTemplate restTemplate;

//    @Autowired
//    public ContatoResponseRepository contatoResponseRepository;
//
//    @Autowired
//    public ContatoRequestRepository contatoRequestRepository;
//
//    @Autowired
//    public TipoContatoResponseRepository tipoContatoResponseRepository;
//
//    @Autowired
//    public TipoContatoRequestRepository tipoContatoRequestRepository;


    /**
     * GET "BUSCAR A LISTA DE CONTATOS CADASTRADOS NO BLING".
     * Método responsável por buscar a lista de contatos, tanto na API externa quanto no banco de dados local.
     *
     * @throws ApiContatoException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponivel para a consulta.
     */
    @Override
    public JsonResponse getAllContacts() throws ApiContatoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(headers);

            /* TESTE BANCO DE DADOS, DESCOMENTAR LINHA ABAIXO */
//            String url = "http://www.teste.com/";
            String url = apiBaseUrl + "/contatos/json/" + apikeyparam + apiKey;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse jsonResponse = objectMapper.readValue(response.getBody(), JsonResponse.class);

//            List<ContatoResponse> contatos = new ArrayList<>();
//            for (RetornoResponse.Contatos contato : jsonResponse.getRetorno().getContatos()) {
//                contatos.add(contato.getContato());
//            }
//
//            ArrayList<RetornoResponse.Contatos> contatosResponse = new ArrayList<>();
//            for (ContatoResponse contato : contatos) {
//                Optional<ContatoResponse> contatoExistente = contatoResponseRepository.findById(contato.getId());
//                if (contatoExistente.isPresent()) {
//                    ContatoResponse contatoExiste = contatoExistente.get();
//                    contatoExiste.setTiposContato(contato.getTiposContato());
//                    for (TiposContatoResponse tiposContato : contato.getTiposContato()) {
//                        Optional<TipoContatoResponse> tipoContatoExistente = tipoContatoResponseRepository.findByDescricao(tiposContato.getTipoContato().getDescricao());
//                        if (tipoContatoExistente.isPresent()) {
//                            tiposContato.setTipoContato(tipoContatoExistente.get());
//                        } else {
//                            TipoContatoResponse novoTipoContato = new TipoContatoResponse();
//                            novoTipoContato.setDescricao(tiposContato.getTipoContato().getDescricao());
//                            tipoContatoResponseRepository.save(novoTipoContato);
//                            tiposContato.setTipoContato(novoTipoContato);
//                        }
//                        if (contatoExiste.getId() != null) {
//                            tiposContato.setContatoResponse(contatoExiste);
//                        }
//                    }
//                    contatoResponseRepository.save(contatoExiste);
//                } else {
//                    for (TiposContatoResponse tiposContato : contato.getTiposContato()) {
//                        Optional<TipoContatoResponse> tipoContatoExistente = tipoContatoResponseRepository.findByDescricao(tiposContato.getTipoContato().getDescricao());
//                        if (tipoContatoExistente.isPresent()) {
//                            tiposContato.setTipoContato(tipoContatoExistente.get());
//                        } else {
//                            TipoContatoResponse novoTipoContato = new TipoContatoResponse();
//                            novoTipoContato.setDescricao(tiposContato.getTipoContato().getDescricao());
//                            tipoContatoResponseRepository.save(novoTipoContato);
//                            tiposContato.setTipoContato(novoTipoContato);
//                        }
//                        if (contato.getId() != null) {
//                            tiposContato.setContatoResponse(contato);
//                        }
//                    }
//                    contatoResponseRepository.save(contato);
//                }
//                RetornoResponse.Contatos contatoResponse = new RetornoResponse.Contatos();
//                contatoResponse.setContato(contato);
//                contatosResponse.add(contatoResponse);
//            }

//            RetornoResponse retornoResponse = new RetornoResponse();
//            retornoResponse.setContatos(contatosResponse);

//            JsonResponse jsonRetornoResponse = new JsonResponse();
//            jsonRetornoResponse.setRetorno(retornoResponse);

//            return jsonRetornoResponse;

            return jsonResponse;

        } catch (JsonProcessingException e) {
            throw new ApiContatoException("Erro ao processar JSON", e);
        } //catch (RestClientException e) {
//            List<ContatoResponse> contatos = contatoResponseRepository.findAll();
//            if (contatos.isEmpty()) {
//                throw new ApiContatoException("Erro ao chamar API: ", e);
//            } else {
//                RetornoResponse retornoResponse = new RetornoResponse();
//                ArrayList<RetornoResponse.Contatos> contatosResponse = new ArrayList<>();
//                for (ContatoResponse contato : contatos) {
//                    RetornoResponse.Contatos contatoResponse = new RetornoResponse.Contatos();
//                    contatoResponse.setContato(contato);
//                    contatosResponse.add(contatoResponse);
//                }
//                retornoResponse.setContatos(contatosResponse);
//                JsonResponse jsonResponse = new JsonResponse();
//                jsonResponse.setRetorno(retornoResponse);
//                return jsonResponse;
//            }
//        }
    }


    /**
     * GET "BUSCA CATEGORIA PELO IDCATEGORIA".
     * Método responsável por localizar uma categoria a partir do seu idCategoria, tanto na API externa quanto no banco de dados local.
     *
     * @param id idCategoria a ser localizado.
     * @throws ApiContatoException Caso ocorra algum erro na comunicação com a API externa o banco de dados fica disponivel para a consulta.
     */
    @Override
    public JsonResponse getContactsById(String id) throws ApiContatoException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(id, headers);

            /* TESTE BANCO DE DADOS, DESCOMENTAR LINHA ABAIXO */
//            String url = "http://www.teste.com/";
            String url = apiBaseUrl + "/contato/" + id + "/json/" + apikeyparam + apiKey + "&identificador=2";
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse jsonResponse = objectMapper.readValue(response.getBody(), JsonResponse.class);

            return jsonResponse;

        } catch (JsonProcessingException e) {
            throw new ApiContatoException("Erro ao processar JSON: ", e);
//        } catch (RestClientException e) {
//            Optional<ContatoResponse> contatoExistente = contatoResponseRepository.findById(Long.valueOf(id));
//            if (contatoExistente.isPresent()) {
//                RetornoResponse.Contatos contato = new RetornoResponse.Contatos();
//                contato.setContato(contatoExistente.get());
//
//                JsonResponse jsonResponse = new JsonResponse();
//                jsonResponse.setRetorno(new RetornoResponse());
//                jsonResponse.getRetorno().setContatos(new ArrayList<>());
//                jsonResponse.getRetorno().getContatos().add(contato);
//
//                return jsonResponse;
//
//            } else {
//                throw new ApiContatoException("A API está indisponível e o contato não foi encontrado no banco de dados.", e);
//            }
        }
    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
    @Override
    public JsonRequest createContact(String xmlContato) throws ApiContatoException {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//            map.add("apikey", apiKey);
            map.add("xml", xmlContato);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            String url = apiBaseUrl + "/contato/json/";

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest jsonRequest = objectMapper.readValue(response.getBody(), JsonRequest.class);

            return jsonRequest;

        } catch (RestClientException e) {
            // Em caso de erro ao chamar a API, salva os dados no banco de dados
//            List<ContatoRequest> contatos = new ArrayList<>();
//            try {
//                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//                DocumentBuilder builder = factory.newDocumentBuilder();
//                InputSource is = new InputSource(new StringReader(xmlContato));
//                Document doc = builder.parse(is);
//
//                ContatoRequest contatoXmlRequest = new ContatoRequest();
//
//                Random random = new Random();
//                int randomNumber = random.nextInt(900000000) + 100000000; // gera número entre 100000000 e 999999999
//
//                // Preenchimento dos campos do contato
//                Node nodeContato = doc.getElementsByTagName("contato").item(0);
//                Element elementoContato = (Element) nodeContato;
//
//                contatoXmlRequest.setId((long) randomNumber);
//                contatoXmlRequest.setNome(elementoContato.getElementsByTagName("nome").item(0).getTextContent());
//                contatoXmlRequest.setFantasia(elementoContato.getElementsByTagName("fantasia").item(0).getTextContent());
//                contatoXmlRequest.setTipoPessoa(elementoContato.getElementsByTagName("tipoPessoa").item(0).getTextContent());
//                contatoXmlRequest.setContribuinte(Integer.parseInt(elementoContato.getElementsByTagName("contribuinte").item(0).getTextContent()));
//                contatoXmlRequest.setCpf_cnpj(elementoContato.getElementsByTagName("cpf_cnpj").item(0).getTextContent());
//                contatoXmlRequest.setIe_rg(elementoContato.getElementsByTagName("ie_rg").item(0).getTextContent());
//                contatoXmlRequest.setEndereco(elementoContato.getElementsByTagName("endereco").item(0).getTextContent());
//                contatoXmlRequest.setNumero(elementoContato.getElementsByTagName("numero").item(0).getTextContent());
//                contatoXmlRequest.setComplemento(elementoContato.getElementsByTagName("complemento").item(0).getTextContent());
//                contatoXmlRequest.setBairro(elementoContato.getElementsByTagName("bairro").item(0).getTextContent());
//                contatoXmlRequest.setCep(elementoContato.getElementsByTagName("cep").item(0).getTextContent());
//                contatoXmlRequest.setCidade(elementoContato.getElementsByTagName("cidade").item(0).getTextContent());
//                contatoXmlRequest.setUf(elementoContato.getElementsByTagName("uf").item(0).getTextContent());
//                contatoXmlRequest.setFone(elementoContato.getElementsByTagName("fone").item(0).getTextContent());
//                contatoXmlRequest.setCelular(elementoContato.getElementsByTagName("celular").item(0).getTextContent());
//                contatoXmlRequest.setEmail(elementoContato.getElementsByTagName("email").item(0).getTextContent());
//                contatoXmlRequest.setEmailNfe(elementoContato.getElementsByTagName("emailNfe").item(0).getTextContent());
//                contatoXmlRequest.setInformacaoContato(elementoContato.getElementsByTagName("informacaoContato").item(0).getTextContent());
//                contatoXmlRequest.setLimiteCredito(BigDecimal.valueOf(Double.parseDouble(elementoContato.getElementsByTagName("limiteCredito").item(0).getTextContent())));
//                contatoXmlRequest.setFlag("POST");
//
//                // Preenchimento dos tipos de contato
//                NodeList tiposContatoNodes = elementoContato.getElementsByTagName("tipos_contatos").item(0).getChildNodes();
//                List<TipoContatoRequest> tipoContatoList = new ArrayList<>();
//                for (int i = 0; i < tiposContatoNodes.getLength(); i++) {
//                    Node tipoContatoNode = tiposContatoNodes.item(i);
//                    if (tipoContatoNode.getNodeType() == Node.ELEMENT_NODE) {
//                        Element tipoContatoElement = (Element) tipoContatoNode;
//                        TipoContatoRequest tipoContatoRequest = new TipoContatoRequest();
//                        tipoContatoRequest.setDescricao(tipoContatoElement.getElementsByTagName("descricao").item(0).getTextContent());
//                        tipoContatoList.add(tipoContatoRequest);
//                    }
//                }
//                List<TiposContatoRequest> tiposContatoList = new ArrayList<>();
//                for (TipoContatoRequest tipoContatoRequest : tipoContatoList) {
//                    TiposContatoRequest tiposContato = new TiposContatoRequest();
//                    tiposContato.setTipoContato(tipoContatoRequest);
//                    tiposContatoList.add(tiposContato);
//                }
//                contatoXmlRequest.setTiposContato(tiposContatoList);
//                contatos.add(contatoXmlRequest);
//
//                ArrayList<RetornoRequest.Contatos> contatosRequest = new ArrayList<>();
//                for (ContatoRequest contato : contatos) {
//                    for (TiposContatoRequest tiposContato : contato.getTiposContato()) {
//                        Optional<TipoContatoRequest> tipoContatoExistente = tipoContatoRequestRepository.findByDescricao(tiposContato.getTipoContato().getDescricao());
//                        if (tipoContatoExistente.isPresent()) {
//                            tiposContato.setTipoContato(tipoContatoExistente.get());
//                        } else {
//                            TipoContatoRequest novoTipoContato = new TipoContatoRequest();
//                            novoTipoContato.setDescricao(tiposContato.getTipoContato().getDescricao());
//                            tipoContatoRequestRepository.save(novoTipoContato);
//                            tiposContato.setTipoContato(novoTipoContato);
//                        }
//                        tiposContato.setContatoRequest(contato);
//                    }
////                    contatoRequestRepository.save(contato);
//                    RetornoRequest.Contatos contatoRequest = new RetornoRequest.Contatos();
//                    contatoRequest.setContato(contato);
//                    contatosRequest.add(contatoRequest);
//                }
//
//                String nomeContato = elementoContato.getElementsByTagName("cpf_cnpj").item(0).getTextContent();
//                List<ContatoRequest> contatoExistente = contatoRequestRepository.findByCpfCnpj(nomeContato);
//
//                boolean contatoJaExiste = !contatoExistente.isEmpty();
//
//                if (!contatoJaExiste) {
//                    contatoRequestRepository.save(contatoXmlRequest);
//                }

//            } catch (ParserConfigurationException | SAXException |
//                     IOException ex) {
//                throw new ApiContatoException("Erro ao processar XML: ", ex);
//            }
            throw new ApiContatoException("Erro ao chamar API", e);
//        } catch (JsonProcessingException e) {
//            throw new ApiContatoException("Erro ao processar JSON: ", e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * PUT "ATUALIZAR PRODUTO PELO ID UTILIZANDO XML.
     */
    @Override
    public JsonRequest updateContact(@RequestBody @Valid String xmlContato, @PathVariable("id") String id) throws ApiContatoException {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("apikey", apiKey);
            map.add("xml", xmlContato);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            String url = apiBaseUrl + "/contato/" + id + "/json/";

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonRequest result = objectMapper.readValue(response.getBody(), JsonRequest.class);

            return result;

        } catch (JsonProcessingException e) {
            throw new ApiContatoException("Erro ao processar JSON: ", e);
        } catch (RestClientException e) {
            throw new ApiContatoException("Erro ao chamar API", e);
        }
    }

//    @Transactional
//    @Scheduled(fixedDelayString = "${api.check.delay}")
//    public void scheduledPostCategory() {
//        try {
//            System.out.println("Chamei o Scheduled POST");
////            String url = "http://www.teste.com/";
//            String url = apiBaseUrl + "/categorias/json/" + apikeyparam + apiKey;
//            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//
//            if (response.getStatusCode() == HttpStatus.OK) {
//                List<ContatoRequest> contatos = contatoRequestRepository.findAll();
//                List<String> descricaoContatos = contatoResponseRepository.findAllDescricao();
//
//                for (ContatoRequest contato : contatos) {
//                    if (contato.getFlag() != null && contato.getFlag().equals("POST")) {
//                        if (!descricaoContatos.contains(contato.getCpf_cnpj())) {
//                            System.out.println("Contato não encontrado na API, adicionando...");
//                            String xmlContato = "<contato>";
//                            xmlContato += "<nome>" + contato.getNome() + "</nome>";
//                            xmlContato += "<fantasia>" + contato.getFantasia() + "</fantasia>";
//                            xmlContato += "<tipoPessoa>" + contato.getTipoPessoa() + "</tipoPessoa>";
//                            xmlContato += "<contribuinte>" + contato.getContribuinte() + "</contribuinte>";
//                            xmlContato += "<cpf_cnpj>" + contato.getCpf_cnpj() + "</cpf_cnpj>";
//                            xmlContato += "<ie_rg>" + contato.getIe_rg() + "</ie_rg>";
//                            xmlContato += "<endereco>" + contato.getEndereco() + "</endereco>";
//                            xmlContato += "<numero>" + contato.getNumero() + "</numero>";
//                            xmlContato += "<complemento>" + contato.getComplemento() + "</complemento>";
//                            xmlContato += "<bairro>" + contato.getBairro() + "</bairro>";
//                            xmlContato += "<cep>" + contato.getCep() + "</cep>";
//                            xmlContato += "<cidade>" + contato.getCidade() + "</cidade>";
//                            xmlContato += "<uf>" + contato.getUf() + "</uf>";
//                            xmlContato += "<fone>" + contato.getFone() + "</fone>";
//                            xmlContato += "<celular>" + contato.getCelular() + "</celular>";
//                            xmlContato += "<email>" + contato.getEmail() + "</email>";
//                            xmlContato += "<emailNfe>" + contato.getEmailNfe() + "</emailNfe>";
//                            xmlContato += "<informacaoContato>" + contato.getInformacaoContato() + "</informacaoContato>";
//                            xmlContato += "<limiteCredito>" + contato.getLimiteCredito() + "</limiteCredito>";
//                            xmlContato += "<tipos_contatos>";
//                            for (TiposContatoRequest tipoContato : contato.getTiposContato()) {
//                                xmlContato += "<tipo_contato>";
//                                xmlContato += "<descricao>" + tipoContato.getTipoContato().getDescricao() + "</descricao>";
//                                xmlContato += "</tipo_contato>";
//                            }
//                            xmlContato += "</tipos_contatos>";
//                            xmlContato += "</contato>";
//
//                            createContact(xmlContato);
//                            contatoRequestRepository.delete(contato);
//                        } else {
//                            System.out.println("Categoria já existe na API, deletando...");
//                            contatoRequestRepository.delete(contato);
//                        }
//                    }
//                }
//
//            }
//        } catch (RestClientException e) {
//            System.out.println("API está offline, nada a fazer");
//        }
//    }

    /**
     * ---------------------------------------------------- VERSÃO 1 - SEM CONEXÃO AO BANCO DE DADOS. ----------------------------------------------------------
     */

    /**
     * GET "BUSCAR A LISTA DE PRODUTOS CADASTRADO NO BLING".
     *///    @Override
//    public JsonResponse getAllContacts() throws ApiContatoException {
//        try {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<String> request = new HttpEntity<>(headers);
//
//            String url = apiBaseUrl + "/contatos/json/" + apikeyparam + apiKey;
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonResponse result = objectMapper.readValue(response.getBody(), JsonResponse.class);
//
//            return result;
//
//        } catch (JsonProcessingException e) {
//            throw new ApiContatoException("Erro ao processar JSON", e);
//        } catch (RestClientException e) {
//            throw new ApiContatoException("Erro ao chamar API", e);
//        }
//    }

    /**
     * GET "BUSCAR UM PRODUTO PELO CÒDIGO (ID)".
     */
//    @Override
//    public JsonResponse getContactsById(String id) throws ApiContatoException {
//        try {
//            /* TESTE BANCO DE DADOS, DESCOMENTAR LINHA ABAIXO */
////            String url = "http://www.teste.com/";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<String> request = new HttpEntity<>(id, headers);
//
//            String url = apiBaseUrl + "/contato/" + id + "/json/" +apikeyparam + apiKey + "&identificador=2";
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonResponse jsonResponse = objectMapper.readValue(response.getBody(), JsonResponse.class);
//
//            return jsonResponse;
//
//        } catch (JsonProcessingException e) {
//            throw new ApiContatoException("Erro ao processar JSON: ", e);
//        } catch (RestClientException e) {
//            Optional<ContatoResponse> contatoExistente = contatoResponseRepository.findById(Long.valueOf(id));
//            if (contatoExistente.isPresent()) {
//                RetornoResponse.Contatos contato = new RetornoResponse.Contatos();
//                contato.setContato(contatoExistente.get());
//
//                JsonResponse jsonResponse = new JsonResponse();
//                jsonResponse.setRetorno(new RetornoResponse());
//                jsonResponse.getRetorno().setContatos(new ArrayList<>());
//                jsonResponse.getRetorno().getContatos().add(contato);
//
//                return jsonResponse;
//
//            } else {
//                throw new ApiContatoException("A API está indisponível e o contato não foi encontrado no banco de dados.", e);
//            }
//        }
//    }

    /**
     * POST "CADASTRAR UM NOVO PRODUTO" UTILIZANDO XML.
     */
//    @Override
//    public JsonRequest createContact(String xmlContato) throws ApiContatoException {
//        try {
//            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//            map.add("apikey", apiKey);
//            map.add("xml", xmlContato);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//            String url = apiBaseUrl + "/contato/json/";
//
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonRequest result = objectMapper.readValue(response.getBody(), JsonRequest.class);
//
//            return result;
//
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Erro ao processar JSON: ", e);
//        } catch (RestClientException e) {
//            throw new RuntimeException("Erro ao chamar API: ", e);
//        }
//    }

    /**
     * PUT "ATUALIZAR PRODUTO PELO ID UTILIZANDO XML.
     */
//    @Override
//    public JsonRequest updateContact(@RequestBody @Valid String xmlContato, @PathVariable("id") String id) throws ApiContatoException {
//        try {
//            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//            map.add("apikey", apiKey);
//            map.add("xml", xmlContato);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//            String url = apiBaseUrl + "/contato/" + id + "/json/";
//
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonRequest result = objectMapper.readValue(response.getBody(), JsonRequest.class);
//
//            return result;
//
//        } catch (JsonProcessingException e) {
//            throw new ApiContatoException("Erro ao processar JSON: ", e);
//        } catch (RestClientException e) {
//            throw new ApiContatoException("Erro ao chamar API", e);
//        }
//    }
}