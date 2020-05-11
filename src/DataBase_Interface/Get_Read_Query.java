package DataBase_Interface;

import org.json.JSONObject;

public class Get_Read_Query {
	public String[] values;
	public String[] features;

	public String get(JSONObject obj, String type) {
		String query = "";
		if (type.equals("Total_Active_Cases")) {
			query = " SELECT COUNT(Aadhar_Number) as Total_Active_Cases FROM PATIENT WHERE Date_Discharge IS  NULL ;";
			features = new String[1];
			features[0] = "Total_Active_Cases";
		} else if (type.equals("Total_Recovered_Cases")) {
			query = " SELECT COUNT(Aadhar_Number) as Total_Recovered_Cases FROM PATIENT WHERE Date_Discharge IS NOT NULL ; ";
			features = new String[1];
			features[0] = "Total_Recovered_Cases";
		} else if (type.equals("Total_Cases")) {
			query = " SELECT COUNT(Aadhar_Number) as Total_Cases FROM PATIENT ; ";
			features = new String[1];
			features[0] = "Total_Cases";
		}

		else if (type.equals("Details_Nearby_Hospital")) {

			query ="SELECT  Hospital.Name as Name, City, State,Count(Patient_ID)  as Patient, Count(Distinct Doctor_ID)  as Doctor from Hospital INNER JOIN (Select Doctor.Hospital_ID , Doctor.Doctor_ID , Patient.Patient_ID from Doctor INNER JOIN Patient on Doctor.Doctor_ID = Patient.Doctor_ID)as der On der.Hospital_ID = Hospital.Hospital_id  where Hospital.city = ? and Hospital.state = ? Group BY Hospital.Hospital_ID ";
			values = new String[2];
			values[0] = (String) obj.get("city") + "";
			values[1] = (String) obj.get("state") + "";
			features = new String[5];
		      features[0] = "Name";
		      features[1] = "City";
		      features[2]="State";
		      features[3] = "Patient";
		      features[4] = "Doctor";
		}

		else if (type.equals("Nearby_Active_Cases")) {
			query =" SELECT COUNT(Patient_ID)as Nearby_Active_Cases FROM PATIENT Inner Join  Person  On person.Aadhar_Number = Patient.Aadhar_Number where  city = ? and state = ? and Date_Discharge IS NULL  ; ";
			values = new String[2];
			values[0] = (String) obj.get("city") + "";
			values[1] = (String) obj.get("state") + "";
			
			features = new String[1];

			features[0] = "Nearby_Active_Cases";

		} else if (type.equals("Nearby_Recovered_Cases")) {
			query =" SELECT COUNT(Patient_ID)as Nearby_Recovered_Cases FROM PATIENT Inner Join  Person  On person.Aadhar_Number = Patient.Aadhar_Number where  city = ? and state = ? and Date_Discharge IS NOT NULL  ; ";
			values = new String[2];
			values[0] = (String) obj.get("city") + "";
			values[1] = (String) obj.get("state") + "";
			features = new String[1];
			features[0] = "Nearby_Recovered_Cases";
		} else if (type.equals("Nearby_Cases")) {
			query = " SELECT COUNT(Patient_ID)as Nearby_Cases FROM PATIENT Inner Join  Person  On person.Aadhar_Number = Patient.Aadhar_Number where  city = ? and state = ? ; " ; 
					
			values = new String[2];
			values[0] = (String) obj.get("city") + "";
			values[1] = (String) obj.get("state") + "";
			
			features = new String[1];
			features[0] = "Nearby_Cases";
			
		}
		return query;
	}
}
