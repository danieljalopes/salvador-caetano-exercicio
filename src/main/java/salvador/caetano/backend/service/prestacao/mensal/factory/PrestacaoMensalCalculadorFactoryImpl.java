package salvador.caetano.backend.service.prestacao.mensal.factory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import salvador.caetano.backend.exception.TipoFinanciamentoException;
import salvador.caetano.backend.service.prestacao.mensal.calculador.PrestacaoMensalCalculador;

import javax.validation.constraints.NotNull;

@Component
public class PrestacaoMensalCalculadorFactoryImpl implements PrestacaoMensalCalculadorFactory {

    @Autowired
    @Qualifier("PrestacaoMensalCalculadorFinanciamentoExterno")
    private PrestacaoMensalCalculador prestacaoMensalCalculadorFinanciamentoExterno;

    @Autowired
    @Qualifier("PrestacaoMensalCalculadorFinanciamentoInterno")
    private PrestacaoMensalCalculador prestacaoMensalCalculadorFinanciamentoInterno;


    /**
     * Cria Calaculador de Preatacao consoante o tipo de financiamento
     * @param o tipo de financiamento usado
     * @return O objecto que vai calcular o valor da prestação
     * @throws TipoFinanciamentoException
     */
    public PrestacaoMensalCalculador getCalculador(@NotNull String tipoFinanciamento) throws TipoFinanciamentoException {

        PrestacaoMensalCalculador prestacaoMensalCalculador = null;

        switch (tipoFinanciamento){
            case "INTERNO":
                prestacaoMensalCalculador = prestacaoMensalCalculadorFinanciamentoInterno;
                break;
            case "EXTERNO":
                prestacaoMensalCalculador = prestacaoMensalCalculadorFinanciamentoExterno;
                break;
            default:
                throw new TipoFinanciamentoException("Financiamento: " + tipoFinanciamento + " inválido");
        }

        return prestacaoMensalCalculador;
    }
}
