package hooks;

import WebConnector.webconnector;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;


public class Hooks extends webconnector {

    private String scenDesc;
    @Before
    public void before(Scenario scenario) {

        this.scenDesc = scenario.getName();
        System.out.println("** Started executing scenario : "+scenDesc+" **");
    }

    @After
    public void after(Scenario scenario) {
        closeDriver(scenario);
        System.out.println("** Ended execution of scenario : "+scenDesc+" **");
    }

    @BeforeStep
    public void beforeStep() throws InterruptedException {

    }

}
