package helpers;

import java.io.IOException;

public class KillAllDrivers {

	public static void killAllDrivers() {
		String killChrome = "taskkill /im chromedriver /F";
		String killFirefox = "taskkill /im geckodriverX64.exe /F";
		try {
			Process p = Runtime.getRuntime().exec(killChrome);
			p = Runtime.getRuntime().exec(killFirefox);
			System.out.println("Processes [chromedriver, geckodriverX64] killed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void killFirefoxBrowsers(){
		String killFirefox = "taskkill /im firefox.exe /F";
		try {
			Process p = Runtime.getRuntime().exec(killFirefox);
			System.out.println("Firefox browsers killed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
