package br.com.bling.ApiDeposito.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@Slf4j
@ControllerAdvice
public class ApplicationDepositoExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiDepositoException.class)
    public ResponseEntity<String> handleApiProdutoException(ApiDepositoException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(DepositoListaNaoEncontradoException.class)
    public ResponseEntity<String> handleListaProdutoNaoEncontradoException(DepositoListaNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(DepositoIdDepositoNaoEncontradoException.class)
    public ResponseEntity<String> handleCodigoProdutoNaoEncontradoException(DepositoIdDepositoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DepositoCadastroException.class)
    public ResponseEntity<String> handleProdutoCadastroException(DepositoCadastroException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
