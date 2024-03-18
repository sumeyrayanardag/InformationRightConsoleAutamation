//import static ViewInterface.scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


class PersonView implements ViewInterface {

	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		
		switch(operationName) {
		case "LogIn": return selectOperation(modelData, operationName);//select
                case "ShowPerson": return selectOperation(modelData, operationName);
                case "select": return selectCountRowOperation(modelData);
		case "insert": return insertOperation(modelData);	
		case "update": return updateOperation(modelData);	
		case "delete": return deleteOperation(modelData);	
		case "select.gui": return selectGUI(modelData);
		case "insert.gui": return insertGUI(modelData);
		case "update.gui": return updateGUI(modelData);
		case "delete.gui": return deleteGUI(modelData);
		}
		
		return new ViewData("MainMenu", "");
	}
	
        
	ViewData selectOperation(ModelData modelData, String operationName) throws Exception {
		ResultSet resultSet = modelData.resultSet;
                
                
                switch(operationName){
                    case "LogIn": 
                        LogInPage(resultSet);
                        break;
                    case "ShowPerson":
                        showperson(resultSet);
                        break;
                    
                }
                
                resultSet.close();
		
		return new ViewData("MainMenu", "");
	}
	
        ViewData selectCountRowOperation(ModelData modelData) throws Exception {
		ResultSet resultSet = modelData.resultSet;
                int numOfPerson = 0; //number of person
                
                if (resultSet != null) {
                    
			while (resultSet.next()) {
				// Retrieve by column name
				String password = resultSet.getString("Password");
				numOfPerson++;
			}
				
		}                
		resultSet.close();
		return new ViewData("MainMenu", "");
	}
        
	ViewData insertOperation(ModelData modelData) throws Exception {
		System.out.println("Number of inserted rows is " + modelData.recordCount);
		
             
		return new ViewData("MainMenu", "");
	}

	ViewData updateOperation(ModelData modelData) throws Exception {
		System.out.println("Number of updated rows is " + modelData.recordCount);
		
		return new ViewData("MainMenu", "");
	}
	
	ViewData deleteOperation(ModelData modelData) throws Exception {
		System.out.println("Number of deleted rows is " + modelData.recordCount);
		
		return new ViewData("MainMenu", "");
	}	
	
	Map<String, Object> getWhereParameters() throws Exception {
		System.out.println("Filter conditions:");
		Integer PersonID = getInteger("PersonID: ", true);
		
		
		Map<String, Object> whereParameters = new HashMap<>();
		if (PersonID != null) whereParameters.put("PersonID", PersonID);
		
		
		return whereParameters;
	}
	
	ViewData selectGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Department", "select", parameters);
	}

	ViewData insertGUI(ModelData modelData) throws Exception {
            
                ResultSet resultSet = modelData.resultSet;
              
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fieldNames", "FullNameAndTitle, eMail, TelephoneNo, Gender, Address, City, District, FaxNo, PersonTypeID, NationalityID, EducationStatus, Password, UserName, TCNumber");//PersonTypeID, GroupName

		List<Object> rows = new ArrayList<>();
		
		int personTypeID = -1; //-1 for controlling data entered or not
                
                String fullNameAndTitle, eMail, telephoneNo, gender = null, address, city, district, faxNo = null, educationStatus = null, password, userName;
                int nationalityID = -1;
                long tcNumber = -1;
                
			System.out.println("Fields to insert:");
			personTypeID = getInteger("PersonTypeID (write 1 for ActualPerson, 2 for LegalPerson) : ", true);
			
                      //  personID = getInteger("PersonID : ", true);
                        fullNameAndTitle = getString("FullNameAndTitle: ", true);
                        eMail = getString("eMail: ", true);
                        telephoneNo = getString("TelephoneNo: ", true);
                        gender = getString("Gender: ", true);
                        address = getString("Address: ", true);
                        city = getString("City: ", true);
                        district = getString("District: ", true);
                        nationalityID = getInteger("NationalityID (write 1 for Turkey, 2 for Others) : ", true);
                        
                        if(nationalityID == 1){
                            while(true){
                                tcNumber = getInteger("TCNumber: ", true);
                                    if(tcNumber>0){
                                        break;
                                    } 
                                    System.out.println("invalid tc number try again");
                            }
                            
                        }
                        
                        userName = getString("UserName: ", true);
                        int id = 0;
                        
                        if (resultSet != null) {
                            while (resultSet.next()) {
                            // Retrieve by column name
                            String tcNum = resultSet.getString("TCNumber");
                            if (tcNum.equals(tcNumber)) {
                                System.out.println("This tc number is already exist!!!");
                               
                            } 
                            
                            
                            else{
                                id = resultSet.getInt("PersonID");
                                break;
                            }
                        }
                            
                        }
                        
                        faxNo = getString("FaxNo: ", true);
                        educationStatus = getString("EducationStatus: ", true);
                        password = getString("Password: ", true);
                        
			if (personTypeID != -1 && fullNameAndTitle != null && eMail != null && telephoneNo != null && address != null && city != null && district != null && password != null && userName != null && nationalityID != -1) {
				rows.add(new Person( (short)personTypeID, (short)nationalityID, fullNameAndTitle, eMail, telephoneNo, gender, address, city, district, faxNo, educationStatus, password, userName,(long)tcNumber));
			}
                parameters.put("rows", rows);
              
		return new ViewData("Person", "insert", parameters);
	}

	ViewData updateGUI(ModelData modelData) throws Exception {
		System.out.println("Fields to update:");
		String fullNameAndTitle = getString("FullName and Title : ", true);
                String eMail = getString("eMail : ", true);
                String telephoneNo = getString("TelephoneNo : ", true);
                String gender = getString("Gender : ", true);
                String address = getString("Address : ", true);
                String city = getString("City : ", true);
                String district = getString("District : ", true);
                String faxNo = getString("Fax No : ", true);
                String educationStatus = getString("Education Status : ", true);
                String tcNumber = getString("TCNumber: ", true);
		System.out.println();
		
		Map<String, Object> updateParameters = new HashMap<>();
                if (fullNameAndTitle != null) updateParameters.put("FullNameAndTitle", fullNameAndTitle);
                if (eMail != null) updateParameters.put("eMail", eMail);
                if (telephoneNo != null) updateParameters.put("TelephoneNo", telephoneNo);
                if (gender != null) updateParameters.put("Gender", gender);
                if (address != null) updateParameters.put("Address", address);
                if (city != null) updateParameters.put("City", city);
                if (district != null) updateParameters.put("District", district);
                if (faxNo != null) updateParameters.put("FaxNo", faxNo);
		if (educationStatus != null) updateParameters.put("EducationStatus", educationStatus);
                if (tcNumber != null)updateParameters.put("TCNumber", tcNumber);
                
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("updateParameters", updateParameters);
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Person", "update", parameters);
	}

	ViewData deleteGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Person", "delete", parameters);
	}

	@Override
	public String toString() {
		return "Person View";
	}
        
        public int LogInPage(ResultSet resultSet) throws SQLException {
        int succes = 0;
        Scanner input = new Scanner(System.in);
        String UserName;
        System.out.println("Welcome to Log in Page\n**************************"
                + "***\nPlease enter your Username\n");
        UserName = input.next();
        int flag = 0;
        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                String UserName1 = resultSet.getString("UserName");
                if (UserName1.equals(UserName)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                System.out.println("Username Couldnt Found\n ");
            }
            if (flag == 1) {
                System.out.println("Please Enter Your Password\n");
                String passwordTry = input.next();
                String Password = resultSet.getString("Password");
                Map<String, Object> whereParameters = new HashMap<>();
                if (Password.equals(passwordTry)) {
                    whereParameters.put("UserName1", UserName);
                    System.out.println("Logged in successfully!!");
                    succes=1;
                } else {
                    System.out.println("password is incorrect!!!!");
                }
            }
        }
        return succes;
    }
        
        
        
        public void showperson(ResultSet resultSet) throws SQLException{
            
            if (resultSet != null) {
			while (resultSet.next()) {
				// Retrieve by column name
                                
                                 String userName = resultSet.getString("UserName");
                                 String personID = resultSet.getString("PersonID");
                                 String personTypeID = resultSet.getString("PersonTypeID");
                                 String nationalityID = resultSet.getString("NationalityID");
                                 String fullNameAndTitle = resultSet.getString("FullNameAndTitle");
                                 String eMail = resultSet.getString("eMail");
                                 String telephoneNo = resultSet.getString("TelephoneNo");
                                 String gender = resultSet.getString("Gender");
                                 String address = resultSet.getString("Address");
                                 String city = resultSet.getString("City");
                                 String district = resultSet.getString("District");
                                 String faxNo = resultSet.getString("FaxNo");
                                 String educationStatus = resultSet.getString("EducationStatus");
                                 String password = resultSet.getString("Password");
                                 String tcNumber = resultSet.getString("TCNumber");
                                 System.out.println("Person ID : "+ personID);
                                 System.out.println("Person Type ID: " +personTypeID);
                                 System.out.println("Nationality ID: " +nationalityID);
                                 System.out.println("FullName and Title: "+fullNameAndTitle);
                                 System.out.println("e-Mail : "+eMail);
                                 System.out.println("Telephone No: "+telephoneNo);
                                 System.out.println("Gender : "+ gender);
                                 System.out.println("Address : "+address);
                                 System.out.println("City : " +city);
                                 System.out.println("District : "+district);
                                 System.out.println("Fax Number : " +faxNo);
                                 System.out.println("Education Status : "+ educationStatus);
                                 System.out.println("UserName : " +userName);
                                 System.out.println("Password : "+password);
                                 System.out.println("TCNumber: " + tcNumber);
                                 
                                 
                                 
                        }}
            
        }
}


        