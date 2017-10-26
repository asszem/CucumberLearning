package src.test.java.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helpers.KillAllDrivers;

public class BasicHooks {
	@Before
	public void beforeCallingScenario(Scenario scenario) {
		System.out.println("***********Scenario " + scenario.getName() + " started***********");
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("Shutdown hook called");
				KillAllDrivers.killAllDrivers();
			}

		});
	}

	@Before(order = 100)
	public void hook100() {
		System.out.println("Hook 100 called");
	}

	@Before(order = 500)
	public void hook500() {
		System.out.println("Hook 500 called");
	}

	@After
	public void afterRunningScenario(Scenario scenario) {
		System.out.println("===========Scenario " + scenario.getName() + " completed==========");
		System.out.println(scenario.getStatus());
	}

}
