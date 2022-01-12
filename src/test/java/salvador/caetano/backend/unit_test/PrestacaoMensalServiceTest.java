package salvador.caetano.backend.unit_test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import salvador.caetano.backend.dto.PrestacaoMensalRequestDto;
import salvador.caetano.backend.exception.PrestacaoMensalException;
import salvador.caetano.backend.exception.TipoFinanciamentoException;
import salvador.caetano.backend.service.prestacao.mensal.PrestacaoMensalService;
import salvador.caetano.backend.service.prestacao.mensal.factory.PrestacaoMensalCalculador;
import salvador.caetano.backend.service.prestacao.mensal.factory.PrestacaoMensalCalculadorFactory;
import salvador.caetano.backend.service.prestacao.mensal.factory.PrestacaoMensalCalculadorFinanciamentoExterno;
import salvador.caetano.backend.service.prestacao.mensal.factory.PrestacaoMensalCalculadorFinanciamentoInterno;

@SpringBootTest
@ActiveProfiles(profiles = "test")
public class PrestacaoMensalServiceTest {


    @Autowired
    private PrestacaoMensalService prestacaoMensalService;

    @MockBean
    private PrestacaoMensalCalculadorFactory prestacaoMensalCalculadorFactory;




    /**
     * Tests if calcular() will throw Exceptions
     */
    @Test
    public void exceptionsThrown() throws TipoFinanciamentoException, PrestacaoMensalException {
        //Argumento é null
        Exception exception = Assert.assertThrows(NullPointerException.class, () -> {
            prestacaoMensalService.calcularPrestacao(null);
        });

        String expectedMessage = "PrestacaoMensalRequestDto null";
        String actualMessage = exception.getMessage();
        Assert.assertEquals(expectedMessage, actualMessage);

        //Inválido TipoFinanciamento
        String tipoFinanciamento = "xxxx";
        PrestacaoMensalRequestDto request1 = PrestacaoMensalRequestDto.builder().numPrestacoes(12).tipoFinanciamento(tipoFinanciamento).valorViatura(10000.00F).build();

        Mockito.when(prestacaoMensalCalculadorFactory.getCalculador(request1.getTipoFinanciamento())).thenThrow(new TipoFinanciamentoException("Financiamento: " + tipoFinanciamento + " inválido"));
        exception = Assert.assertThrows(TipoFinanciamentoException.class, () -> {
            prestacaoMensalService.calcularPrestacao(request1);
        });

        expectedMessage = "Financiamento: " + tipoFinanciamento + " inválido";
        actualMessage = exception.getMessage();

        Assert.assertEquals(expectedMessage, actualMessage);


    }



}
