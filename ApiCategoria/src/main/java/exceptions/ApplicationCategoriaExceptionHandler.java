package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ApplicationCategoriaExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiCategoriaException.class)
    public ResponseEntity<String> handleApiCategoriaException(ApiCategoriaException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(CategoriaListaNaoEncontradoException.class)
    public ResponseEntity<String> handleCategoriaListaNaoEncontradoException(CategoriaListaNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(CategoriaIdCategoriaNaoEncontradoException.class)
    public ResponseEntity<String> handleCategoriaIdCategoriaNaoEncontradoException(CategoriaIdCategoriaNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CategoriaCadastroException.class)
    public ResponseEntity<String> handleCategoriaCadastroException(CategoriaCadastroException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
