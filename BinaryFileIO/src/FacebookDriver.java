import java.util.InputMismatchException;
import java.util.Scanner;

public class FacebookDriver {

	Facebook facebookObj = new Facebook();
	static Scanner scannerObj = new Scanner(System.in); // Object for all user input

	static int getInt(String msg) {
		boolean isInt = false;
		int tmpInt = 0;
		while(!isInt) {
			try {
				System.out.println(msg);
				tmpInt = scannerObj.nextInt();
				isInt = true;
			} catch (InputMismatchException ex) {
				System.out.println("Please Enter integers Only");
				scannerObj.next();
			}
			
		
		}
		return tmpInt;
		
		
	}
	static int getInt(String msg,int lower, int upper) {
		boolean isInt = false;
		int tmpInt = 0;
		while(!isInt) {
			try {
				System.out.println(msg);
				tmpInt = scannerObj.nextInt();
				if (tmpInt >= lower && tmpInt <= upper) {
					isInt = true;
				
				} else {
					System.out.println("Please enter a number between " + lower + "-" + upper);
				}
			} catch (InputMismatchException ex) {
				System.out.println("Please Enter integers Only");
				scannerObj.next();
			}
			
		
		}
		return tmpInt;
		
		
	}


	static int displayMenu() {
		// Display Menu
		int userInput; // stores user choice
		
		System.out.println("\nMenu");
		System.out.println("1. List Users");
		System.out.println("2. Add User");
		System.out.println("3. Delete User");
		System.out.println("4. Get Password Hint");
		System.out.println("5. Quit");
		
		userInput = getInt("Please select a choice between 1-5",1,5);

		return userInput;
	}

	public static void main(String[] args) {
		int choice = displayMenu();
		System.out.println("CHoice: " + choice);
		
		scannerObj.close();
		System.out.println("Buh bye");
	}

}
