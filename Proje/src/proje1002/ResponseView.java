import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


class ResponseView implements ViewInterface {

	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		
		switch(operationName) {
		case "LogIn": return selectOperation(modelData, operationName);
                case "ShowPerson": return selectOperation(modelData, operationName);
                case "select": return selectCountRejectedOperation(modelData);   
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
                       // System.out.println("CASE LOGIN");
                       // LogInPage(resultSet);
                        break;
                    case "ShowPerson":
                     //  System.out.println("here we are going to show person table to employee");
                        showperson(resultSet);
                        break;
                }
                
                resultSet.close();
		return new ViewData("MainMenu", "");
	}
        
        ViewData selectCountRejectedOperation(ModelData modelData) throws Exception {
		ResultSet resultSet = modelData.resultSet;
                int numOfRejApp = 0; //number of rejected applications
                int numOfNotRejApp = 0; //number of rejected applications
                
                if (resultSet != null) {
                    
			while (resultSet.next()) {
				// Retrieve by column name
				int rejectedOrNot = resultSet.getInt("RejectedOrNot");
				
                                if(rejectedOrNot == 1)
                                    numOfRejApp++;
                                else if(rejectedOrNot == 2)
                                    numOfNotRejApp++;
                                
			}
				
		}                
		resultSet.close();
		System.out.println("Number of Rejected Applications: "+ numOfRejApp);
                System.out.println("Number of NOT Rejected Applications: "+ numOfNotRejApp);
                
                System.out.println((numOfRejApp*100)/(numOfRejApp+numOfNotRejApp)+"% rejected applications, " + (numOfNotRejApp*100)/(numOfRejApp+numOfNotRejApp)+"% not rejected applications. " );
                
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
		Integer appID = getInteger("AppID: ", true);
		
		Map<String, Object> whereParameters = new HashMap<>();
		if (appID != null) whereParameters.put("AppID", appID);
		
		return whereParameters;
	}
	
	ViewData selectGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Department", "select", parameters);
	}

        ViewData insertGUI(ModelData modelData) throws Exception {
               
                
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fieldNames", "AppID, StatusID, RejectedOrNot, ReasonForRejectionID, WhereToTransferID, ResponseDetail");//PersonTypeID, GroupName

		List<Object> rows = new ArrayList<>();
		
                
                int appID = -1, statusID = -1, rejectedOrNot= -1 ,reasonForRejectionID = -1, whereToTransferID = -1;
                String responseDetail = null;
                
		//do
		//{
			System.out.println("Fields to insert:");
			appID = getInteger("AppID: ", true);
                        statusID = getInteger(""
                                + "\n********Status Informations***********\n"
                                + "1-Accepted\n"
                                + "2-Rejected\n"
                                + "3-Transfered\n"
                                + "4-Partial Answer\n"
                                + "5-Received\n"
                                + "6-İn Action\n"
                                + "7-Rejected But Later Accepted\n"
                                + "Please choose StatusID : ", true);
                        rejectedOrNot = getInteger("RejectedOrNot(1 for rejected, 2 for not rejectted: ", true);
                        if(rejectedOrNot==1){
                            reasonForRejectionID = getInteger(""
                                + "\n*******Reasons For Rejection*******\n"
                                + "1.Classified information or documents that are state secrets ReasonForRejectionID\n"
                                + "2.Information or documents relating to the economic interests of the country\n"
                                + "3.Information or documents regarding intelligence\n"
                                + "4.Information or documents related to the administrative investigation\n"
                                + "5.Information or documents regarding judicial investigation and prosecution\n"
                                + "6.Privacy of private life\n"
                                + "7.Privacy of communication\n"
                                + "8.Trade secret\n"
                                + "9. Ideas and works of art\n"
                                + "10.In-house regulations\n"
                                + "11.Internal opinions, information notes and recommendations\n"
                                + "12. Requests for advice and opinion\n"
                                + "13.Application cost is not paid\n\n"
                                + "Choose reason for rejection : ", true);
                        }
                        else reasonForRejectionID=0;
                        whereToTransferID = getInteger("\n******Institutions*******\n"
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
                                + "\nPlease choose where to Transfer ID: ", true);
                        responseDetail = getString("ResponseDetail: ", true);
                        
			if (appID != -1 && statusID != -1 && rejectedOrNot != -1 && reasonForRejectionID != -1 && whereToTransferID != -1 && responseDetail != null) {
				rows.add(new Response((short)appID, (short)statusID, (short)rejectedOrNot, (short)reasonForRejectionID, (short)whereToTransferID/*, (Date)responseDate*/, responseDetail));
			}
		//}
                parameters.put("rows", rows);

               
                
                return new ViewData("Response", "insert", parameters);
	}

        
        ViewData updateGUI(ModelData modelData) throws Exception {
		System.out.println("Fields to update:");
		String appID = getString("AppID: ", true);
                String statusID = getString(" Status Informations***********\n1-Accepted\n2-Rejected\n3-Transfered\n4-Partial Answer\n5-Received\n6-İn Action\n7-Rejected But Later Accepted\nPlease choose StatusID : ", true);
               // String rejectedOrNot = getString("RejectedOrNot (If YES type 1, if NO type 2) : ", true);
                
                String reasonForRejectionID;
                String rejectedOrNot;
                rejectedOrNot = getString("RejectedOrNot(1 for rejected, 2 for not rejectted: ", true);
                        if(Integer.parseInt(rejectedOrNot)==1){
                            reasonForRejectionID = getString(""
                                + "\n*******Reasons For Rejection*******\n"
                                + "1.Classified information or documents that are state secrets ReasonForRejectionID\n"
                                + "2.Information or documents relating to the economic interests of the country\n"
                                + "3.Information or documents regarding intelligence\n"
                                + "4.Information or documents related to the administrative investigation\n"
                                + "5.Information or documents regarding judicial investigation and prosecution\n"
                                + "6.Privacy of private life\n"
                                + "7.Privacy of communication\n"
                                + "8.Trade secret\n"
                                + "9. Ideas and works of art\n"
                                + "10.In-house regulations\n"
                                + "11.Internal opinions, information notes and recommendations\n"
                                + "12. Requests for advice and opinion\n"
                                + "13.Application cost is not paid\n\n"
                                + "Choose reason for rejection : ", true);
                        }
                        else reasonForRejectionID="0";
                
                
           
                String whereToTransferID = getString("1.CIMER\n2.TOB\n3.YOK\n4.AYBU\n5.Muhendisler odası\n6.Cevre ve sehircilik bakanlığı\n7.Barolar Bırlığı\n8.Konya Baro Baskanlıgı\n9.Selcuklu Barosu\n10.Iç İşleri Bakanlığı\n" +
"                        WhereToTransferID: ", true);
                String responseDetail = getString("ResponseDetail: ", true);
		System.out.println();
		
		Map<String, Object> updateParameters = new HashMap<>();
		if (appID != null) updateParameters.put("AppID", Integer.parseInt(appID));
                if (statusID != null) updateParameters.put("StatusID", Integer.parseInt(statusID));
                if (rejectedOrNot != null) updateParameters.put("RejectedOrNot", Integer.parseInt(rejectedOrNot));
                if (reasonForRejectionID != null) updateParameters.put("ReasonForRejectionID", Integer.parseInt(reasonForRejectionID));
                if (whereToTransferID != null) updateParameters.put("WhereToTransferID", Integer.parseInt(whereToTransferID));
		if (responseDetail != null) updateParameters.put("ResponseDetail", responseDetail);
                
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("updateParameters", updateParameters);
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Response", "update", parameters);
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


        