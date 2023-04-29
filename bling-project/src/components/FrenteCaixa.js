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
import Suggest from 'react-autosuggest';



class FrenteCaixa extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            buscaProduto: '',
            produtos: [],
            produtoSelecionado: null,
            carregando: false,
            preco: null, // mover o preco para fora do produtoSelecionado
            produtosSelecionados: [],
            precoProdutoSelecionado: '',
            quantidade: 1,
        };
    }

    buscarProdutos = (value) => {
        console.log("Buscando produto por:", value);
        this.setState({ buscaProduto: value, carregando: true });
        fetch(`http://localhost:8081/api/v1/produtos`)
            .then((resposta) => {
                if (!resposta.ok) {
                    throw new Error('Erro na chamada da API');
                }
                return resposta.json();
            })
            .then((dados) => {
                if (dados.retorno.produtos) {
                    const produtosFiltrados = dados.retorno.produtos.filter(
                        (produto) =>
                            (produto.produto.descricao && produto.produto.descricao.toLowerCase().includes(value.toLowerCase())) ||
                            (produto.produto.codigo && produto.produto.codigo.toLowerCase().includes(value.toLowerCase())) ||
                            (produto.produto.gtin && produto.produto.gtin.toLowerCase().includes(value.toLowerCase())) ||
                            (produto.produto.gtinEmbalagem && produto.produto.gtinEmbalagem.toLowerCase().includes(value.toLowerCase())) ||
                            (produto.produto.descricaoFornecedor && produto.produto.descricaoFornecedor.toLowerCase().includes(value.toLowerCase())) ||
                            (produto.produto.idFabricante && produto.produto.idFabricante.toLowerCase().includes(value.toLowerCase()))
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

    selecionarProduto = (produto) => {
        this.setState({
            produtoSelecionado: produto,
            preco: produto.produto.preco,
            produtos: [],
        });
        this.atualizarBuscaProduto({ target: { value: '' } });
    };



    adicionarProdutoSelecionado = (produtoSelecionado) => {
        const { produtosSelecionados, quantidade } = this.state;
        const produtoExistente = produtosSelecionados.find((produto) => produto.produto.id === produtoSelecionado.produto.id);

        if (produtoExistente) {
            produtoExistente.quantidade += quantidade; // Adiciona a quantidade selecionada
        } else {
            produtosSelecionados.push({
                produto: produtoSelecionado.produto,
                quantidade: quantidade, // Salva a quantidade selecionada
            });
        }

        this.setState({
            produtosSelecionados: produtosSelecionados,
            produtoSelecionado: null,
            preco: 0,
            quantidade: 1, // Reinicia o campo de quantidade
            produtos: [],
            buscaProduto: '',
        });
    };


    calcularTotal() {
        let total = 0;
        this.state.produtosSelecionados.forEach((produto) => {
            total += Number(produto.produto.preco);
        });
        return total.toFixed(2);
    }

    atualizaPreco = (event) => {
        const preco = event.target.value;
        this.setState({ precoProdutoSelecionado: preco });
    }


    atualizarBuscaProduto = (e) => {
        this.setState({
            buscaProduto: e.target.value
        })
    }

    atualizarQuantidade = (e) => {
        this.setState({
            quantidade: e.target.value
        })
    }

    atualizarDesconto = (e) => {
        this.setState({
            desconto: e.target.value
        })
    }



    render() {
        const { produtos, produtoSelecionado, buscaProduto, carregando, preco } = this.state;
        return (
            <div className="container">
                <div className="produtos">
                    <div>
                        <div>
                            <label htmlFor="produto">Produto</label>
                            <div>
                                <input
                                    type="text"
                                    id="produto"
                                    placeholder="Digite a descrição do produto"
                                    value={buscaProduto}
                                    onChange={this.atualizarBuscaProduto}
                                />
                                <button onClick={() => this.buscarProdutos(buscaProduto)}>Buscar</button>
                            </div>
                            <ul>
                                {produtos.map((produto) => (
                                    <li key={produto.produto.id} onClick={() => this.selecionarProduto(produto)}>
                                        {produto.produto.descricao} - R${produto.produto.preco}
                                    </li>
                                ))}
                            </ul>
                            {produtoSelecionado && (
                                <div>
                                    <h2>Produto selecionado: {produtoSelecionado.produto.descricao}</h2>
                                </div>
                            )}
                            <div>
                                <label htmlFor="quantidade">Quantidade</label>
                                <input type="number" id="quantidade" name="quantidade" min="1" value={this.state.quantidade} onChange={this.atualizarQuantidade} />
                            </div>
                            <label htmlFor="desconto">Desconto</label>
                            <input type="text" id="desconto" name="desconto" value={this.state.desconto} onChange={this.atualizarDesconto} />

                            <div>
                                <label htmlFor="preco">Preço</label>
                                <input type="text" id="preco" name="preco" value={preco} onChange={this.atualizaPreco} />
                                {produtoSelecionado && (
                                    <button onClick={() => this.adicionarProdutoSelecionado(produtoSelecionado)}>Adicionar</button>
                                )}
                            </div>
                            {carregando && <div>Carregando...</div>}
                        </div>
                    </div>
                    <div className="carrinho">
                        <div>
                            <h2>Total: R${this.calcularTotal()}</h2>
                            <ul>
                                {this.state.produtosSelecionados.map((produto) => (
                                    <li key={produto.produto.id}>
                                        {produto.produto.descricao} - R${produto.produto.preco}
                                    </li>
                                ))}
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}






//                 <div className="form-group">
//                     <label htmlFor="inputAddress">Address</label>
//                     <input type="text" className="form-control" id="inputAddress" placeholder="1234 Main St" />
//                 </div>
//                 <div className="form-group">
//                     <label htmlFor="inputAddress2">Address 2</label>
//                     <input type="text" className="form-control" id="inputAddress2" placeholder="Apartment, studio, or floor" />
//                 </div>
//                 <div className="form-row">
//                     <div className="form-group col-md-6">
//                         <label htmlFor="inputCity">City</label>
//                         <input type="text" className="form-control" id="inputCity" />
//                     </div>
//                     <div className="form-group col-md-4">
//                         <label htmlFor="inputState">State</label>
//                         <select id="inputState" className="form-control">
//                             <option selected>Choose...</option>
//                             <option>...</option>
//                         </select>
//                     </div>
//                     <div className="form-group col-md-2">
//                         <label htmlFor="inputZip">Zip</label>
//                         <input type="text" className="form-control" id="inputZip" />
//                     </div>
//                 </div>
//                 <div className="form-group">
//                     <div className="form-check">
//                         <input className="form-check-input" type="checkbox" id="gridCheck" />
//                         <label className="form-check-label" htmlFor="gridCheck">
//                             Check me out
//                         </label>
//                     </div>
//                 </div>
//                 <button type="submit" className="btn btn-primary">Sign in</button>
//             </form >
//         );
//     }
// }

export default FrenteCaixa;

