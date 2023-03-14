package br.com.bling.ApiDepositos.exceptions;

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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DepositoListaException.class)
    public ResponseEntity<String> handleListaDepositoListaException(DepositoListaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DepositoIdDepositoException.class)
    public ResponseEntity<String> handleDepositoIdDepositoException(DepositoIdDepositoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DepositoCadastroException.class)
    public ResponseEntity<String> handleDepositoCadastroException(DepositoCadastroException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(DepositoAtualizarException.class)
    public ResponseEntity<String> handleDepositoAtualizarException(DepositoAtualizarException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
