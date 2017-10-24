package cucumberTests;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = { "pretty" },
        features = { "src/test/resources/WebUITests" },
        glue = { "stepDefinitions" },
        monochrome=true,
        dryRun=false,
//        tags = { "@Tag1_CucumberPage"}
        tags = { "@EntireFeature_tag"}
//        plugin = { "json:target/Reports/SmokeTest.json", "html:target/Reports/SmokeTestReport.html"}
)
public class CucumberSetupRun {
	public static void main(String[] args) {
		System.out.println("Cucumber Setup test started...");
	}
}
