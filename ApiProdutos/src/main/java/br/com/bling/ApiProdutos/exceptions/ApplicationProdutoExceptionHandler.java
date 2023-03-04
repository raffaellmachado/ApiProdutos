package br.com.bling.ApiProdutos.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@Slf4j
@ControllerAdvice
public class ApplicationProdutoExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiProdutoException.class)
    public ResponseEntity<String> handleApiProdutoException(ApiProdutoException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoListaException.class)
    public ResponseEntity<String> handleProdutoListaException(ProdutoListaException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoCodigoException.class)
    public ResponseEntity<String> handleProdutoCodigoException(ProdutoCodigoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoCodigoFornecedorException.class)
    public ResponseEntity<String> handleProdutoCodigoFOrnecedorException(ProdutoCodigoFornecedorException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoExclusaoException.class)
    public ResponseEntity<String> ProdutoExclusaoException(ProdutoExclusaoException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoCadastroException.class)
    public ResponseEntity<String> handleProdutoCadastroException(ProdutoCadastroException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoAtualizarException.class)
    public ResponseEntity<String> handleProdutoAtualizarException(ProdutoAtualizarException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
