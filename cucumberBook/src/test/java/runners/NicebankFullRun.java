package src.test.java.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		glue = {"src.test.java.nicebankStepDefinitions", "src.test.java.hooks" }, 
		features = "cucumberBook/src/test/resources/features",
		monochrome = true,
		dryRun = false,
		snippets = SnippetType.CAMELCASE,
		plugin= {"progress",
				"html:TestResultLogs/html",
				"pretty:TestResultLogs/prettyResult.txt", 
				"testng:TestResultLogs/testngResult.txt"}
		)
public class NicebankFullRun {

}