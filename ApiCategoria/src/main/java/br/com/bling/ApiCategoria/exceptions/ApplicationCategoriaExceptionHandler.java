package br.com.bling.ApiCategoria.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@Slf4j
@ControllerAdvice
public class ApplicationCategoriaExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiCategoriaException.class)
    public ResponseEntity<String> handleApiCategoriaException(ApiCategoriaException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(CategoriaListaException.class)
    public ResponseEntity<String> handleCategoriaListaNaoEncontradoException(CategoriaListaException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(CategoriaIdCategoriaException.class)
    public ResponseEntity<String> handleCategoriaIdCategoriaNaoEncontradoException(CategoriaIdCategoriaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CategoriaCadastroException.class)
    public ResponseEntity<String> handleCategoriaCadastroException(CategoriaCadastroException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
