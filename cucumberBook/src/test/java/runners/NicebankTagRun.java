package src.test.java.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
//		format = { "pretty", "html:target/cucumber-html-report", },
		glue = {"src.test.java.nicebankStepDefinitions", "src.test.java.hooks" }, 
		features = "cucumberBook/src/test/resources/features",
		monochrome = true,
		dryRun = false,
//		tags = {"@HappyPath"},
//		tags = {"@UnhappyPath"},
		tags = {"@WIP"},
		snippets = SnippetType.CAMELCASE,
		plugin= {"progress", "html:TestResultLogs/html",
				"pretty:TestResultLogs/prettyResult.txt", 
				 "testng:TestResultLogs/testngResult.txt"}
		)
public class NicebankTagRun {

}
