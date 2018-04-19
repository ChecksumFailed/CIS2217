import java.io.Serializable;
import java.util.Scanner;


public class Action implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String theAction;
	private FacebookUser subjectUser;
	private Object object;
	
	public Action(String theAction, FacebookUser subjectUser, Object object) {
		this.theAction = theAction;
		this.subjectUser = subjectUser;
		this.object = object;
	}
	
	public boolean undo() {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the password for " + subjectUser.toString() + ": ");
		String password = input.nextLine();
		
		if (!subjectUser.checkPassword(password)) {
			System.out.println("Invalid password");
			return false;
		}
		
		if (theAction.equals("addFriend")) {
			subjectUser.defriend((FacebookUser) object);
			
		} else if (theAction.equals("likeSomething")) {
			
		} else if (theAction.equals("removeFriend")) {
			subjectUser.friend((FacebookUser) object);
		}
		
		return true;
	}
}
