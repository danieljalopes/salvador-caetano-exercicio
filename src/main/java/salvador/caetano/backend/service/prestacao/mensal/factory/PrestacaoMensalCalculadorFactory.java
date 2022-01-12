package salvador.caetano.backend.service.prestacao.mensal.factory;

import salvador.caetano.backend.exception.TipoFinanciamentoException;

public interface PrestacaoMensalCalculadorFactory {

    PrestacaoMensalCalculador getCalculador(String tipoFinanciamento) throws TipoFinanciamentoException;
}
