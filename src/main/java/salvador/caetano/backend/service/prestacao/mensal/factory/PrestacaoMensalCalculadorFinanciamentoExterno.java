package salvador.caetano.backend.service.prestacao.mensal.factory;

import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import salvador.caetano.backend.exception.PrestacaoMensalException;

import javax.validation.constraints.NotNull;
import java.util.Objects;


@Component
@Qualifier("PrestacaoMensalCalculadorFinanciamentoExterno")
public class PrestacaoMensalCalculadorFinanciamentoExterno implements PrestacaoMensalCalculador {

    private final Logger logger =  LoggerFactory.getLogger(PrestacaoMensalCalculadorFinanciamentoExterno.class);

    @Value("${financiamento.externo.incremento}")
    private  Float INCREMENTO ;



    /**
     * Calcula a Prestação mensal para um Financiamento Externo
     * @param numeroMensalidades
     * @param valorViatura
     * @return
     * @throws PrestacaoMensalException
     */
    @Override
    public Float calcular(@NotNull Integer numeroMensalidades,@NotNull Float valorViatura) throws PrestacaoMensalException {

        logger.debug("PrestacaoMensalCalculadorFinanciamentoExterno.numeroMensalidades(valorViatura) - valorViatura:" + valorViatura);

        if(Objects.isNull(numeroMensalidades) || Objects.isNull(valorViatura))
            throw new PrestacaoMensalException("Numero de Mensalidades ou Valor Viatura não existentes");

        validarNumeroMensalidades(numeroMensalidades);
        validarvalorViatura(valorViatura);

        return valorViatura * (1 + INCREMENTO) / numeroMensalidades ;
    }

    /**
     * Valida o Numero de Mensalidadaes para Financiamento externo
     * @param numeroMensalidades
     * @throws PrestacaoMensalException
     */
    private void validarNumeroMensalidades(Integer numeroMensalidades) throws PrestacaoMensalException {

        logger.debug("PrestacaoMensalCalculadorFinanciamentoExterno.validarNumeroMensalidades(numeroMensalidades) - numeroMensalidades:" + numeroMensalidades);

        if( numeroMensalidades < 12 || numeroMensalidades > 60)
            throw new PrestacaoMensalException("Financiamento Externo: Numero de mensalidades tem que estar entre 12 e 60 meses");
    }

    /**
     * Valida o Valor da Viatura para Financiamento Externo
     * @param valorViatura
     * @throws PrestacaoMensalException
     */
    private void validarvalorViatura(Float valorViatura) throws PrestacaoMensalException {

        logger.debug("PrestacaoMensalCalculadorFinanciamentoExterno.validarvalorViatura(valorViatura) - valorViatura:" + valorViatura);

        if (valorViatura <= 0)
            throw new PrestacaoMensalException("Financiamento Externo: Valor da viatura tem que ser maior que 0");
    }
}
