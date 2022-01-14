package salvador.caetano.backend.service.prestacao.mensal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import salvador.caetano.backend.dto.PrestacaoMensalRequestDto;
import salvador.caetano.backend.exception.PrestacaoMensalException;
import salvador.caetano.backend.exception.TipoFinanciamentoException;
import salvador.caetano.backend.service.prestacao.mensal.calculador.PrestacaoMensalCalculador;
import salvador.caetano.backend.service.prestacao.mensal.factory.PrestacaoMensalCalculadorFactory;

@Service
public class PrestacaoMensalServiceImpl implements PrestacaoMensalService{

  private PrestacaoMensalCalculadorFactory prestacaoMensalCalculadorFactory;

  @Autowired
  public  PrestacaoMensalServiceImpl(PrestacaoMensalCalculadorFactory prestacaoMensalCalculadorFactory){
      this.prestacaoMensalCalculadorFactory = prestacaoMensalCalculadorFactory;
  }

    /**
     * Calcula o valor da prestação
     * @param prestacaoMensalRequestDto
     * @return Float com valor da prestacão
     * @throws PrestacaoMensalException
     * @throws TipoFinanciamentoException
     */
    public Float calcularPrestacao(PrestacaoMensalRequestDto prestacaoMensalRequestDto) throws NullPointerException, PrestacaoMensalException, TipoFinanciamentoException {
        if(prestacaoMensalRequestDto == null)
            throw new NullPointerException("PrestacaoMensalRequestDto null");

        PrestacaoMensalCalculador prestacaoMensalCalculador  = prestacaoMensalCalculadorFactory.getCalculador(prestacaoMensalRequestDto.getTipoFinanciamento());

        return prestacaoMensalCalculador.calcular(prestacaoMensalRequestDto.getNumPrestacoes(), prestacaoMensalRequestDto.getValorViatura());

    }
}
