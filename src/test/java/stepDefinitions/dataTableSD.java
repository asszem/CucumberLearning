package stepDefinitions;

import java.util.ArrayList;
import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class dataTableSD {

	private List<List<String>> modifiableList; // Create a list that consists of lists<String>

	@Given("^customers are:$")
	public void customers_are(DataTable inputTable) throws Throwable {
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)

		//DataTable is unmodifiable, so it's content needs to be changed to an ArrayList

		modifiableList = new ArrayList<List<String>>(); // Create an array list that will hold List<String>
		for (List<String> currentRow : inputTable.raw()) { // Retrieve each row from table as List<String>
			System.out.println("currentRow=" + currentRow.toString());
			System.out.println("arrayList size=" + modifiableList.size());
			modifiableList.add(new ArrayList<String>(currentRow)); // Add the current row to the ArrayList
		}

		// Result:
		// currentRow=[country, customer_number, customer_name]
		// currentRow=[897, 123456, Joe]
		// currentRow=[123, 654321, Bill]
		// currentRow=[760, 000000, Tibor]

	}

	@When("^Tibor changes his name to Klara$")
	public void tibor_changes_his_name_to_Kl_ra() throws Throwable {
		//Perform the actual modification of the ArrayList

		System.out.println("Before: "+ modifiableList.get(3).get(2));
//		modifiableList.get(3).get(2).replace("Tibor", "Klara");
		modifiableList.get(3).set(2, "Klara");
		System.out.println("After: "+modifiableList.get(3).get(2));
	}

	@Then("^the table should look like this$")
	public void the_table_should_look_like_this(DataTable updatedTable) throws Throwable {

		// Compare arrayList to originalTable
		updatedTable.diff(modifiableList);
	}
}
