package salvador.caetano.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import salvador.caetano.backend.dto.PrestacaoMensalRequestDto;
import salvador.caetano.backend.dto.PrestacaoMensalResponseDto;
import salvador.caetano.backend.exception.PrestacaoMensalException;
import salvador.caetano.backend.exception.TipoFinanciamentoException;
import salvador.caetano.backend.service.prestacao.mensal.PrestacaoMensalService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/prestacao-mensal")
public class PrestacaoMensalCalculadorController {

    private PrestacaoMensalService prestacaoMensalService;

    @Autowired
    public PrestacaoMensalCalculadorController(PrestacaoMensalService prestacaoMensalService){
        this.prestacaoMensalService = prestacaoMensalService;
    }

    @PostMapping("/calcular")
    public ResponseEntity<PrestacaoMensalResponseDto> calcular(@Valid @RequestBody PrestacaoMensalRequestDto prestacaoMensalRequestDto) throws PrestacaoMensalException, TipoFinanciamentoException {

        PrestacaoMensalResponseDto prestacaoMensalResponseDto = PrestacaoMensalResponseDto
                .builder()
                .valorPrestacao(prestacaoMensalService.calcularPrestacao(prestacaoMensalRequestDto))
                .build();
        return ResponseEntity.ok(prestacaoMensalResponseDto);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
