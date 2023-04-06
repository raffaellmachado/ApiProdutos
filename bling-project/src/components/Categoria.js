import React from "react";

import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Table from 'react-bootstrap/Table';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import { Container } from "react-bootstrap";
import { FaSync, VscNewFile } from 'react-icons/fa';
import Spinner from 'react-bootstrap/Spinner';

import '../css/Contato.css';
import { parse } from 'js2xmlparser';


class Categoria extends React.Component {


    constructor(props) {
        super(props);

        this.state = {
            categorias: [],
            id: 0,
            descricao: '',
            idCategoriaPai: ''
        }
    }

    //GET - MÉTODO PARA CONSUMO DA API CONTATOS
    buscarCategoria = () => {
        fetch("http://localhost:8082/api/v1/categorias")
            .then(resposta => resposta.json())
            .then(dados => {
                if (dados.retorno.categorias) {
                    this.setState({ categorias: dados.retorno.categorias })
                } else {
                    this.setState({ categorias: [] })
                }
                this.setState({ carregando: false })
            })
    }

    componentDidMount() {
        this.buscarCategoria();
    }

    render() {
        if (this.state.carregando) {
            return (
                <div className="spinner-container">
                    <div className="d-flex align-items-center justify-content-center">
                        <Spinner variant="secondary" animation="border" role="status">
                            <span className="visually-hidden">Carregando categorias...</span>
                        </Spinner>
                    </div>
                    <div>
                        <p>Carregando categorias...</p>
                    </div>
                </div>
            )
        } else {
            return (
                <div className="background">
                    <div className="container">
                        <div className="">
                            <Button variant="success" bsPrefix="btn-cadastro-button" onClick={this.reset}>
                                + Incluir Cadastro
                            </Button>
                        </div>
                        <div>
                            <div className="table-tabela">
                                <Table striped bordered hover className="table-dark" responsive="sm">
                                    <thead>
                                        <tr>
                                            <th title="Identificador">ID</th>
                                            <th title="Código">Descrição</th>
                                            <th title="Nome">idCategoriaPai</th>
                                            <th title="Opções">Opções</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {
                                            this.state.categorias.map((categorias) =>
                                                <tr key={categorias.categoria.id}>
                                                    <td>{categorias.categoria.id}</td>
                                                    <td>{categorias.categoria.descricao}</td>
                                                    <td>{categorias.categoria.idCategoriaPai}</td>
                                                    <td>
                                                        <Button variant="warning" onClick={() => this.carregarContato(categorias.categoria.id)}>
                                                            <FaSync />
                                                        </Button>
                                                    </td>
                                                </tr>
                                            )
                                        }
                                        {this.state.categorias.length === 0 && <tr><td colSpan="6">Nenhum contato cadastrado.</td></tr>}
                                    </tbody>
                                </Table>
                            </div>
                        </div>
                    </div>
                </div>
            )
        }
    }

}

export default Categoria;

