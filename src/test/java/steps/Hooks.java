package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;
import java.io.IOException;

public class Hooks extends CommonMethods {
    //pre-condition for all feature file
    @Before
    public void  start() throws IOException {
        openBrowserAndLaunchApplication();
    }

    // post-condition for all feature file
    @After
    public void end(){
    //adding screenshots to the reports at the end of the execution, before closing the browser
        closeBrowser();
    }
}
