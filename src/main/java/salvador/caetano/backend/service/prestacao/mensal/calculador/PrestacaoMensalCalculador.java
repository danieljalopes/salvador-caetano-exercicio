package salvador.caetano.backend.service.prestacao.mensal.calculador;

import salvador.caetano.backend.exception.PrestacaoMensalException;

public interface PrestacaoMensalCalculador {

    Float calcular(Integer numeroMensalidades, Float valorViatura) throws PrestacaoMensalException;
}
