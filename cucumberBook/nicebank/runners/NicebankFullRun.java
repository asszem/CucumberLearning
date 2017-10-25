package nicebank.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin= {"pretty", "html:out"},
		glue = {"nicebank.stepDefinitionsForNiceBank", "nicebank.hooks" }, 
		features = "cucumberBook/nicebank/resources/features",
		monochrome = true,
		dryRun = false,
		snippets = SnippetType.CAMELCASE
		)
public class NicebankFullRun {

}