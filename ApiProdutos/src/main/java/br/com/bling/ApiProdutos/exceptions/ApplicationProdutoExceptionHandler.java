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

    @ExceptionHandler(ProdutoListaNaoEncontradoException.class)
    public ResponseEntity<String> handleListaProdutoNaoEncontradoException(ProdutoListaNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoCodigoNaoEncontradoException.class)
    public ResponseEntity<String> handleCodigoProdutoNaoEncontradoException(ProdutoCodigoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoNaoEncontradoExclusaoException.class)
    public ResponseEntity<String> ProdutoNaoEncontradoParaExclusaoException(ProdutoNaoEncontradoExclusaoException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoCadastroException.class)
    public ResponseEntity<String> handleProdutoCadastroException(ProdutoCadastroException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
