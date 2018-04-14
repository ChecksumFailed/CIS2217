import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class FacebookDriver {

	static Facebook facebookObj = new Facebook();
	static Scanner scannerObj = new Scanner(System.in); // Object for all user input

	
	 // Generate new password
    // https://docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html
    static String genPassword(int passLength) {
        StringBuilder newPass = new StringBuilder(); // Used to build password string
        SecureRandom random = new SecureRandom(); // Used to generate random numbers

        String upperChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numChar = "01234567890";
        String specialChar = "!@#$%^&*()_+ ,.-=[] {}~\\/";
        String passwordChars = (upperChar + upperChar.toLowerCase() + numChar + specialChar); // Combined Character
        // string for password

        // Append random character until we meet password length.
        for (int i = 1; i <= passLength; i++) {
            newPass.append(passwordChars.charAt(random.nextInt(passwordChars.length())));
        }

        return newPass.toString();

    }

    
    // Search arraylist for existing user.
    static Boolean searchList(ArrayList<FacebookUser> listToSearch, String strToSearch) {
        for (FacebookUser usr : listToSearch) {
            if (usr.getUsername().equalsIgnoreCase(strToSearch))
                return true;
        }

        return false;

    }

    // Generate facebook users for testing. Uses US Census data and SSA for names
    static ArrayList<FacebookUser> genUsers(int numUsers) {
        ArrayList<FacebookUser> tmpList = new ArrayList<FacebookUser>();// Array list to hold created users accounts

        // List of first and last names to create random user accounts.
        String[] surnames = {"SMITH", "JOHNSON", "WILLIAMS", "BROWN", "JONES", "GARCIA", "MILLER", "DAVIS",
                "RODRIGUEZ", "MARTINEZ", "HERNANDEZ", "LOPEZ", "GONZALEZ", "WILSON", "ANDERSON", "THOMAS", "TAYLOR",
                "MOORE", "JACKSON", "MARTIN", "LEE", "PEREZ", "THOMPSON", "WHITE", "HARRIS", "SANCHEZ", "CLARK",
                "RAMIREZ", "LEWIS", "ROBINSON", "WALKER", "YOUNG", "ALLEN", "KING", "WRIGHT", "SCOTT", "TORRES",
                "NGUYEN", "HILL", "FLORES", "GREEN", "ADAMS", "NELSON", "BAKER", "HALL", "RIVERA", "CAMPBELL",
                "MITCHELL", "CARTER", "ROBERTS"};
        String[] firstNames = {"Noah", "Liam", "William", "Mason", "James", "Benjamin", "Jacob", "Michael", "Elijah",
                "Ethan", "Alexander", "Oliver", "Daniel", "Lucas", "Matthew", "Aiden", "Jackson", "Logan", "David",
                "Joseph", "Samuel", "Henry", "Owen", "Sebastian", "Gabriel", "Emma", "Olivia", "Ava", "Sophia",
                "Isabella", "Mia", "Charlotte", "Abigail", "Emily", "Harper", "Amelia", "Evelyn", "Elizabeth", "Sofia",
                "Madison", "Avery", "Ella", "Scarlett", "Grace", "Chloe", "Victoria", "Riley", "Aria", "Lily",
                "Aubrey"};
        Random rand = new Random();
        String userName;

        // create users and build list
        for (int i = 1; i <= numUsers; i++) {
            do {
                userName = firstNames[rand.nextInt(firstNames.length)] + "." + surnames[rand.nextInt(surnames.length)]; // Generate
                // Username
            } while (searchList(tmpList, userName)); // Check if user already exists
            String userPassword = genPassword(8); // generate random password
            FacebookUser tmpUsr = new FacebookUser(userName, userPassword); // create new user
            tmpUsr.setPasswordHint("Your Password is: " + userPassword); // set password hint
            // tmpUsr.getPasswordHelp();
            tmpList.add(tmpUsr);
        }
        
        for (FacebookUser f: tmpList) {
        	addRandomFriends(f,tmpList);
        }
        return tmpList;
    }
	
    // Generate random friend lists
    static void addRandomFriends(FacebookUser usrObj, ArrayList<FacebookUser> allUsers) {
        Random rand = new Random();
        if (allUsers.size() == 1) {
            System.out.println("Error:  List must contain other users to add to friends list");
            return;
        }
        int numFriends = rand.nextInt(allUsers.size() - 1); // Random number of friends.  Reduce by one to exclude self
        int numAvailable = allUsers.size(); // Total number for user accounts available
        FacebookUser tmpUser; // user object to add to friends list

        for (int i = 0; i <= numFriends; i++) {
            do {
                tmpUser = allUsers.get(rand.nextInt(numAvailable));
            } while (usrObj.equals(tmpUser) || usrObj.friends.contains(tmpUser)); // Do not add account if it is the
            // same as user object or already
            // exists
            usrObj.friend(tmpUser);
        }

    }
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
		System.out.println("5. Quit");

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

	//Main function of driver
	public static void main(String[] args) throws CloneNotSupportedException {
		int usrChoice = 0;
		boolean debug = false;
		
		//Load Facebook Object
		try {
			readDB();
		} catch (ClassNotFoundException e1) {
			System.out.print(e1.getMessage());
		} catch (IOException e1) {
			
			if (debug)
				facebookObj = new Facebook(genUsers(20)); 
			else
				facebookObj = new Facebook(); //Set to new blank object if no file exists
		}

		//Loop until users decides to quit
		do {
			displayMenu();
			usrChoice = getInt("Please select a choice between 1-5", 1, 5);
			switch (usrChoice) {
			case 1: //Print all facebook users
				printUsers(facebookObj.listUsers());
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
			}
		} while (usrChoice != 5);

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
