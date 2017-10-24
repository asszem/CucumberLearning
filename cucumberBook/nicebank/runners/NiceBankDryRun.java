package nicebank.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		format = { "pretty", "html:target/cucumber-html-report", },
		glue = {"nicebank.stepDefinitionsForNiceBank", "nicebank.hooks" }, 
		features = "cucumberBook/nicebank/resources/features",
//		features = "C:/at/CucumberLearning/cucumberBook/nicebank/resources/features",
		monochrome = true,
		dryRun = true,
		snippets = SnippetType.CAMELCASE,
		tags = {"@SingleAccount"}
		)
public class NiceBankDryRun {
}
