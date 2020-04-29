package Tables;

import java.util.*;

public class Person {
	final public String [] Features = {
			"Aadhar_Number",
			"First_Name",
			"Last_Name",
			"Age",
			"Gender",
			"Address_Line_1",
			"City",
			"State",
			"PinCode"
	};
    final public HashSet<String> Primary_Key ;
	public Person() {
		Primary_Key = new HashSet<String>();
		Primary_Key.add("Aadhar_Number");
	}
}