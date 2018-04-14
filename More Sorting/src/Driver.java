/*Ben Scherer
 * CIS 2217.800
 * Chapter 23 Heap Sort
 */

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Driver {
	
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

    static String genSimpleString(int passLength) {
        StringBuilder newPass = new StringBuilder(); // Used to build password string
        SecureRandom random = new SecureRandom(); // Used to generate random numbers

        String upperChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
              String passwordChars = (upperChar + upperChar.toLowerCase()); // Combined Character
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
    
	public static <E> void main(String[] args) {
	       Random rand = new Random();
	       Utilities<?> utilities = new Utilities<>();
	       
		int numElements = 20; //number of elements in each test array
		
		
		// Test integers with heapsort
		System.out.println("Creating Random Integer Array");
		Integer intArr[] = new Integer[numElements];
		for (int i = 0; i < intArr.length;i++) {
			intArr[i] = rand.nextInt(99);
		}
		
		System.out.println("Initial Array: \n" + Arrays.toString(intArr));
		utilities.heapSort(intArr);
		System.out.println("Array after comparable heap sort: \n" + Arrays.toString(intArr));
		utilities.heapSort(intArr,utilities.BY_INTEGER_REVERSE);
		System.out.println("Array after comparator(Reverse Order) heap sort: \n" + Arrays.toString(intArr));
		
		
		//String 
		System.out.println("\n\nCreating Random String Array");
		String stringArr[] = new String[numElements];
		for (int i = 0; i < stringArr.length;i++) {
			stringArr[i] = genSimpleString(8);
		}
		System.out.println("Initial Array: \n" + Arrays.toString(stringArr));
		utilities.heapSort(stringArr);
		System.out.println("Array after comparable heap sort: \n" + Arrays.toString(stringArr));
		utilities.heapSort(stringArr,utilities.BY_STRING_IGNORECASE);
		System.out.println("Array after comparator(Ignore Case) heap sort: \n" + Arrays.toString(stringArr));

		
		//facebook users 
		System.out.println("\n\nCreating Random FacebookUser Array");
		FacebookUser facebookArr[] = new FacebookUser[numElements];
		genUsers(numElements).toArray(facebookArr);
		
	
		System.out.println("Initial Array: \n" + Arrays.toString(facebookArr));
		utilities.heapSort(facebookArr);
		System.out.println("Array after comparable heap sort: \n" + Arrays.toString(facebookArr));
		utilities.heapSort(facebookArr,FacebookUser.BY_NUMFRIENDS);
		System.out.println("Array after comparator(by number of friends) heap sort: ");
		System.out.println("User\tNum Friends");
		for (FacebookUser f : facebookArr) {
			
			//System.out.println(f.getUsername() + "\t\t\t" + f.friends.size());
			System.out.printf("%-20s %d\n", f.getUsername().toString(),f.friends.size()) ;
		}
	
	}
}
