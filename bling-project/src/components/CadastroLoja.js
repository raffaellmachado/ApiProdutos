import React from "react";
import { Spinner } from 'react-bootstrap';
import { Button } from 'react-bootstrap';
import { Modal } from 'react-bootstrap';
import { Container } from 'react-bootstrap'
import { Col } from 'react-bootstrap'
import { Row } from 'react-bootstrap'
import { Form } from 'react-bootstrap'
import { Table } from "react-bootstrap";
import { Alert } from "react-bootstrap";
import { Offcanvas } from 'react-bootstrap';
import { Image } from 'react-bootstrap';
import { InputGroup } from "react-bootstrap";

class CadastroLoja extends React.Component {

  state = {
    lojas: [],
    showModal: false,
  };

  componentDidMount() {
    this.fetchLojas();
  }

  fetchLojas = () => {
    fetch('http://localhost:8086/api/v1/selecionarLojas')
      .then(response => response.json())
      .then(data => {
        this.setState({ lojas: data });
      })
      .catch(error => {
        console.error('Erro ao buscar as lojas:', error);
      });
  };

  handleCadastrarClick = () => {
    this.setState({ showModal: true });
  };

  handleCloseModal = () => {
    this.setState({ showModal: false });
  };

  render() {
    const { lojas, showModal } = this.state;

    return (
      <div>
        <Button onClick={this.handleCadastrarClick} className="btn btn-primary">Cadastrar</Button>
        <Container>
          <Table className="table">
            <thead>
              <tr>
                <th>ID Loja</th>
                <th>Nome Loja</th>
                <th>Unidade Loja</th>
                <th>Ações</th>
              </tr>
            </thead>
            <tbody>
              {lojas.map(loja => (
                <tr key={loja.id}>
                  <td>{loja.idLoja}</td>
                  <td>{loja.nomeLoja}</td>
                  <td>{loja.unidadeLoja}</td>
                  <td></td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Container>

        {/* Modal */}
        {showModal && (
          <div className="modal" tabIndex="-1" role="dialog">
            <div className="modal-dialog" role="document">
              <div className="modal-content">
                <div className="modal-header">
                  <h5 className="modal-title">Cadastrar Loja</h5>
                  <button type="button" className="close" onClick={this.handleCloseModal}>
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div className="modal-body">
                  {/* Conteúdo do formulário de cadastro */}
                </div>
                <div className="modal-footer">
                  <button type="button" className="btn btn-secondary" onClick={this.handleCloseModal}>Fechar</button>
                  <button type="button" className="btn btn-primary">Salvar</button>
                </div>
              </div>
            </div>
          </div>
        )}
      </div>
    );
  }
}

export default CadastroLoja;
