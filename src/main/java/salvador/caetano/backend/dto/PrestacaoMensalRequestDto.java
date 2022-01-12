package salvador.caetano.backend.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
@Builder
public class PrestacaoMensalRequestDto {

    /**
     * Tipo de Financiamento
     */
    @NotNull(message = "Tipo de Financiamento e obrigatorio")
    @NotBlank(message = "Tipo de Financiamento e obrigatorio")
    private String tipoFinanciamento;

    /**
     * Numero de prestações a usar no cálculo
     */
    @NotNull(message = "Numero de Prestacoes e obrigatorio")
    @Positive(message = "Numero de Prestacoes tem que ser maior que zero")
    private Integer numPrestacoes;


    /**
     * Valor da viatura
     */
    @NotNull(message = "Valor da Viatura e obrigatorio")
    @Positive(message = "Valor da Viatura tem que ser maior que zero")
    private Float valorViatura;
}
