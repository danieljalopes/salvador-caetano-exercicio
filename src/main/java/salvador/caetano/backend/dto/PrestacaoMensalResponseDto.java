package salvador.caetano.backend.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PrestacaoMensalResponseDto {

    /**
     * Valor da prestaçao
     */
    private Float valorPrestacao;
}
