import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FacebookDriver {

	static Facebook facebookObj = new Facebook();
	static Scanner scannerObj = new Scanner(System.in); // Object for all user input

	static int getInt(String msg) {
		boolean isInt = false;
		int tmpInt = 0;
		while (!isInt) {
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

	static int getInt(String msg, int lower, int upper) {
		boolean isInt = false;
		int tmpInt = 0;
		while (!isInt) {
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
			} finally {
				scannerObj.nextLine(); //consume newline
			}

		}
		return tmpInt;

	}

	static void displayMenu() {
		// Display Menu

		System.out.println("\nMenu");
		System.out.println("1. List Users");
		System.out.println("2. Add User");
		System.out.println("3. Delete User");
		System.out.println("4. Get Password Hint");
		System.out.println("5. Quit");

	}

	static void printUsers(ArrayList<FacebookUser> users) {
		System.out.println("\nUsers:");
		for (FacebookUser i : users) {
			System.out.println(i);
		}
	}

	static String getStrInput(String msg) {
		
		System.out.println(msg);
		
		return scannerObj.nextLine();
	

	}

	static void addUser() {
		String userName = getStrInput("Enter Username to add");

		if (facebookObj.searchUser(userName) != null) {
			System.out.println("Error: User already exists");
			return;
		}

		String password = getStrInput("Enter password for new user");
		String passwordHint = getStrInput("Enter password hint for new user");

		facebookObj.addUser(userName, password, passwordHint);

	}
	
	

	public static void main(String[] args) throws CloneNotSupportedException {
		int usrChoice = 0;

		do {
			displayMenu();
			usrChoice = getInt("Please select a choice between 1-5", 1, 5);
			switch (usrChoice) {
			case 1:
				printUsers(facebookObj.listUsers());
				break;
			case 2: {
				try {
					addUser();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 3: {
				try {
					facebookObj.deleteUser(getStrInput("Enter username to delete"));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 4: {
				try {
				System.out.println(facebookObj.getPasswordHint(getStrInput("Enter username")));
				}
				catch (RuntimeException ex) {
					System.out.println(ex.getMessage());
				}
				break;
				
			}
			}
		} while (usrChoice != 5);

		scannerObj.close();
		System.out.println("Buh bye");
	}

}
