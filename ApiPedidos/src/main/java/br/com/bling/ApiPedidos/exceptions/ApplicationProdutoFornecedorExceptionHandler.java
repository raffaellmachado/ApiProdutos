package br.com.bling.ApiPedidos.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApplicationProdutoFornecedorExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiPedidoException.class)
    public ResponseEntity<String> handleApiApiProdutoFornecedorException(ApiPedidoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoFornecedorListaException.class)
    public ResponseEntity<String> handleListaProdutoFornecedorListaException(ProdutoFornecedorListaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoFornecedorIdException.class)
    public ResponseEntity<String> handleIdProdutoFornecedorIdException(ProdutoFornecedorIdException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoFornecedorCadastroException.class)
    public ResponseEntity<String> handleProdutoFornecedorCadastroException(ProdutoFornecedorCadastroException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoFornecedorAtualizarException.class)
    public ResponseEntity<String> handleProdutoFornecedorAtualizarExceptionException(ProdutoFornecedorAtualizarException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
