package src.test.java.support;

public class DITest {

	private String diString="Dependency Injection";

	//Constructor for DITest class that will be instantiated by the DI handling container
	//This class was never instantiated with new in the code anywhere
	public DITest() {
		System.out.println("DI Test class instantiated");
	}
	
	public void printMsg() {
		System.out.println("Message: " + diString);
	}

}
