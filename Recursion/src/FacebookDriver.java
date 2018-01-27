import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FacebookDriver {

	static Facebook facebookObj = new Facebook();
	static Scanner scannerObj = new Scanner(System.in); // Object for all user input

	// Function to retrieve integer input. Peforms input validation to only allow
	// int
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

	// Function to retrieve integer input. Peforms input validation to only allow
	// int. Will also check against range
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
				scannerObj.nextLine(); // consume newline
			}

		}
		return tmpInt;

	}

	//Displays basic menu for user input
	static void displayMenu() {
		// Display Menu

		System.out.println("\nMenu");
		System.out.println("1. List Users");
		System.out.println("2. Add User");
		System.out.println("3. Delete User");
		System.out.println("4. Get Password Hint");
		System.out.println("5. Friend someone");
		System.out.println("6. Defriend someone");
		System.out.println("7. List Friends");
		System.out.println("8. Quit");

	}

	//Prints list of facebook users
	static void printUsers(ArrayList<FacebookUser> users) {
		System.out.println("\nUsers:");
		for (FacebookUser i : users) {
			System.out.println(i);
		}
	}

	//Retrieves string input from user
	static String getStrInput(String msg) {

		System.out.println(msg);

		return scannerObj.nextLine();

	}

	//Adds user to facebook.
	static void addUser() {
		String userName = getStrInput("Enter Username to add");

		//Perform a search for user before asking for more info
		if (facebookObj.searchUser(userName) != null) {
			System.out.println("Error: User already exists");
			return;
		}

		String password = getStrInput("Enter password for new user");
		String passwordHint = getStrInput("Enter password hint for new user");

		facebookObj.addUser(userName, password, passwordHint);

	}

	//Write facebook object to file
	static void writeDB() throws IOException {
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("facebook.dat", false));) {

			output.writeObject(facebookObj);
		}
	}

	//Read facebook object from file
	static void readDB() throws IOException, ClassNotFoundException {
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("facebook.dat"));) {

			facebookObj = (Facebook) input.readObject();

		}
	}
	
	//Manages user input
	static void runFacebook(){
		int usrChoice = 0;
		//Loop until users decides to quit
		do {
			displayMenu();
			usrChoice = getInt("Please select a choice between 1-8", 1, 8);
			switch (usrChoice) {
			case 1: //Print all facebook users
				try {
					printUsers(facebookObj.listUsers());
				} catch (CloneNotSupportedException e1) {
					System.out.println(e1.getMessage());				
				}
				break;
			case 2: {//Add new user to facebook
				try {
					addUser();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 3: { //delete user from facebook
				try {
					String usr = getStrInput("Enter username to delete");
					facebookObj.deleteUser(usr);
					System.out.println("\nDeleted " + usr + " from facebook");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 4: { //Retrieve users password hint
				try {
					System.out.println("Password Hint: " + facebookObj.getPasswordHint(getStrInput("Enter username")));
				} catch (RuntimeException ex) {
					System.out.println(ex.getMessage());
				}
				break;

			}
			case 5: {
				break;
			}
			case 6: {
				break;
			}
			case 7: {
				break;
			}
			}
		} while (usrChoice != 8);
	}

	//Main function of driver
	public static void main(String[] args) throws CloneNotSupportedException {
		
		
		//Load Facebook Object
		try {
			readDB();
		} catch (ClassNotFoundException e1) {
			System.out.print(e1.getMessage());
		} catch (IOException e1) {
			facebookObj = new Facebook(); //Set to new blank object if no file exists
		}


		runFacebook();		//Show menu and manage user input

		scannerObj.close(); //Close scanner
		
		//Save changes to disk
		System.out.println("Writing Facebook database to disk");
		try {
			writeDB();
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
		System.out.println("Buh bye");
	}

}
