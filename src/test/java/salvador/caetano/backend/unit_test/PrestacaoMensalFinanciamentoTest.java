package salvador.caetano.backend.unit_test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import salvador.caetano.backend.exception.PrestacaoMensalException;
import salvador.caetano.backend.service.prestacao.mensal.factory.PrestacaoMensalCalculador;

@SpringBootTest
@ActiveProfiles(profiles = "test")
public class PrestacaoMensalFinanciamentoTest {

    @Autowired
    @Qualifier("PrestacaoMensalCalculadorFinanciamentoExterno")
    private PrestacaoMensalCalculador calculadorFinanciamentoExterno;

    @Autowired
    @Qualifier("PrestacaoMensalCalculadorFinanciamentoInterno")
    private PrestacaoMensalCalculador calculadorFinanciamentoInterno;


    @Test
    public void financiamentoInterno() throws PrestacaoMensalException {
       Float result =  calculadorFinanciamentoInterno.calcular(2, 10000F);
        Assert.assertEquals(5200F, result, 0.001);

         result =  calculadorFinanciamentoInterno.calcular(48, 10000F);
        Assert.assertEquals(216.6667, result, 0.001);
    }

    @Test
    public void financiamentoExterno() throws PrestacaoMensalException {
        Float result =  calculadorFinanciamentoExterno.calcular(12, 10000F);
        Assert.assertEquals(1375, result, 0.001);

        result =  calculadorFinanciamentoExterno.calcular(60, 10000F);
        Assert.assertEquals(275F, result, 0.001);
    }
}
