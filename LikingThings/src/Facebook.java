import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;

public class Facebook implements Serializable {
	private static final long serialVersionUID = 7099795459621169470L;
	private ArrayList<FacebookUser> users = new ArrayList<FacebookUser>(); // Arraylist to hold all users

	// Constructor
	public Facebook() {

	}

	// Add new user to facebook
	void addUser(String userName, String Password, String passwordHint) throws RuntimeException {
		FacebookUser tmpUser = new FacebookUser(userName, Password);
		tmpUser.setPasswordHint(passwordHint);
		this.users.add(tmpUser);
		Collections.sort(this.users);

	}

	// Delete User from facebook
	void deleteUser(String userName) throws RuntimeException {

		FacebookUser tmpUser = searchUser(userName);
		if (tmpUser == null) {
			throw new RuntimeException("Error: User " + userName + " does not exist");
		}
		this.users.remove(tmpUser);

	}

	// Search for user. Return null if not found
	private FacebookUser searchUser(String userName) {

		for (FacebookUser i : this.users) {
			if (i.toString().equalsIgnoreCase(userName))
				return i;
		}
		return null;
	}

	// Return copy of Users arraylist
	ArrayList<FacebookUser> listUsers() throws CloneNotSupportedException {
		ArrayList<FacebookUser> tmpUsers = new ArrayList<>();
		for (FacebookUser i : this.users) {
			tmpUsers.add(i.clone());
		}

		return tmpUsers;

	}

	// returns password hint of user
	String getPasswordHint(String userName) throws RuntimeException {
		FacebookUser tmpUser = searchUser(userName);
		if (tmpUser == null) {
			throw new RuntimeException("Error: User " + userName + " does not exist");
		}
		return tmpUser.getPasswordHelp();
	}

	// Add user to friends list
	void friend(String userName, String usrFriend) throws RuntimeException {
		FacebookUser baseUser = searchUser(userName);
		if (baseUser == null) {
			throw new RuntimeException("Error: User " + userName + " does not exist");
		}

		FacebookUser friendUser = searchUser(usrFriend);
		if (friendUser == null) {
			throw new RuntimeException("Error: User " + userName + " does not exist");
		}

		baseUser.friend(friendUser);

	}

	// Defriend user
	void deFriend(String userName, String usrFriend) throws RuntimeException {
		FacebookUser baseUser = searchUser(userName);
		if (baseUser == null) {
			throw new RuntimeException("Error: User " + userName + " does not exist");
		}

		FacebookUser friendUser = searchUser(usrFriend);
		if (friendUser == null) {
			throw new RuntimeException("Error: User " + userName + " does not exist");
		}

		baseUser.friend(friendUser);
	}

	ArrayList<FacebookUser> getFriends(String userName) throws RuntimeException, CloneNotSupportedException {
		FacebookUser tmpUser = searchUser(userName);
		if (tmpUser == null)
			throw new RuntimeException("Error: User " + userName + " does not exist");
		return tmpUser.getFriends();

	}

	ArrayList<FacebookUser> recommendFriends(String userName) throws RuntimeException {
		FacebookUser tmpUser = searchUser(userName);
		if (tmpUser == null)
			throw new RuntimeException("Error: User " + userName + " does not exist");
		// System.out.println(tmpUser.friends.size());
		ArrayList<FacebookUser> listToReturn = new ArrayList<FacebookUser>();
		ArrayList<FacebookUser> processedUsers = new ArrayList<FacebookUser>();
		// listToReturn = recurseFriends(tmpUser, tmpUser.friends, listToReturn,
		// tmpUser);
		listToReturn = recurseFriends(tmpUser, listToReturn, processedUsers, tmpUser);
		return listToReturn;

	}

	ArrayList<FacebookUser> recurseFriends(FacebookUser baseUser, ArrayList<FacebookUser> listToReturn,
			ArrayList<FacebookUser> processedUsers, FacebookUser tmpUser) {

		if (processedUsers.contains(tmpUser))
			return listToReturn;
		processedUsers.add(tmpUser);

		for (FacebookUser i : tmpUser.friends) {
			// Add to list if it does not already exist and is not the initial user we are
			// making recommendation for
			if (baseUser != i && !listToReturn.contains(i))
				listToReturn.add(i);
			// Recurse friends friends, if not already processed
			if (!processedUsers.contains(i))
				recurseFriends(tmpUser, listToReturn, processedUsers, tmpUser);
		}

		return listToReturn;

	}

	// Generate facebook users for testing. Uses US Census data and SSA for names
	void genUsers(int numUsers) {

		// List of first and last names to create random user accounts.
		String[] surnames = { "SMITH", "JOHNSON", "WILLIAMS", "BROWN", "JONES", "GARCIA", "MILLER", "DAVIS",
				"RODRIGUEZ", "MARTINEZ", "HERNANDEZ", "LOPEZ", "GONZALEZ", "WILSON", "ANDERSON", "THOMAS", "TAYLOR",
				"MOORE", "JACKSON", "MARTIN", "LEE", "PEREZ", "THOMPSON", "WHITE", "HARRIS", "SANCHEZ", "CLARK",
				"RAMIREZ", "LEWIS", "ROBINSON", "WALKER", "YOUNG", "ALLEN", "KING", "WRIGHT", "SCOTT", "TORRES",
				"NGUYEN", "HILL", "FLORES", "GREEN", "ADAMS", "NELSON", "BAKER", "HALL", "RIVERA", "CAMPBELL",
				"MITCHELL", "CARTER", "ROBERTS" };
		String[] firstNames = { "Noah", "Liam", "William", "Mason", "James", "Benjamin", "Jacob", "Michael", "Elijah",
				"Ethan", "Alexander", "Oliver", "Daniel", "Lucas", "Matthew", "Aiden", "Jackson", "Logan", "David",
				"Joseph", "Samuel", "Henry", "Owen", "Sebastian", "Gabriel", "Emma", "Olivia", "Ava", "Sophia",
				"Isabella", "Mia", "Charlotte", "Abigail", "Emily", "Harper", "Amelia", "Evelyn", "Elizabeth", "Sofia",
				"Madison", "Avery", "Ella", "Scarlett", "Grace", "Chloe", "Victoria", "Riley", "Aria", "Lily",
				"Aubrey" };
		Random rand = new Random();
		String userName;

		// create users and build list
		for (int i = 1; i <= numUsers; i++) {
			do {
				userName = firstNames[rand.nextInt(firstNames.length)] + "." + surnames[rand.nextInt(surnames.length)]; // Generate
				// Username
			} while (searchUser(userName) != null); // Check if user already exists
			String userPassword = genPassword(8); // generate random password
			FacebookUser tmpUsr = new FacebookUser(userName, userPassword); // create new user
			tmpUsr.setPasswordHint("Your Password is: " + userPassword); // set password hint
			// tmpUsr.getPasswordHelp();

			this.users.add(tmpUsr);
		}

	}

	// Generate random friend lists
	void addRandomFriends(FacebookUser usrObj) {
		Random rand = new Random();
		if (this.users.size() == 1) {
			System.out.println("Error:  List must contain other users to add to friends list");
			return;
		}
		int numFriends = rand.nextInt(this.users.size() - 1); // Random number of friends. Reduce by one to exclude self
		int numAvailable = this.users.size(); // Total number for user accounts available
		FacebookUser tmpUser; // user object to add to friends list

		for (int i = 0; i <= numFriends; i++) {
			do {
				tmpUser = this.users.get(rand.nextInt(numAvailable));
			} while (usrObj.equals(tmpUser) || usrObj.friends.contains(tmpUser)); // Do not add account if it is the
			// same as user object or already
			// exists
			usrObj.friend(tmpUser);
		}

	}

	// Generate random friend lists for all users
	void addRandomFriends() {
		Random rand = new Random();
		if (this.users.size() == 1) {
			System.out.println("Error:  List must contain other users to add to friends list");
			return;
		}
		int numFriends = rand.nextInt(this.users.size() - 1); // Random number of friends. Reduce by one to exclude self
		int numAvailable = this.users.size(); // Total number for user accounts available
		FacebookUser tmpUser; // user object to add to friends list

		for (FacebookUser usrObj : this.users) {

			for (int i = 0; i <= numFriends; i++) {
				do {
					tmpUser = this.users.get(rand.nextInt(numAvailable));
				} while (usrObj.equals(tmpUser) || usrObj.friends.contains(tmpUser)); // Do not add account if it is the
				// same as user object or already
				// exists
				usrObj.friend(tmpUser);
			}
		}

	}

	// Generate new password
	// https://docs.oracle.com/javase/8/docs/api/java/security/SecureRandom.html
	String genPassword(int passLength) {
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

	void login(String username, String password) throws RuntimeException {

		FacebookUser tmpUser = this.binarySearch(username);
		if (tmpUser == null)
			throw new RuntimeException("Invalid Username");

		if (!tmpUser.checkPassword(password))
			throw new RuntimeException("Invalid password entered");

	}

	// binary search of facebook users array, using binary search
	FacebookUser binarySearch(String x) {
		int left = 0;
		int right = this.users.size() - 1;
		while (left <= right) {
			int middle = left + (right - left) / 2;
			FacebookUser tmpUser = this.users.get(middle);
			// Found user
			if (tmpUser.getUsername() == x)
				return tmpUser;

			// split into right half
			if (tmpUser.getUsername().compareTo(x) < 0)
				left = middle++;

			// split into left half
			else
				right = middle--;
		}

		return null;
	}
	
	void like(String username, String strToLike) throws RuntimeException{
		FacebookUser tmpUser = this.binarySearch(username);
		if (tmpUser == null)
			throw new RuntimeException("Invalid Username");
		
		tmpUser.like(strToLike);
		
		
	}
	
	TreeSet<String> getLikes(String username) throws RuntimeException {
		FacebookUser tmpUser = this.binarySearch(username);
		if (tmpUser == null)
			throw new RuntimeException("Invalid Username");
		
		return tmpUser.getLikes();
		
		
	}

}
