
import java.util.*;


class MainMenuView implements ViewInterface {
	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

		Integer choice;
		do {
			System.out.println("1. Log in ");
			System.out.println("2. Sign In");
			System.out.println("3. Apply Application");
			System.out.println("4. Response to application");
                        System.out.println("5. Update responded application");
                        System.out.println("6. Number of applications");
                        System.out.println("7. Number of rejected applications and statistics");
			System.out.println("8. Quit");
			System.out.println();

			choice = getInteger("Enter your choice : ", false);
		} 
		while (choice == null || choice < 1 || choice > 8);
		
		
		Map<String, Object> userInput = new HashMap<>();
		userInput.put("mainMenuChoice", choice);
		
		switch (choice.intValue()) {
		case 1: functionName = "Person"; operationName = "LogIn";	break;
		case 2: functionName = "Person"; operationName = "insert.gui";	break;
		case 3: functionName = "Application"; operationName = "insert.gui";	break;
		case 4: functionName = "Response"; operationName = "insert.gui";	break;
                case 5: functionName = "Response"; operationName = "update.gui";    break;
                case 6: functionName = "Application"; operationName = "select";    break;
                case 7: functionName = "Response"; operationName = "select";    break;
		default: return new ViewData(null, null);
		}
		
		return new ViewData(functionName, operationName, new HashMap<>());
	}

	@Override
	public String toString() {
		return "Main Menu View";
	}		
}
