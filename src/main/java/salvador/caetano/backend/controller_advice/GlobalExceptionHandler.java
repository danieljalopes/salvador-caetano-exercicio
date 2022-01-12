package salvador.caetano.backend.controller_advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import salvador.caetano.backend.dto.GlobalErrorMessage;
import salvador.caetano.backend.exception.PrestacaoMensalException;
import salvador.caetano.backend.exception.TipoFinanciamentoException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = PrestacaoMensalException.class)
    public ResponseEntity prestacaoMensalException(PrestacaoMensalException exception) {
        logger.error("PrestacaoMensalException",exception);

        GlobalErrorMessage error = GlobalErrorMessage.builder().message(exception.getMessage()).build();
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TipoFinanciamentoException.class)
    public ResponseEntity tipoFinanciamentoException(TipoFinanciamentoException exception) {
        logger.error("TipoFinanciamentoException",exception);
        GlobalErrorMessage error = GlobalErrorMessage.builder().message(exception.getMessage()).build();
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception exception) {
        logger.error("TipoFinanciamentoException",exception);
        GlobalErrorMessage error = GlobalErrorMessage.builder().message(exception.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}