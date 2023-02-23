package br.com.bling.ApiProdutosFornecedores.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@Slf4j
@ControllerAdvice
public class ApplicationProdutoFornecedorExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiProdutoFornecedorException.class)
    public ResponseEntity<String> handleApiApiProdutoFornecedorException(ApiProdutoFornecedorException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoFornecedorListaNaoEncontradoException.class)
    public ResponseEntity<String> handleListaProdutoFornecedorListaNaoEncontradoException(ProdutoFornecedorListaNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(IdProdutoFornecedorNaoEncontradoException.class)
    public ResponseEntity<String> handleIdProdutoFornecedorNaoEncontradoException(IdProdutoFornecedorNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoFornecedorCadastroException.class)
    public ResponseEntity<String> handleProdutoFornecedorCadastroException(ProdutoFornecedorCadastroException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
