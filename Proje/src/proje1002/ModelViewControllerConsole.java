
import java.util.*;

public class ModelViewControllerConsole {

	public static void main(String[] args) throws Exception {
		// Connect to database
		connectToDatabase();

		// Model View Controller (MVC)
		// Router knows all the controllers
                Map<String, Controller> router = new HashMap<>();
		router.put("MainMenu", new Controller(new MainMenuView(), new NopModel()));
                router.put("Person", new Controller(new PersonView(), new PersonModel()));
                router.put("Application", new Controller(new ApplicationView(), new ApplicationModel()));
                router.put("Response", new Controller (new ResponseView(), new ResponseModel()));

		ViewData viewData = new ViewData("MainMenu", "");		
		do {
			// Model, View, and Controller
			Controller controller = router.get(viewData.functionName);
			ModelData modelData = controller.executeModel(viewData);
			viewData = controller.getView(modelData, viewData.functionName, viewData.operationName);
			
			System.out.println();
			System.out.println("-------------------------------------------------");
			System.out.println();
		}
		while (viewData.functionName != null);
                
		// Disconnect from database
		disconnectFromDatabase();
	}

	
	public static void connectToDatabase() {
		DatabaseUtilities.host = "localhost:1433 ";
		DatabaseUtilities.databaseName = "Proje";
		
		try {
			DatabaseUtilities.getConnection();
		}
		catch(Exception e) {
			System.out.println("Exception occured : " + e);
			return;
		}		
	}
	
	public static void disconnectFromDatabase() {
		try {
			DatabaseUtilities.disconnect();
		}
		catch(Exception e) {
			System.out.println("Error disconnecting from database : " + e);
			return;
		}		
	}
	
}
