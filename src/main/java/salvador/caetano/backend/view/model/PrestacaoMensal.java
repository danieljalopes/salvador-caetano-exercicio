package salvador.caetano.backend.view.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Builder
@Data
public class PrestacaoMensal {

    @Builder.Default
    private List<String> tiposFinanciamento = new LinkedList<>(Arrays.asList("INTERNO", "EXTERNO"));

    /**
     * Tipo de Financiamento
     */
    @Pattern(regexp = "[a-zA-Z]+", message="Somente caracteres")
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
