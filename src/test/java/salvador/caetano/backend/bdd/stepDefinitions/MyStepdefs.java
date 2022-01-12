package salvador.caetano.backend.bdd.stepDefinitions;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@CucumberContextConfiguration
@SpringBootTest
public class MyStepdefs {


    private RequestSpecification request =  new RequestSpecBuilder().build();
    private Response response;
    private String API_PATH = "/api";

    @Given("^Servico define o (.*)$")
    public void serviçoDefineOURL(String URL) {
        request.baseUri(URL);
    }

    @And("^Realiza um pedido á API para calcular um Financiamento (.*), viatura no valor (.*) e numero de prestacoes (.*)$")
    public void paraUmFinanciamentoFINANCIAMENTO_TIPOViaturaNoValorVALOR_VIATURAENumeroDePrestacoesNUMERO_PRESTACOES(String FINANCIAMENTO_TIPO, String VALOR_VIATURA, String NUMERO_PRESTACOES) {
        String path = API_PATH + "/prestacao-mensal/calcular";

        String tipoFinanciamento = FINANCIAMENTO_TIPO.equals("[null]") ? null : FINANCIAMENTO_TIPO;
        Float valorViatura = VALOR_VIATURA.equals("[null]") ? null : Float.valueOf(VALOR_VIATURA);
        Integer numeroPrestacoes = NUMERO_PRESTACOES.equals("[null]") ? null : Integer.valueOf(NUMERO_PRESTACOES);

        JSONObject requestParams = new JSONObject();
        requestParams.put("tipoFinanciamento", tipoFinanciamento);
        requestParams.put("numPrestacoes", numeroPrestacoes);
        requestParams.put("valorViatura", valorViatura);

       response =  given().header("Content-Type", "application/json")
               .body(requestParams.toJSONString())
               .when().post(path).then().extract().response();

    }



    @Then("^Servico valida o pedido com o (.*) e estado (.*)$")
    public void servicoValidaOPedido(Float VALOR_PRESTACAO, Integer STATUS_CODE) {

        Assert.assertEquals(VALOR_PRESTACAO, response.body().jsonPath().get("valorPrestacao"), 0.001f);
        Assert.assertEquals(STATUS_CODE, Integer.valueOf(response.getStatusCode()));

    }


    @When("^Servico valida o pedido com a menssagem (.*) na variavel (.*) e estado (.*)$")
    public void servicoValidaOPedidoComAMenssagemMENSSAGEMNaVariavelVARIAVELEEstadoSTATUS_CODE(String MENSSAGEM, String VARIAVEL, Integer STATUS_CODE) {
        Assert.assertEquals(MENSSAGEM, response.body().jsonPath().get(VARIAVEL));
        Assert.assertEquals(STATUS_CODE, Integer.valueOf(response.getStatusCode()));
    }



}


