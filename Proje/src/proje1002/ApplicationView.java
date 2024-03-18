
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


class ApplicationView implements ViewInterface {

	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		
		switch(operationName) {
                case "select": return selectCountRowOperation(modelData);    
		case "LogIn": return selectOperation(modelData);
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
	
	ViewData selectOperation(ModelData modelData) throws Exception {
		ResultSet resultSet = modelData.resultSet;
                if (resultSet == null) {
                System.out.println("Result set null değil");}
		Scanner input= new Scanner(System.in);
                String UserName;
                System.out.print("Please enter your Username: ");
                UserName= input.next();
                int flag=0;
		if (resultSet != null) {
			while (resultSet.next()) {
				// Retrieve by column name
                                 String UserName1 = resultSet.getString("UserName");
                                if (UserName1.equals(UserName)){
                                    flag=1 ;
                                    break;
                                }
                               
			}
                        if(flag==0)System.out.println("Username Couldnt Found\n ");
                        if(flag==1){
                            System.out.print("Please Enter Your Password: ");
                            String passwordTry=input.next();
                            String Password = resultSet.getString("Password");
                            Map<String, Object> whereParameters = new HashMap<>();
		            
                            if (Password.equals(passwordTry)) {
                                whereParameters.put("UserName1", UserName);
                                System.out.println("Welcome " + UserName);
                            }
                            else{
                                System.out.println("Password is not correct!!!");
                            }
                        }
				
		}
		resultSet.close();
		return new ViewData("MainMenu", "");
	}
        
        
        ViewData selectCountRowOperation(ModelData modelData) throws Exception {
		ResultSet resultSet = modelData.resultSet;
                
                int numOfApp = 0; //number of applications
                
                if (resultSet != null) {
                    
			while (resultSet.next()) {
				// Retrieve by column name
				int appID = resultSet.getInt("AppID");
				String details = resultSet.getString("Details");
				numOfApp++;
			}
				
		}                
		resultSet.close();
		System.out.println("Number of Applications: "+ numOfApp);
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
		parameters.put("fieldNames", "ToWhereID, PersonID, HowToResponseID, Details");

		List<Object> rows = new ArrayList<>();
		
		String details;
                int toWhereID = -1, personID = -1, howToResponseID = -1;
		//do
		//{
			System.out.println("Fields to insert:");
                        personID = getInteger("PersonID: ", true);
			details = getString("Details : ", true);
			toWhereID = getInteger("\n******Institutions*******\n"
                                + "1.CIMER\n"
                                + "2.TOB\n"
                                + "3.YOK\n"
                                + "4.AYBU\n"
                                + "5.Muhendisler odası\n"
                                + "6.Cevre ve sehircili bakanlığı\n"
                                + "7.Barolar Bırlığı\n"
                                + "8.Konya Baro Baskanlıgı\n"
                                + "9.Selcuklu Barosu\n"
                                + "10.Iç İşleri Bakanlığı\n "
                                + "\nPlease choose to where ID: ", true);
                        
                        
                        howToResponseID = getInteger("******Response Types*****\n"
                                + "1.eMail\n"
                                + "2.Cargo\n"
                                + "3.Fax\n"
                                + "Please choose how to response: ", true);
			System.out.println();
				
			if (details != null && toWhereID != -1 && personID != -1 && howToResponseID != -1) {
                                rows.add(new Application( (short)toWhereID, (short)personID, (short)howToResponseID, details));
                        }    
		
		parameters.put("rows", rows);
		
		return new ViewData("Application", "insert", parameters);
	}

	ViewData updateGUI(ModelData modelData) throws Exception {
		System.out.println("Fields to update:");
		String name = getString("Name : ", true);
		String groupName = getString("Group Name : ", true);
		System.out.println();
		
		Map<String, Object> updateParameters = new HashMap<>();
		if (name != null) updateParameters.put("Name", name);
		if (groupName != null) updateParameters.put("GroupName", groupName);
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("updateParameters", updateParameters);
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Department", "update", parameters);
	}

	ViewData deleteGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Department", "delete", parameters);
	}

	@Override
	public String toString() {
		return "Application View";
	}		
}
