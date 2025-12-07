package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import utils.CommonMethods;

@RunWith(Cucumber.class)
@CucumberOptions(
        //path to the feature directory
        features = "src/test/resources/features/",
        //connecting feature files to steps with the glue keyword
        glue="steps",
        //dryRun provides missing step definition when true, and executes code when false
        dryRun = true,
        // true tags we can control, and filter test execution
        tags= "@Login",
        plugin={"pretty","html:target/cucumber.html","json:target/cucumber.json"}
)
public class RunnerClass extends CommonMethods {
}
