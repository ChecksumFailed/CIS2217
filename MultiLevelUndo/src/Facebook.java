import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;



public class Facebook implements Serializable {
	private static final long serialVersionUID = 7099795459621169470L;
	private ArrayList<FacebookUser> users = new ArrayList<FacebookUser>(); //Arraylist to hold all users
	private GenericStack<FacebookUndo> undoList = new GenericStack<>();
	
	//Constructor
	public Facebook() {

	}
	
	//Constructor
	public Facebook(ArrayList<FacebookUser> list) {
        for (FacebookUser f: list) {
        	this.users.add(f);
        }
	}

	// Add new user to facebook
	void addUser(String userName, String Password, String passwordHint) throws RuntimeException {
		
		FacebookUser tmpUser = new FacebookUser(userName, Password);
		tmpUser.setPasswordHint(passwordHint);
		this.users.add(tmpUser);
		Collections.sort(this.users);
		this.undoList.push(new FacebookUndo("deleteUser",this));
		

	}

	// Delete User from facebook
	void deleteUser(String userName) throws RuntimeException {

		FacebookUser tmpUser = searchUser(userName);
		if (tmpUser == null) {
			throw new RuntimeException("Error: User " + userName + " does not exist");
		}
		this.users.remove(tmpUser);

	}

	//Search for user.  Return null if not found
	FacebookUser searchUser(String userName)  {

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
	
	//returns password hint of user
	String getPasswordHint(String userName) throws RuntimeException {
		FacebookUser tmpUser = searchUser(userName);
		if (tmpUser == null) {
			throw new RuntimeException("Error: User " + userName + " does not exist");
		}
		return tmpUser.getPasswordHelp();
	}
	
	class FacebookUndo {
		String undoAction; //Undo action to perform
		Object sourceObj; //Object to initate undo action against
		Object undoObj; //Object that is being added/removed back to sourceObj
		
		//Constructors
		FacebookUndo() {
			
		}
		
		FacebookUndo(String undoAction,Object sourceObj, Object undoObj) {
			this.undoAction = undoAction;
			this.sourceObj = sourceObj;
			this.undoObj = undoObj;
		}
	}
	

}

