package salvador.caetano.backend.service.prestacao.mensal.calculador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import salvador.caetano.backend.exception.PrestacaoMensalException;

import java.util.Objects;

;

@Component
@Qualifier("PrestacaoMensalCalculadorFinanciamentoInterno")
public class PrestacaoMensalCalculadorFinanciamentoInterno implements PrestacaoMensalCalculador {

    private final Logger logger =  LoggerFactory.getLogger(PrestacaoMensalCalculadorFinanciamentoInterno.class);

    @Value("${financiamento.interno.incremento}")
    private Float INCREMENTO;


    /**
     * Calcula a Prestação mensal para um Financiamento Interno
     *
     * @param numeroMensalidades
     * @param valorViatura
     * @return
     * @throws PrestacaoMensalException
     */
    @Override
    public Float calcular(Integer numeroMensalidades, Float valorViatura) throws PrestacaoMensalException {

        logger.debug("PrestacaoMensalCalculadorFinanciamentoInterno.numeroMensalidades(valorViatura) - valorViatura:" + valorViatura);

        if (Objects.isNull(numeroMensalidades) || Objects.isNull(valorViatura))
            throw new PrestacaoMensalException("Numero de Mensalidades ou Valor Viatura não existentes");


        validarNumeroMensalidades(numeroMensalidades);
        validarvalorViatura(valorViatura);

        return valorViatura * (1 + INCREMENTO) / numeroMensalidades;
    }


    /**
     * Valida o Numero de Mensalidadaes para Financiamento externo
     *
     * @param numeroMensalidades
     * @throws PrestacaoMensalException
     */
    private void validarNumeroMensalidades(Integer numeroMensalidades) throws PrestacaoMensalException {

        logger.debug("PrestacaoMensalCalculadorFinanciamentoInterno.validarNumeroMensalidades(numeroMensalidades) - numeroMensalidades:" + numeroMensalidades);

        if (numeroMensalidades > 48)
            throw new PrestacaoMensalException("Financiamento Interno: Numero de mensalidades não pode ser superior a 48");

    }

    /**
     * Valida o Valor da Viatura para Financiamento Externo
     *
     * @param valorViatura
     * @throws PrestacaoMensalException
     */
    private void validarvalorViatura(Float valorViatura) throws PrestacaoMensalException {
        logger.debug("PrestacaoMensalCalculadorFinanciamentoInterno.validarvalorViatura(valorViatura) - valorViatura:" + valorViatura);
        if (valorViatura <= 0)
            throw new PrestacaoMensalException("Financiamento Interno: Valor da viatura tem que ser maior que 0");
    }
}
