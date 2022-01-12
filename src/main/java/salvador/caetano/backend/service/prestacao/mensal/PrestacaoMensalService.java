package salvador.caetano.backend.service.prestacao.mensal;

import salvador.caetano.backend.dto.PrestacaoMensalRequestDto;
import salvador.caetano.backend.exception.PrestacaoMensalException;
import salvador.caetano.backend.exception.TipoFinanciamentoException;

public interface PrestacaoMensalService {
    Float calcularPrestacao(PrestacaoMensalRequestDto prestacaoMensalRequestDto) throws PrestacaoMensalException, TipoFinanciamentoException;
}
