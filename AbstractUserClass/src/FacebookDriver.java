import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.security.SecureRandom;

/*Author: Ben Scherer
 * Class: CIS.2217.800
 * Class: FacebookUserDriver
 * Description: Driver for FacebookUser Class
 */
public class FacebookDriver {

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

    // Remove random friends
    static void removeRandomFriends(FacebookUser usrObj) {
        Random rand = new Random();
        if (usrObj.friends.size() == 0)
            return;
        int numFriends = rand.nextInt(usrObj.friends.size()); // Random number of friends

        FacebookUser tmpUser; // user object to add to friends list

        for (int i = 0; i <= numFriends; i++) {

            tmpUser = usrObj.friends.get(rand.nextInt(usrObj.friends.size()));

            usrObj.defriend(tmpUser);
        }

    }

    public static void main(String[] args) {
        int numUsers = 10; // number of users to generate

        // Create user accounts and sort
        System.out.println("Creating " + numUsers + " facebook user accounts");
        ArrayList<FacebookUser> facebookUsers = genUsers(numUsers); // List of user accounts created by driver
        System.out.println("Sorting list....");
        Collections.sort(facebookUsers);


        //Create friends lists for all the users.  Randomized
        System.out.println("Generating random friends lists for users");
        for (FacebookUser i : facebookUsers) {
            addRandomFriends(i, facebookUsers); // Build friends list for user
        }


        //Print out list of users and their password int.
        System.out.println("\nList of facebook Users in system: ");
        System.out.println("UserName\tPassword Hint");
        for (FacebookUser i : facebookUsers) {
            System.out.print(i);
            System.out.print("\t");
            i.getPasswordHelp();

        }


        //Print list of users and their friends list
        System.out.println("\nList of users and their friends list");
        System.out.println("UserName\tFriends");
        for (FacebookUser i : facebookUsers) {
            System.out.println(i);

            try {
                for (FacebookUser y : i.getFriends()) {
                    System.out.println("\t\t" + y);
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

        }


        //Randomly remove users from friends lists
        System.out.println("Removing random users from Friend Lists");
        for (FacebookUser i : facebookUsers) {
            removeRandomFriends(i); // Build friends list for user
        }


        //Print out users and friends lists, post removals.
        System.out.println("\nList of users and their friends After random removals");
        System.out.println("UserName\tFriends");
        for (FacebookUser i : facebookUsers) {
            System.out.println(i);

            try {
                for (FacebookUser y : i.getFriends()) {
                    System.out.println("\t\t" + y);
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

        }

    }

}
