import React from "react";
import { Spinner } from 'react-bootstrap';
import { Button } from 'react-bootstrap';
import { Modal } from 'react-bootstrap';
import { Container } from 'react-bootstrap'
import { Col } from 'react-bootstrap'
import { Row } from 'react-bootstrap'
import { Form } from 'react-bootstrap'
import { Table } from "react-bootstrap";

import { BsPersonAdd } from 'react-icons/bs';


import 'bootstrap/dist/css/bootstrap.min.css';
import '../css/CadastroLoja.css';


import { BsTrashFill } from 'react-icons/bs';
import { BsPencilSquare } from 'react-icons/bs';

class CadastroLoja extends React.Component {


  constructor(props) {
    super(props);

    this.state = {
      lojas: [],
      modalEditarProduto: false,
    };
  }

  componentDidMount() {
    this.buscaLojas();
  }

  componentDidUpdate(prevProps, prevState) {
    const { modalEditarLoja } = this.state;
    const prevModalEditarLoja = prevState.modalEditarLoja;

    if (prevModalEditarLoja && !modalEditarLoja) {
      // Se o modal estiver fechando, chame a função para buscar as lojas novamente
      this.buscaLojas();
    }
  }

  buscaLojas = () => {
    fetch('https://dev-api-forma-pagamento.azurewebsites.net/api/v1/selecionarLojas')
      .then(response => response.json())
      .then(data => {
        this.setState({ lojas: data });
      })
      .catch(error => {
        console.error('Erro ao buscar as lojas:', error);
      });
  };

  buscarIdLoja = (idLoja) => {
    fetch(`https://dev-api-forma-pagamento.azurewebsites.net/api/v1/${idLoja}`)
      .then(response => response.json())
      .then(data => {
        console.log('Resposta da API:', data);

        if (data && data.length > 0) {
          console.log('Loja encontrada:', data[0]);

          // Caso a loja seja encontrada, você pode atualizar o estado com a loja encontrada
          this.setState({ lojaEncontrada: data[0] });
        } else {
          console.log('Loja não encontrada.');
          // Caso a loja não seja encontrada, você pode mostrar uma mensagem de erro ou fazer alguma outra tratativa
        }
      })
      .catch(error => {
        console.error('Erro ao buscar a loja:', error);
      });
  };

  deletarLoja = (idLoja) => {
    fetch(`https://dev-api-forma-pagamento.azurewebsites.net/api/v1/${idLoja}`, {
      method: 'DELETE',
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Erro ao deletar loja');
        }
        // Atualizar o estado após a deleção bem-sucedida (opcional)
        this.buscaLojas();
      })
      .catch(error => {
        console.error('Erro ao deletar a loja:', error);
      });
  };

  adicionarLoja = (selecionaLoja) => {
    fetch('https://dev-api-forma-pagamento.azurewebsites.net/api/v1/adicionarLoja/', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(selecionaLoja),
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Erro ao adicionar loja');
        }
        // Atualizar o estado após a adição bem-sucedida (opcional)
        this.buscaLojas();
      })
      .catch(error => {
        console.error('Erro ao adicionar a loja:', error);
      });
  };

  handleModalEditarLoja = (loja) => {
    // Verificar se a loja existe
    if (loja) {
      // Caso a loja exista, setar o estado com os dados para edição
      this.setState({
        modalEditarLoja: true,
        selecionaLoja: {
          idLoja: loja.idLoja,
          nomeLoja: loja.nomeLoja,
          unidadeLoja: loja.unidadeLoja,
        },
      });
    } else {
      // Caso a loja não exista, setar o estado com os dados para adicionar nova loja
      this.setState({
        modalEditarLoja: true,
        selecionaLoja: {
          idLoja: '',
          nomeLoja: '',
          unidadeLoja: '',
        },
      });
    }
  };

  fecharModalEditarLoja = () => {
    // Limpe os dados da loja editando quando o modal for fechado
    this.setState({
      modalEditarLoja: false,
      selecionaLoja: null,
    });
  };

  handleCadastrarClick = () => {
    // Chamar a função handleModalEditarLoja sem nenhum argumento para abrir o modal de cadastro de nova loja
    this.handleModalEditarLoja(null);
  };

  atualizarIDLoja = (event) => {
    const idLoja = event.target.value;
    this.setState(prevState => ({
      selecionaLoja: {
        ...prevState.selecionaLoja,
        idLoja,
      },
    }));
  };

  // Função para atualizar o estado com o valor do campo Nome da Loja
  atualizarNomeLoja = (event) => {
    const nomeLoja = event.target.value;
    this.setState(prevState => ({
      selecionaLoja: {
        ...prevState.selecionaLoja,
        nomeLoja,
      },
    }));
  };

  // Função para atualizar o estado com o valor do campo Unidade de Negócio
  atualizarUnidadeLoja = (event) => {
    const unidadeLoja = event.target.value;
    this.setState(prevState => ({
      selecionaLoja: {
        ...prevState.selecionaLoja,
        unidadeLoja,
      },
    }));
  };

  salvarLoja = () => {
    const { selecionaLoja } = this.state;

    this.adicionarLoja(selecionaLoja);
    this.fecharModalEditarLoja();
  };

  render() {

    const { lojas, modalEditarLoja, selecionaLoja } = this.state;

    return (
      <div>
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
                  <td>
                    <Button variant="light" title="Editar loja" className="transparent-button" onClick={() => {
                      this.handleModalEditarLoja(loja);
                    }}>
                      <BsPencilSquare className="blue-icon" />
                    </Button>
                    <Button variant="light" title="Excluir loja" className="transparent-button" onClick={() => {
                      this.deletarLoja(loja.idLoja);
                    }}>
                      <BsTrashFill className="red-icon" />
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Container>
        <div className="container-btn d-flex justify-content-center">
          <Row xs={12} className="justify-content-center mt-3">
            <Button onClick={this.handleCadastrarClick} className="btn btn-warning btn-lg btn-block">
              <BsPersonAdd style={{ marginRight: '0.5rem' }} />
              Cadastrar
            </Button>
          </Row>
        </div>

        {/* Modal */}
        <Modal show={modalEditarLoja} onHide={this.fecharModalEditarLoja} size="lg" centered>
          <Modal.Header closeButton>
            <Modal.Title>{selecionaLoja ? 'Editar Loja' : 'Adicionar Nova Loja'}</Modal.Title>
          </Modal.Header>
          <Modal.Body style={{ padding: '20px' }}>
            {selecionaLoja && (
              <div>
                <Row>
                  <Col>
                    <Form.Group className="mb-3">
                      <Form.Label htmlFor="idLoja" className="texto-campos">ID Loja</Form.Label>
                      <Form.Control
                        type="text"
                        id="idLoja"
                        className="form-control"
                        name="idLoja"
                        value={selecionaLoja.idLoja}
                        onChange={this.atualizarIDLoja}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group className="mb-3">
                      <Form.Label htmlFor="nomeLoja" className="texto-campos">Nome da Loja</Form.Label>
                      <Form.Control
                        type="text"
                        id="nomeLoja"
                        className="form-control"
                        name="nomeLoja"
                        value={selecionaLoja.nomeLoja}
                        onChange={this.atualizarNomeLoja}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group className="mb-3">
                      <Form.Label htmlFor="unidadeNegocio" className="texto-campos">Unidade de Negócio</Form.Label>
                      <Form.Control
                        type="text"
                        id="unidadeNegocio"
                        className="form-control"
                        name="unidadeNegocio"
                        value={selecionaLoja.unidadeLoja}
                        onChange={this.atualizarUnidadeLoja}
                      />
                    </Form.Group>
                  </Col>
                </Row>
              </div>
            )}
          </Modal.Body>
          <Modal.Footer>
            <Button variant="outline-secondary" className="mr-2" onClick={this.fecharModalEditarLoja}>Cancelar</Button>
            <Button variant="secondary" className="mr-2" onClick={() => this.salvarLoja()}>Salvar</Button>
          </Modal.Footer>
        </Modal>
      </div>
    );
  }
}

export default CadastroLoja;
