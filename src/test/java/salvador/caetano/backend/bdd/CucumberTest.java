package salvador.caetano.backend.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources"
        , plugin = {"pretty", "html:target/cucumber-report.html"}
        , glue = "salvador.caetano.backend.bdd.stepDefinitions")
public class CucumberTest {
}
