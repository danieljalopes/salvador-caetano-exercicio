package salvador.caetano.backend.service.prestacao.mensal.factory;

import salvador.caetano.backend.exception.TipoFinanciamentoException;
import salvador.caetano.backend.service.prestacao.mensal.calculador.PrestacaoMensalCalculador;

public interface PrestacaoMensalCalculadorFactory {

    PrestacaoMensalCalculador getCalculador(String tipoFinanciamento) throws TipoFinanciamentoException;
}
