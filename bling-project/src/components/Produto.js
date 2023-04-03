import React from "react";

import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Table from 'react-bootstrap/Table';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import { Container } from "react-bootstrap";
import Spinner from 'react-bootstrap/Spinner';
import { FaSync, FaTrash } from 'react-icons/fa';

import '../css/Produto.css';
import { parse } from 'js2xmlparser';
import CadastroProduto from "./CadastroProduto";



class Produto extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            produtos: [],
            id: 0,
            codigo: '',
            descricao: '',
            tipo: '',
            situacao: '',
            unidade: '',
            preco: '',
            precoCusto: '',
            descricaoCurta: '',
            descricaoComplementar: '',
            dataInclusao: '',
            dataAlteracao: '',
            imageThumbnail: '',
            urlVideo: '',
            nomeFornecedor: '',
            codigoFabricante: '',
            marca: '',
            class_fiscal: '',
            cest: '',
            origem: '',
            idGrupoProduto: '',
            linkExterno: '',
            observacoes: '',
            grupoProduto: '',
            garantia: '',
            descricaoFornecedor: '',
            idFabricante: '',
            categoria: [],
            pesoLiq: '',
            pesoBruto: '',
            estoqueMinimo: '',
            estoqueMaximo: '',
            gtin: '',
            gtinEmbalagem: '',
            larguraProduto: '',
            alturaProduto: '',
            profundidadeProduto: '',
            unidadeMedida: '',
            itensPorCaixa: '',
            volumes: '',
            localizacao: '',
            crossdocking: '',
            condicao: '',
            freteGratis: '',
            producao: '',
            dataValidade: '',
            spedTipoItem: '',
            carregando: true,
            modalAberta: false,
        };
    }

    componentDidMount() {
        this.buscarProduto();
    }

    componentWillUnmount() {

    }

    //GET - MÉTODO PARA CONSUMO DA API CONTATOS
    buscarProduto = () => {
        fetch("http://localhost:8081/api/v1/produtos")
            .then(resposta => resposta.json())
            .then(dados => {
                console.log(dados); // Adicionando o console.log

                if (dados.retorno.produtos) {
                    this.setState({ produtos: dados.retorno.produtos })
                } else {
                    this.setState({ produtos: [] })
                }
                this.setState({ carregando: false })
            })
    }

    //GET - MÉTODO PARA CONSUMO DA API CONTATOS
    carregarProdutos = (codigo) => {
        fetch("http://localhost:8081/api/v1/produto/" + codigo, { method: 'GET' })
            .then(resposta => resposta.json())
            .then(dados => {
                if (dados.retorno.produtos) {
                    this.setState({

                        id: dados.retorno.produtos[0].produto.id,
                        codigo: dados.retorno.produtos[0].produto.codigo,
                        descricao: dados.retorno.produtos[0].produto.descricao,
                        tipo: dados.retorno.produtos[0].produto.tipo,
                        situacao: dados.retorno.produtos[0].produto.situacao,
                        unidade: dados.retorno.produtos[0].produto.unidade,
                        preco: dados.retorno.produtos[0].produto.preco,
                        precoCusto: dados.retorno.produtos[0].produto.precoCusto,
                        descricaoCurta: dados.retorno.produtos[0].produto.descricaoCurta,
                        descricaoComplementar: dados.retorno.produtos[0].produto.descricaoComplementar,
                        dataInclusao: dados.retorno.produtos[0].produto.dataInclusao,
                        dataAlteracao: dados.retorno.produtos[0].produto.dataAlteracao,
                        imageThumbnail: dados.retorno.produtos[0].produto.imageThumbnail,
                        urlVideo: dados.retorno.produtos[0].produto.urlVideo,
                        nomeFornecedor: dados.retorno.produtos[0].produto.nomeFornecedor,
                        codigoFabricante: dados.retorno.produtos[0].produto.codigoFabricante,
                        marca: dados.retorno.produtos[0].produto.marca,
                        class_fiscal: dados.retorno.produtos[0].produto.class_fiscal,
                        cest: dados.retorno.produtos[0].produto.cest,
                        origem: dados.retorno.produtos[0].produto.origem,
                        idGrupoProduto: dados.retorno.produtos[0].produto.idGrupoProduto,
                        linkExterno: dados.retorno.produtos[0].produto.linkExterno,
                        observacoes: dados.retorno.produtos[0].produto.observacoes,
                        grupoProduto: dados.retorno.produtos[0].produto.grupoProduto,
                        garantia: dados.retorno.produtos[0].produto.garantia,
                        descricaoFornecedor: dados.retorno.produtos[0].produto.descricaoFornecedor,
                        idFabricante: dados.retorno.produtos[0].produto.idFabricante,
                        categoria: [],
                        id: dados.retorno.produtos[0].produto.categoria.id,
                        descricao: dados.retorno.produtos[0].produto.categoria.descricao,
                        pesoLiq: dados.retorno.produtos[0].produto.pesoLiq,
                        pesoBruto: dados.retorno.produtos[0].produto.pesoBruto,
                        estoqueMinimo: dados.retorno.produtos[0].produto.estoqueMinimo,
                        estoqueMaximo: dados.retorno.produtos[0].produto.estoqueMaximo,
                        gtin: dados.retorno.produtos[0].produto.gtin,
                        gtinEmbalagem: dados.retorno.produtos[0].produto.gtinEmbalagem,
                        larguraProduto: dados.retorno.produtos[0].produto.larguraProduto,
                        alturaProduto: dados.retorno.produtos[0].produto.alturaProduto,
                        profundidadeProduto: dados.retorno.produtos[0].produto.profundidadeProduto,
                        unidadeMedida: dados.retorno.produtos[0].produto.unidadeMedida,
                        itensPorCaixa: dados.retorno.produtos[0].produto.itensPorCaixa,
                        volumes: dados.retorno.produtos[0].produto.volumes,
                        localizacao: dados.retorno.produtos[0].produto.localizacao,
                        crossdocking: dados.retorno.produtos[0].produto.crossdocking,
                        condicao: dados.retorno.produtos[0].produto.condicao,
                        freteGratis: dados.retorno.produtos[0].produto.freteGratis,
                        producao: dados.retorno.produtos[0].produto.producao,
                        dataValidade: dados.retorno.produtos[0].produto.dataValidade,
                        spedTipoItem: dados.retorno.produtos[0].produto.spedTipoItem

                    })

                } else {
                    this.setState({ produtos: [] })
                }
                this.setState({ carregando: false });
                this.abrirModal();
            })

            .catch(error => console.error(error));
    }

    excluirProduto(codigo) {
        fetch(`http://localhost:8081/api/v1/produto/${codigo}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(resposta => resposta.json())
            .then(dados => {
                console.log(dados);
                this.buscarProduto(); // atualiza a lista de produtos após a exclusão
            })
            .catch(erro => console.error(erro));
    }


    //POST - MÉTODO PARA INSERIR UM NOVO CONTATO NA API CONTATOS
    cadastraProduto = (xmlProduto) => {
        const parser = new DOMParser();
        const xml = parser.parseFromString(xmlProduto, 'text/xml');
        const stringXml = new XMLSerializer().serializeToString(xml);

        fetch('http://localhost:8080/api/v1/cadastrarproduto', {
            method: 'POST',
            body: stringXml,
            headers: {
                'Content-Type': 'application/xml'
            }
        })
    }

    //PUT - MÉTODO PARA ATUALIZAR UM CONTATO EXISTENTE NA API CONTATOS
    atualizarProduto = (xmlProduto) => {
        const parser = new DOMParser();
        const xml = parser.parseFromString(xmlProduto, 'text/xml');
        const stringXml = new XMLSerializer().serializeToString(xml);
        const codigo = xml.querySelector('codigo').textContent;

        fetch('http://localhost:8080/api/v1/atualizarproduto/' + codigo, {
            method: 'POST',
            body: stringXml,
            headers: {
                'Content-Type': 'application/xml'
            }
        })
    }

    atualizaNome = (e) => {
        this.setState({
            nome: e.target.value
        })
    }

    atualizaCodigo = (e) => {
        this.setState({
            codigo: e.target.value
        })
    }

    atualizaDescricao = (e) => {
        this.setState({
            descricao: e.target.value
        })
    }

    atualizaTipo = (e) => {
        this.setState({
            tipo: e.target.value
        })
    }

    atualizaPreco = (e) => {
        this.setState({
            preco: e.target.value
        })
    }

    atualizaUnidade = (e) => {
        this.setState({
            unidade: e.target.value
        })
    }

    atualizaCondicao = (e) => {
        this.setState({
            condicao: e.target.value
        })
    }

    atualizaMarca = (e) => {
        this.setState({
            marca: e.target.value
        })
    }

    atualizaProducao = (e) => {
        this.setState({
            producao: e.target.value
        })
    }

    atualizaDataValidade = (e) => {
        this.setState({
            dataValidade: e.target.value
        })
    }



    //Ações do botão SUBMIT (Cadastrar).
    submit = (event) => {
        event.preventDefault();
        console.log("logradouro: ", this.state.logradouro);
        console.log("endereco: ", this.state.endereco);
        console.log("-----------------------------------");
        console.log("localidade: ", this.state.localidade);
        console.log("cidade: ", this.state.cidade);
        console.log("-----------------------------------");
        console.log("tipo: ", this.state.tipo);
        console.log("tipoPessoa: ", this.state.tipoPessoa);
        console.log("-----------------------------------");
        console.log("cnpj: ", this.state.cnpj);
        console.log("cpf_cnpj: ", this.state.cpf_cnpj);

        if (this.state.id === 0) {
            const produto = {
                // descricao: this.state.descricao,
                // codigo: this.state.codigo,
                // unidade: this.state.unidade,
                // preco: this.state.preco,
                // estoqueMaximo: this.state.estoqueMaximo
                codigo: this.state.codigo,
                descricao: this.state.descricao,
                tipo: this.state.tipo,
                situacao: this.state.situacao,
                unidade: this.state.unidade,
                preco: this.state.preco,
                precoCusto: this.state.precoCusto,
                descricaoCurta: this.state.descricaoCurta,
                descricaoComplementar: this.state.descricaoComplementar,
                dataInclusao: this.state.dataInclusao,
                dataAlteracao: this.state.dataAlteracao,
                imageThumbnail: this.state.imageThumbnail,
                urlVideo: this.state.urlVideo,
                nomeFornecedor: this.state.nomeFornecedor,
                codigoFabricante: this.state.codigoFabricante,
                marca: this.state.marca,
                class_fiscal: this.state.class_fiscal,
                cest: this.state.cest,
                origem: this.state.origem,
                idGrupoProduto: this.state.idGrupoProduto,
                linkExterno: this.state.linkExterno,
                observacoes: this.state.observacoes,
                grupoProduto: this.state.grupoProduto,
                garantia: this.state.garantia,
                descricaoFornecedor: this.state.descricaoFornecedor,
                idFabricante: this.state.idFabricante,
                categoria: [],
                pesoLiq: this.state.pesoLiq,
                pesoBruto: this.state.pesoBruto,
                estoqueMinimo: this.state.estoqueMinimo,
                estoqueMaximo: this.state.estoqueMaximo,
                gtin: this.state.gtin,
                gtinEmbalagem: this.state.gtinEmbalagem,
                larguraProduto: this.state.larguraProduto,
                alturaProduto: this.state.alturaProduto,
                profundidadeProduto: this.state.profundidadeProduto,
                unidadeMedida: this.state.unidadeMedida,
                itensPorCaixa: this.state.itensPorCaixa,
                volumes: this.state.volumes,
                localizacao: this.state.localizacao,
                crossdocking: this.state.crossdocking,
                condicao: this.state.condicao,
                freteGratis: this.state.freteGratis,
                producao: this.state.producao,
                dataValidade: this.state.dataValidade,
                spedTipoItem: this.state.spedTipoItem
            }
            const xmlProduto = parse('produto', produto);
            this.cadastraProduto(xmlProduto);
        } else {
            const produto = {
                id: this.state.id,
                codigo: this.state.codigo,
                descricao: this.state.descricao,
                tipo: this.state.tipo,
                situacao: this.state.situacao,
                unidade: this.state.unidade,
                preco: this.state.preco,
                precoCusto: this.state.precoCusto,
                descricaoCurta: this.state.descricaoCurta,
                descricaoComplementar: this.state.descricaoComplementar,
                dataInclusao: this.state.dataInclusao,
                dataAlteracao: this.state.dataAlteracao,
                imageThumbnail: this.state.imageThumbnail,
                urlVideo: this.state.urlVideo,
                nomeFornecedor: this.state.nomeFornecedor,
                codigoFabricante: this.state.codigoFabricante,
                marca: this.state.marca,
                class_fiscal: this.state.class_fiscal,
                cest: this.state.cest,
                origem: this.state.origem,
                idGrupoProduto: this.state.idGrupoProduto,
                linkExterno: this.state.linkExterno,
                observacoes: this.state.observacoes,
                grupoProduto: this.state.grupoProduto,
                garantia: this.state.garantia,
                descricaoFornecedor: this.state.descricaoFornecedor,
                idFabricante: this.state.idFabricante,
                categoria: [],
                pesoLiq: this.state.pesoLiq,
                pesoBruto: this.state.pesoBruto,
                estoqueMinimo: this.state.estoqueMinimo,
                estoqueMaximo: this.state.estoqueMaximo,
                gtin: this.state.gtin,
                gtinEmbalagem: this.state.gtinEmbalagem,
                larguraProduto: this.state.larguraProduto,
                alturaProduto: this.state.alturaProduto,
                profundidadeProduto: this.state.profundidadeProduto,
                unidadeMedida: this.state.unidadeMedida,
                itensPorCaixa: this.state.itensPorCaixa,
                volumes: this.state.volumes,
                localizacao: this.state.localizacao,
                crossdocking: this.state.crossdocking,
                condicao: this.state.condicao,
                freteGratis: this.state.freteGratis,
                producao: this.state.producao,
                dataValidade: this.state.dataValidade,
                spedTipoItem: this.state.spedTipoItem
            }
            const xmlProduto = parse('produto', produto);
            this.atualizarProduto(xmlProduto);
        }
        const form = event.currentTarget;

        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation(); // se algum campo obrigatorio nãao for preenchidos o modal é travado
            this.setState({ validated: true }); // atribui true na validação
        } else {
            event.preventDefault();
            this.setState({ validated: true }); // atribui true na validação
            this.fecharModal(); // se todos os campos estiverem preenchidos o modal é fechado
            this.buscarContato(); // atualiza a lista de produtos após a exclusão
        }
    }

    //Ação para limpar o campos do modal para cadastrar um novo cliente.
    reset = () => {
        this.setState(
            {
                id: 0,
                codigo: '',
                descricao: '',
                tipo: '',
                situacao: '',
                unidade: '',
                preco: '',
                precoCusto: '',
                descricaoCurta: '',
                descricaoComplementar: '',
                dataInclusao: '',
                dataAlteracao: '',
                imageThumbnail: '',
                urlVideo: '',
                nomeFornecedor: '',
                codigoFabricante: '',
                marca: '',
                class_fiscal: '',
                cest: '',
                origem: '',
                idGrupoProduto: '',
                linkExterno: '',
                observacoes: '',
                grupoProduto: '',
                garantia: '',
                descricaoFornecedor: '',
                idFabricante: '',
                categoria: [],
                pesoLiq: '',
                pesoBruto: '',
                estoqueMinimo: '',
                estoqueMaximo: '',
                gtin: '',
                gtinEmbalagem: '',
                larguraProduto: '',
                alturaProduto: '',
                profundidadeProduto: '',
                unidadeMedida: '',
                itensPorCaixa: '',
                volumes: '',
                localizacao: '',
                crossdocking: '',
                condicao: '',
                freteGratis: '',
                producao: '',
                dataValidade: '',
                spedTipoItem: '',
            })
        this.abrirModal();
    }

    //Ação para fechar o modal de cadastro e atualização.
    fecharModal = () => {
        this.setState(
            {
                modalAberta: false
            }
        )
        this.buscarProduto();
        window.location.reload();
    }

    //Ação para abrir o modal de cadastro e atualização.
    abrirModal = () => {
        this.setState(
            {
                modalAberta: true
            }
        )
    }

    render() {
        if (this.state.carregando) {
            return (
                <div className="spinner-container" >
                    <div className="d-flex align-items-center justify-content-center">
                        <Spinner variant="secondary" animation="border" role="status">
                            <span className="visually-hidden">Carregando produtos...</span>
                        </Spinner>
                    </div>
                    <div>
                        <p>Carregando produtos...</p>
                    </div>
                </div>
            )
        } else {
            return (
                <div className="background">
                    <div className="container">
                        <div>
                            <Button variant="success" bsPrefix="btn-cadastro-button" onClick={this.reset}>
                                + Incluir Cadastro
                            </Button>
                        </div>
                        <div>
                            <div className="table-tabela">
                                <Table striped bordered hover className="table-dark" responsive="sm">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th title="Descrição">Descrição</th>
                                            <th title="Código">Código</th>
                                            <th title="Unidade">Unidade</th>
                                            <th title="Preço">Preço</th>
                                            <th title="Estoque">Estoque</th>
                                            <th title="Opções">Opções</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {
                                            this.state.produtos.map((produtos) =>
                                                <tr key={produtos.produto.id}>
                                                    <td>{produtos.produto.id}</td>
                                                    <td>{produtos.produto.descricao}</td>
                                                    <td>{produtos.produto.codigo}</td>
                                                    <td>{produtos.produto.unidade}</td>
                                                    <td>{produtos.produto.preco}</td>
                                                    <td>{produtos.produto.estoqueMaximo}</td>
                                                    <td>
                                                        <div className="button-container-table">
                                                            <Button variant="warning" onClick={() => this.carregarProdutos(produtos.produto.codigo)}>
                                                                <FaSync />
                                                            </Button>
                                                            <Button variant="danger" onClick={() => this.excluirProduto(produtos.produto.codigo)}>
                                                                <FaTrash />
                                                            </Button>
                                                        </div>
                                                    </td>
                                                </tr>
                                            )
                                        }
                                        {this.state.produtos.length === 0 && <tr><td colSpan="6">Nenhum produto cadastrado.</td></tr>}
                                    </tbody>
                                </Table>
                            </div>
                        </div>
                        {this.renderModal()}
                    </div>
                </div>
            )
        }
    }

    renderModal() {
        return (
            <Modal show={this.state.modalAberta} onHide={this.fecharModal} size="xl" backdrop="static">
                <Modal.Header closeButton>
                    <Modal.Title>Cadastro/Atualização de Produto</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form noValidate validated={this.state.validated} onSubmit={this.submit}>
                        <Container>
                            <Row>
                                <Col xs={2} md={2}>
                                    <Form.Group controlId="id" className="mb-3 form-row" as={Col}>
                                        <Form.Label type="text">ID</Form.Label>
                                        <Form.Control type="text" value={this.state.id || ''} readOnly disabled />
                                    </Form.Group>
                                </Col>
                                <Col xs={6} md={8}>
                                    <Form.Group controlId="nome" className="mb-3">
                                        <Form.Label>Nome</Form.Label>
                                        <Form.Control type="text" placeholder="Digite o nome" value={this.state.nome || ''} onChange={this.atualizaNome} required />
                                        <Form.Control.Feedback type="invalid">Campo obrigatório.</Form.Control.Feedback>
                                    </Form.Group>
                                </Col>
                            </Row>
                            <Row>
                                <Col xs={2} md={4}>
                                    <Form.Group controlId="codigo" className="mb-3">
                                        <Form.Label>Código (SKU)</Form.Label>
                                        <Form.Control type="text" placeholder="Digite o código" value={this.state.codigo || ''} onChange={this.atualizaCodigo} />
                                    </Form.Group>
                                </Col>
                                <Col xs={2} md={4}>
                                    <Form.Group controlId="Formato" className="mb-3">
                                        <Form.Label>Formato</Form.Label>
                                        <Form.Select as="select" placeholder="Tipo de formato" value={''} onChange={this.atualizaDescricao} >
                                            <option value="">Simples</option>
                                            <option value="">Com variação</option>
                                            <option value="">Com composição</option>
                                        </Form.Select>
                                    </Form.Group>
                                </Col>
                                <Col xs={2} md={4}>
                                    <Form.Group controlId="Formato" className="mb-3">
                                        <Form.Label>Tipo</Form.Label>
                                        <Form.Select as="select" placeholder="Selecione o tipo" value={this.state.tipo || ''} onChange={this.atualizaTipo} >
                                            <option value="P">Produto</option>
                                            <option value="S">Serviço</option>
                                        </Form.Select>
                                    </Form.Group>
                                </Col>
                            </Row>
                            <Row>
                                <Col xs={2} md={4}>
                                    <Form.Group controlId="nome" className="mb-3">
                                        <Form.Label>Preço venda</Form.Label>
                                        <Form.Control type="text" placeholder="Digite o preço de venda" value={this.state.preco || ''} onChange={this.atualizaPreco} required />
                                        <Form.Control.Feedback type="invalid">Campo obrigatório.</Form.Control.Feedback>
                                    </Form.Group>
                                </Col>
                                <Col xs={2} md={4}>
                                    <Form.Group controlId="nome" className="mb-3">
                                        <Form.Label>Unidade</Form.Label>
                                        <Form.Control type="text" placeholder="Digite a unidade (pc, un, cx)" value={this.state.unidade || ''} onChange={this.atualizaUnidade} required />
                                        <Form.Control.Feedback type="invalid">Campo obrigatório.</Form.Control.Feedback>
                                    </Form.Group>
                                </Col>
                                <Col xs={2} md={4}>
                                    <Form.Group controlId="Formato" className="mb-3">
                                        <Form.Label>Condição</Form.Label>
                                        <Form.Select as="select" placeholder="Selecione a condição" value={this.state.condicao || ''} onChange={this.atualizaCondicao} >
                                            <option value="Não Especificado">Não Especificado</option>
                                            <option value="Novo">Novo</option>
                                            <option value="Usado">Usado</option>
                                            <option value="Recondicionado">Recondicionado</option>
                                        </Form.Select>
                                    </Form.Group>
                                </Col>
                            </Row>

                            <Tabs defaultActiveKey="caracteristica" id="fill-tab-example" className="mb-3" fill>
                                <Tab eventKey="caracteristica" title="Caracteristica">
                                    <Row>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="marca" className="mb-3">
                                                <Form.Label>Marca</Form.Label>
                                                <Form.Control type="text" placeholder="Digite o nome" value={this.state.marca || ''} onChange={this.atualizaMarca} required />
                                                <Form.Control.Feedback type="invalid">Campo obrigatório.</Form.Control.Feedback>
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="produção" className="mb-3">
                                                <Form.Label>Tipo Contato</Form.Label>
                                                <Form.Select as="select" placeholder="Tipo de contato" value={this.state.producao || ''} onChange={this.atualizaProducao} >
                                                    <option value="">Própria</option>
                                                    <option value="">Terceiros</option>
                                                </Form.Select>
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="datavalidade" className="mb-3">
                                                <Form.Label>Data de validade</Form.Label>
                                                <Form.Control type="date" placeholder="Digite o nome" value={this.state.dataValidade || ''} onChange={this.atualizaDataValidade} required />
                                                <Form.Control.Feedback type="invalid">Campo obrigatório.</Form.Control.Feedback>
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="frete" className="mb-3">
                                                <Form.Label>Frete Grátis</Form.Label>
                                                <Form.Select as="select" placeholder="Selecione o frete" value={this.state.freteGratis || ''} onChange={this.atualizaDescricao} >
                                                    <option value="">Não</option>
                                                    <option value="">Sim</option>
                                                </Form.Select>
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="pesoliquido" className="mb-3">
                                                <Form.Label>Peso Líquido </Form.Label>
                                                <Form.Control type="text" placeholder="Insira o peso liquido" value={this.state.pesoLiq || ''} onChange={this.atualizaNome} required />
                                                <Form.Control.Feedback type="invalid">Campo obrigatório.</Form.Control.Feedback>
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="pesobruto" className="mb-3">
                                                <Form.Label>Peso Bruto</Form.Label>
                                                <Form.Control type="text" placeholder="Insira o peso bruto" value={this.state.pesoBruto || ''} onChange={this.atualizaDescricao} />
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="largura" className="mb-3">
                                                <Form.Label>Largura</Form.Label>
                                                <Form.Control type="text" placeholder="Insira a largura" value={this.state.larguraProduto || ''} onChange={this.atualizaNome} required />
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="altura" className="mb-3">
                                                <Form.Label>Altura</Form.Label>
                                                <Form.Control type="text" placeholder="Insira a altura" value={this.state.alturaProduto || ''} onChange={this.atualizaDescricao} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="profundidade" className="mb-3">
                                                <Form.Label>Profundidade</Form.Label>
                                                <Form.Control type="text" placeholder="Insira a profundidade" value={this.state.profundidadeProduto || ''} onChange={this.atualizaNome} required />
                                                <Form.Control.Feedback type="invalid">Campo obrigatório.</Form.Control.Feedback>
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="pesobruto" className="mb-3">
                                                <Form.Label>Volumes</Form.Label>
                                                <Form.Control type="text" placeholder="Insira o volume" value={this.state.volumes || ''} onChange={this.atualizaDescricao} />
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="itenscaixa" className="mb-3">
                                                <Form.Label>Itens p/ caixa</Form.Label>
                                                <Form.Control type="text" placeholder="Digite o volume" value={this.state.itensPorCaixa || ''} onChange={this.atualizaNome} required />
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="unidade" className="mb-3">
                                                <Form.Label>Unidade de medida</Form.Label>
                                                <Form.Select as="select" placeholder="Selecione o frete" value={this.state.unidadeMedida || ''} onChange={this.atualizaDescricao} >
                                                    <option value="">Metros</option>
                                                    <option value="">Centimetros</option>
                                                    <option value="">Milímetro</option>
                                                </Form.Select>
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="gtin" className="mb-3">
                                                <Form.Label>GTIN/EAN </Form.Label>
                                                <Form.Control type="text" placeholder="SEM GTIN" value={this.state.gtin || ''} onChange={this.atualizaNome} required />
                                                <Form.Control.Feedback type="invalid">Campo obrigatório.</Form.Control.Feedback>
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="gtintributario" className="mb-3">
                                                <Form.Label>Volumes</Form.Label>
                                                <Form.Control type="text" placeholder="SEM GTIN" value={this.state.volumes || ''} onChange={this.atualizaDescricao} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={2} md={12}>

                                            <Form.Group controlId="descricaocurta" className="mb-3">
                                                <Form.Label>Descrição Curta (Descrição Principal) </Form.Label>
                                                <Form.Control as="textarea" rows={3} placeholder="Insira a descrição curta" value={this.state.descricaoCurta || ''} onChange={this.atualizaInformacaoContato} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={2} md={12}>

                                            <Form.Group controlId="descricaocurta" className="mb-3">
                                                <Form.Label>Descrição Complementar</Form.Label>
                                                <Form.Control as="textarea" rows={3} placeholder="Insira a descrição complementar" value={this.state.descricaoComplementar || ''} onChange={this.atualizaInformacaoContato} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={2} md={12}>
                                            <Form.Group controlId="linkexterno" className="mb-3">
                                                <Form.Label>Link Externo</Form.Label>
                                                <Form.Control type="text" placeholder="insira o link externo" value={this.state.linkExterno || ''} onChange={this.atualizaDescricao} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={2} md={12}>
                                            <Form.Group controlId="video" className="mb-3">
                                                <Form.Label>Video</Form.Label>
                                                <Form.Control type="text" placeholder="insira o link do video" value={this.state.urlVideo || ''} onChange={this.atualizaDescricao} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col xs={2} md={12}>
                                            <Form.Group controlId="observacoes" className="mb-3">
                                                <Form.Label>Observações</Form.Label>
                                                <Form.Control tas="textarea" rows={3} placeholder="insira as observações" value={this.state.observacoes || ''} onChange={this.atualizaDescricao} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                </Tab>
                                <Tab eventKey="imagens" title="Imagens">
                                    <Row>
                                        <Col xs={2} md={12}>
                                            <Form.Group controlId="observacoes" className="mb-3">
                                                <Form.Label>Imagens</Form.Label>
                                                <Form.Control tas="textarea" rows={3} placeholder="insira as observações" value={''} onChange={this.atualizaDescricao} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                </Tab>
                                <Tab eventKey="estoque" title="Estoque">
                                    <Row>
                                        <Col xs={1} md={3}>
                                            <Form.Group controlId="minimo" className="mb-3">
                                                <Form.Label>Minimo</Form.Label>
                                                <Form.Control tas="text" placeholder="insira o minimo" value={this.state.estoqueMinimo || ''} onChange={this.atualizaDescricao} />
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="maximo" className="mb-3">
                                                <Form.Label>Máximo</Form.Label>
                                                <Form.Control tas="text" placeholder="insira o maximo" value={this.state.estoqueMaximo || ''} onChange={this.atualizaDescricao} />
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="crossdocking" className="mb-3">
                                                <Form.Label>Crossdocking</Form.Label>
                                                <Form.Control tas="text" placeholder="insira o crossdocking" value={this.state.crossdocking || ''} onChange={this.atualizaDescricao} />
                                            </Form.Group>
                                        </Col>
                                        <Col xs={2} md={3}>
                                            <Form.Group controlId="localizacao" className="mb-3">
                                                <Form.Label>Localização</Form.Label>
                                                <Form.Control tas="text" placeholder="insira a localização" value={this.state.localizacao || ''} onChange={this.atualizaDescricao} />
                                            </Form.Group>
                                        </Col>
                                    </Row>
                                </Tab>
                                <Tab eventKey="fornecedores" title="Fornecedores">
                                </Tab>
                            </Tabs>
                            <Container>
                                <Row className="text-center">
                                    <Col>
                                        <Form.Group controlId="buttonSalvar" className="mb-3">
                                            <div className="button-container d-flex justify-content-center">
                                                <Button bsPrefix="btn-salvar-button" variant="primary" type="submit">
                                                    Salvar
                                                </Button>
                                                <Button bsPrefix="btn-cancelar-button" variant="warning" onClick={this.fecharModal}>
                                                    Cancelar
                                                </Button>
                                            </div>
                                        </Form.Group>
                                    </Col>
                                </Row>
                            </Container>
                        </Container>
                    </Form>
                </Modal.Body>
            </Modal >
        )
    }
}

export default Produto;