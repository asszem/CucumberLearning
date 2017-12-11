package cucumberTests;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = { "pretty" },
        features = { "src/test/resources/OfflineTests" },
        glue = { "stepDefinitions" },
        monochrome=true,
        dryRun=false,
//        tags = { "@Tag1_CucumberPage"}
//        tags = { "@Tag2_WebDriverPage"}
        tags = { "@WIP"}
//        plugin = { "json:target/Reports/SmokeTest.json", "html:target/Reports/SmokeTestReport.html"}
)
public class WipRun {
	public static void main(String[] args) {
		System.out.println("Cucumber Setup test started...");
	}
}
