import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FacebookDriver {
	static boolean debug = false;
	static Facebook facebookObj = new Facebook();
	static Scanner scannerObj = new Scanner(System.in); // Object for all user input

	// Function to retrieve integer input. Performs input validation to only allow
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

	// Displays basic menu for user input
	static void displayMenu() {
		// Display Menu

		System.out.println("\nMenu");
		
		System.out.println("1. List users alphabetically");
		System.out.println("2. List users by number of friends");
		System.out.println("3. Add User");
		System.out.println("4. Delete User");
		System.out.println("5. Get Password Hint");
		System.out.println("6. Friend someone");
		System.out.println("7. Defriend someone");
		System.out.println("8. List Friends");
		System.out.println("9. Recommend Friends");
		System.out.println("10. Like something");
		System.out.println("11. List Likes");
		System.out.println("12. Quit");

	}

	static FacebookUser searchUsers(String userName) throws CloneNotSupportedException {
		for (FacebookUser i : facebookObj.listUsers()) {
			if (i.toString().equalsIgnoreCase(userName))
				return i;
		}
		return null;
	}

	// Prints list of facebook users
	static void printUsers(ArrayList<FacebookUser> users) {
		System.out.println();
		String format = "%-10s%s%n";
		System.out.printf(format,"User","Num Friends");
		for (FacebookUser i : users) {
			try {
			//	System.out.print(i.getUsername() + "\t" + i.getFriends().size());
				System.out.printf(format, i.getUsername(),i.getFriends().size());
			} catch (CloneNotSupportedException e) {
			
				System.out.println(e.getMessage());
			}
		}
	}

	// Retrieves string input from user
	public static String getStrInput(String msg) {

		System.out.println(msg);

		return scannerObj.nextLine();

	}

	// Adds user to facebook.
	static void addUser() throws CloneNotSupportedException {
		String userName = getStrInput("Enter Username to add");

		// Perform a search for user before asking for more info
		if (searchUsers(userName) != null) {
			System.out.println("Error: User already exists");
			return;
		}

		String password = getStrInput("Enter password for new user");
		String passwordHint = getStrInput("Enter password hint for new user");

		try {
			facebookObj.addUser(userName, password, passwordHint);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}

	}

	// Write facebook object to file
	static void writeDB() throws IOException {
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("facebook.dat", false));) {

			output.writeObject(facebookObj);
		}
	}

	// Read facebook object from file
	static void readDB() throws IOException, ClassNotFoundException {
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("facebook.dat"));) {

			facebookObj = (Facebook) input.readObject();

		}
	}

	// Print out list of friends
	static void printFriends() {
		String userName = getStrInput("Enter username");
		ArrayList<FacebookUser> tmpList;
		try {
			tmpList = facebookObj.getFriends(userName);
			if (tmpList.size() == 0) {
				System.out.println("No friends in friends list");
				return;
			}
			System.out.println("\n" + userName + "'s Friend list");
			System.out.println("----------------------------");
			for (FacebookUser i : tmpList) {
				System.out.println(i);
			}
		} catch (RuntimeException | CloneNotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}

	// Manages user input
	static void runFacebook() {
		int usrChoice = 0;
		// Loop until users decides to quit
		do {
			displayMenu();
			usrChoice = getInt("Please select a choice between 1-12", 1, 12);
			switch (usrChoice) {
			case 1: // Print all facebook users
				try {
					ArrayList<FacebookUser> tmpUsers = facebookObj.listUsers();
					Collections.sort(tmpUsers,new facebookNameComparator());
					printUsers(tmpUsers);
				} catch (CloneNotSupportedException e1) {
					System.out.println(e1.getMessage());
				}
				break;
			case 2: // Print all facebook users
				try {
					ArrayList<FacebookUser> tmpUsers = facebookObj.listUsers();
					Collections.sort(tmpUsers,new facebookFriendsComparator().reversed()); //Sort by number of friends
					//Collections.sort(tmpUsers,Collections.reverseOrder()); //Reverse order(descending)
					printUsers(tmpUsers);
				} catch (CloneNotSupportedException e1) {
					System.out.println(e1.getMessage());
				}
				break;
			case 3: {// Add new user to facebook
				try {
					addUser();
				} catch (RuntimeException | CloneNotSupportedException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 4: { // delete user from facebook
				try {
					String usr = getStrInput("Enter username to delete");
					facebookObj.deleteUser(usr);
					System.out.println("\nDeleted " + usr + " from facebook");
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 5: { // Retrieve users password hint
				try {
					System.out.println("Password Hint: " + facebookObj.getPasswordHint(getStrInput("Enter username")));
				} catch (RuntimeException ex) {
					System.out.println(ex.getMessage());
				}
				break;

			}
			case 6: {
				try {
					setFriend(1);
				} catch (RuntimeException | CloneNotSupportedException ex) {
					System.out.println(ex.getMessage());
				}
				break;
			}
			case 7: {
				try {
					setFriend(2);
				} catch (RuntimeException | CloneNotSupportedException ex) {
					System.out.println(ex.getMessage());
				}
				break;
			}
			case 8: {
				printFriends();
				break;
			}
			case 9: {
				getRecommendedFriends();

				break;
			}
			case 10: {
				try {
					//Like a string
					String username = getStrInput("Enter Username: ");
					String password = getStrInput("Enter Password: ");
					facebookObj.login(username, password);
					String strToLike = getStrInput("What do you like: ");
					facebookObj.like(username, strToLike);
					System.out.println(username + " likes " + strToLike);
				} catch (RuntimeException e) {
					System.out.println("ERROR: " + e.getMessage());
				}
				
				break;
			}
			case 11: {
				String username = getStrInput("Enter Username: ");
				System.out.println("\nLikes:");
				System.out.println("-------------------------------------");
				for (String i: facebookObj.getLikes(username)) {
					System.out.println(i);
				}

				break;
			}
			}
		} while (usrChoice != 12);
	}

	static void setFriend(int action) throws RuntimeException, CloneNotSupportedException {
		String userName = getStrInput("Enter username");
		FacebookUser usrObj = searchUsers(userName);
		if (usrObj == null)
			throw new RuntimeException("ERROR: " + userName + " does not exist in facebook database");

		String userPassword = getStrInput("Enter password");
		if (!usrObj.checkPassword(userPassword))
			throw new RuntimeException("ERROR: " + "incorrect password entered");

		switch (action) {
		case 1: {
			userName = getStrInput("Enter username to add friends list");
			FacebookUser friendToAdd = searchUsers(userName);
			if (friendToAdd == null)
				throw new RuntimeException("ERROR: " + userName + " does not exist in facebook database");
			facebookObj.friend(usrObj.getUsername(), friendToAdd.getUsername());
			break;
		}
		case 2: {
			userName = getStrInput("Enter username to remove from friends list");
			FacebookUser friendToAdd = searchUsers(userName);
			if (friendToAdd == null)
				throw new RuntimeException("ERROR: " + userName + " does not exist in facebook database");
			facebookObj.deFriend(usrObj.getUsername(), friendToAdd.getUsername());
			break;
		}
		}

	}

	static void getRecommendedFriends() {
		try {
			ArrayList<FacebookUser> friends = facebookObj.recommendFriends(getStrInput("Enter Username"));

			if (friends.size() == 0)
				System.out.println("No friends to recommend");
			else {
				System.out.println("Recommended friends based on your friend's friends lists");
				System.out.println("---------------------------------------------------------");
				Collections.sort(friends,new facebookFriendsComparator().reversed()); //Sort by number of friends
				printUsers(friends);
			}
		} catch (RuntimeException e) {
			System.out.println("Error: " + e.getMessage());
			return;
		}

	}

	// Main function of driver
	public static void main(String[] args) {

		// Load Facebook Object
		try {
			readDB();
		} catch (ClassNotFoundException e1) {
			System.out.print(e1.getMessage());
		} catch (IOException e1) {
			facebookObj = new Facebook(); // Set to new blank object if no file exists
		}
		
		// Generate random user data if debug is enabled
		if (debug) {
			// Create user accounts and sort
			int numUsers = 10;
			System.out.println("Creating " + numUsers + " facebook user accounts");
			facebookObj.genUsers(numUsers); // List of user accounts created by driver

		}

		runFacebook(); // Show menu and manage user input

		scannerObj.close(); // Close scanner

		// Save changes to disk
		System.out.println("Writing Facebook database to disk");
		try {
			writeDB();
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
		System.out.println("Buh bye");
	}

}
