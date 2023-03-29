import React from "react";

import Button from "react-bootstrap/Button";
import Table from 'react-bootstrap/Table';

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
            carregando: true

        };
    }

    componentDidMount() {
        this.buscarProduto();
    }

    componentWillUnmount() {

    }

    //GET - MÉTODO PARA CONSUMO DA API CONTATOS
    buscarProduto = () => {
        fetch("http://localhost:8080/api/v1/produtos")
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
        fetch("http://localhost:8080/api/v1/produto/" + codigo, { method: 'GET' })
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



    renderTabela() {

        if (this.state.carregando) {
            return (
                <div>
                    <p>Carregando produtos...</p>
                </div>
            )
        } else {
            return (
                <div className="tabela">
                    <Table striped bordered hover className="table-dark" responsive="sm">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th title="Descrição">Descrição</th>
                                <th title="Código">Código</th>
                                <th title="Unidade">Unidade</th>
                                <th title="Preço">Preço</th>
                                <th title="Estoque">Estoque</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.produtos.map((produtos) =>
                                    <tr key={produtos.produto.id}>
                                        <td>{produtos.produto.descricao}</td>
                                        <td>{produtos.produto.codigo}</td>
                                        <td>{produtos.produto.unidade}</td>
                                        <td>{produtos.produto.preco}</td>
                                        <td>{produtos.produto.estoqueMaximo}</td>
                                        <td>
                                            <Button variant="outline-success" onClick={() => this.carregarProdutos(produtos.produto.codigo)}>Atualizar</Button>
                                        </td>
                                    </tr>
                                )
                            }
                            {this.state.produtos.length === 0 && <tr><td colSpan="6">Nenhum produto cadastrado.</td></tr>}
                        </tbody>
                    </Table>
                    <div>
                        <Button variant="success" className="cadastro-button" onClick={() => this.setState({ showCadastro: true })}>
                            + Incluir Produto
                        </Button>
                        {this.state.showCadastro && <CadastroProduto />}
                    </div>
                </div>
            )
        }
    }



    //Ações do botão SUBMIT (Cadastrar).
    submit = (event) => {
        event.preventDefault();

        if (this.state.id === 0) {
            const produto = {
                descricao: this.state.descricao,
                codigo: this.state.codigo,
                unidade: this.state.unidade,
                preco: this.state.preco,
                estoqueMaximo: this.state.estoqueMaximo
            }
            const xmlProduto = parse('produto', produto);
            this.cadastraProduto(xmlProduto);
        } else {
            const produto = {
                id: this.state.id,
                descricao: this.state.descricao,
                codigo: this.state.codigo,
                unidade: this.state.unidade,
                preco: this.state.preco,
                estoqueMaximo: this.state.estoqueMaximo
            }
            const xmlProduto = parse('produto', produto);
            this.atualizarProduto(xmlProduto);
        }
    }


    //Ação para limpar o campos do modal para cadastrar um novo cliente.
    reset = () => {
        this.setState(
            {
                id: 0,
                descricao: '',
                codigo: '',
                unidade: '',
                preco: '',
                estoqueMaximo: '',
            })
    }

    render() {
        return (
            <div>
                {this.renderTabela()}
            </div>
        )
    }
}

export default Produto;