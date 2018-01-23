/* Ben Scherer
 * CIS 2217
 * driver for UserAccount Class
 */

public class UserAccountDriver {

	public static void main(String[] args) {
		// Create useraccount objects
		UserAccount account1 = new UserAccount("bsmith", "pass1234");
		UserAccount account2 = new UserAccount("jdoe", "p@ssw0rd");
		UserAccount account3 = new UserAccount("jdoe", "dsdf@ssw0rd");

		System.out.println("Account1: " + account1);
		System.out.println("Account1: " + account2);
		System.out.println("Account3: " + account3);
		System.out.println();

		// Check Password validation
		System.out.println("Testing checkpassword function with invalid password");
		if (account1.checkPassword("badpass"))
			System.out.println("Passwords match!");
		else
			System.out.println("Passwords do not match");
		System.out.println("Testing checkpassword function with valid password");
		if (account1.checkPassword("pass1234"))
			System.out.println("Passwords match!");
		else
			System.out.println("Passwords do not match");

		// Deactivate account testing
		System.out.println();
		System.out.println("Testing account deactivation");
		System.out.println("Account1 status: " + account1.isActive());
		account1.deactiveAccount();
		System.out.println("Deactivating Account1");
		System.out.println("Account1 status: " + account1.isActive());

		// Testing Accounts for equalness
		System.out.println();
		System.out.println("Testing if account1 = account2");
		if (account1.equals(account2))
			System.out.println(account1 + " EQUAL TO " + account2);
		else
			System.out.println(account1 + " NOT EQUAL TO " + account2);

		System.out.println("Testing if account2 = account3");
		if (account2.equals(account3))
			System.out.println(account2 + " EQUAL TO " + account3);
		else
			System.out.println(account2 + " NOT EQUAL TO " + account3);

	}

}
