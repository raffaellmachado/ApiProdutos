import React from "react";

import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Table from 'react-bootstrap/Table';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import { Container } from "react-bootstrap";
import { FaSync } from 'react-icons/fa';
import Spinner from 'react-bootstrap/Spinner';

import '../css/FrenteCaixa.css';
import { parse } from 'js2xmlparser';


class FrenteCaixa extends React.Component {

    state = {
        busca: "",
        produtos: [],
        produtoSelecionado: null,
        carregando: false
    };

    handleChangeBusca = (event) => {
        this.setState({ busca: event.target.value });
    };

    buscarProdutos = () => {
        const { busca } = this.state;
        console.log("Buscando por:", busca);
        this.setState({ carregando: true });
        fetch(`http://localhost:8081/api/v1/produtos`)
            .then((resposta) => resposta.json())
            .then((dados) => {
                if (dados.retorno.produtos) {
                    const produtosFiltrados = dados.retorno.produtos.filter(
                        (produto) =>
                            (produto.produto.descricao && produto.produto.descricao.toLowerCase().includes(busca.toLowerCase())) ||
                            (produto.produto.codigo && produto.produto.codigo.toLowerCase().includes(busca.toLowerCase())) ||
                            (produto.produto.gtin && produto.produto.gtin.toLowerCase().includes(busca.toLowerCase())) ||
                            (produto.produto.gtinEmbalagem && produto.produto.gtinEmbalagem.toLowerCase().includes(busca.toLowerCase())) ||
                            (produto.produto.descricaoFornecedor && produto.produto.descricaoFornecedor.toLowerCase().includes(busca.toLowerCase())) ||
                            (produto.produto.idFabricante && produto.produto.idFabricante.toLowerCase().includes(busca.toLowerCase()))
                    );
                    console.log("Objeto retornado:", produtosFiltrados);
                    this.setState({
                        produtos: produtosFiltrados,
                        produtoSelecionado: null,
                        carregando: false,
                    });
                } else {
                    this.setState({ produtos: [], carregando: false });
                }
            })
            .catch((error) => {
                console.log("Erro ao buscar produtos:", error);
                this.setState({ produtos: [], carregando: false });
            });
    };

    cadastraContato = (xmlContato) => {
        const parser = new DOMParser();
        const xml = parser.parseFromString(xmlContato, 'text/xml');
        const stringXml = new XMLSerializer().serializeToString(xml);

        fetch('http://localhost:8080/api/v1/cadastrarcontato', {
            method: 'POST',
            body: stringXml,
            headers: {
                'Content-Type': 'application/xml'
            }
        })
    }

    handleSelectProduto = (produto) => {
        this.setState({ produtoSelecionado: produto });
    };

    calcularSubTotal = () => {
        const quantidade = parseInt(document.getElementById("quantidade").value);
        const desconto = parseFloat(document.getElementById("desconto").value || 0);
        const preco = parseFloat(this.state.produtoSelecionado?.produto?.preco || 0);

        const valorDesconto = preco * (desconto / 100);
        const valorComDesconto = (preco - valorDesconto) * quantidade;

        const subTotal = valorComDesconto.toFixed(2);

        document.getElementById("subTotal").value = subTotal;
    }


    render() {
        const { produtos, busca, carregando, produtoSelecionado } = this.state;

        return (
            <div>
                <input
                    type="text"
                    value={busca}
                    onChange={this.handleChangeBusca}
                />
                <button onClick={this.buscarProdutos}>Buscar</button>
                {carregando ? (
                    <p>Carregando...</p>
                ) : produtos.length > 0 ? (
                    <div>
                        <p>Selecione um produto:</p>
                        <ul>
                            {produtos.map((produto) => (
                                <li
                                    key={produto.produto.codigo}
                                    onClick={() =>
                                        this.handleSelectProduto(produto)
                                    }
                                >
                                    {produto.produto.descricao}
                                </li>
                            ))}
                        </ul>
                    </div>
                ) : (
                    <p>Nenhum produto encontrado</p>
                )}

                {produtoSelecionado && (
                    <div>
                        <p>Produto selecionado:</p>
                        <ul>
                            <li>{produtoSelecionado.produto.descricao}</li>
                            <li>{produtoSelecionado.produto.codigo}</li>
                            <li>Preço: R${produtoSelecionado.produto.preco}</li>
                        </ul>
                        <label htmlFor="quantidade">Quantidade:</label>
                        <input type="number" id="quantidade" name="quantidade" onInput={this.calcularSubTotal} />
                        <br />
                        <label htmlFor="desconto">Desconto:</label>
                        <input type="number" id="desconto" name="desconto" defaultValue="0" onInput={this.calcularSubTotal} />
                        <br />
                        <label htmlFor="subTotal">Sub Total:</label>
                        <input type="text" id="subTotal" name="subTotal" disabled />
                    </div>
                )}
            </div>
        );
    }
}



export default FrenteCaixa;

