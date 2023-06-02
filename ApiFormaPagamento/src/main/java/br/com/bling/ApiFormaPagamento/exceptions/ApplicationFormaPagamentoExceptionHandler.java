package br.com.bling.ApiFormaPagamento.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApplicationFormaPagamentoExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiFormaPagamentoException.class)
    public ResponseEntity<String> handleApiApiProdutoFornecedorException(ApiFormaPagamentoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(FormaPagamentoListaException.class)
    public ResponseEntity<String> handleListaProdutoFornecedorListaException(FormaPagamentoListaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(FormaPagamentoException.class)
    public ResponseEntity<String> handleIdProdutoFornecedorIdException(FormaPagamentoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(FormaPagamentoCadastroException.class)
    public ResponseEntity<String> handleProdutoFornecedorCadastroException(FormaPagamentoCadastroException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(FormaPagamentoAtualizarException.class)
    public ResponseEntity<String> handleProdutoFornecedorAtualizarExceptionException(FormaPagamentoAtualizarException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
