import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Facebook implements Serializable {
	private static final long serialVersionUID = 7099795459621169470L;
	private ArrayList<FacebookUser> users = new ArrayList<FacebookUser>();

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
		this.users.remove(tmpUser);

	}

	FacebookUser searchUser(String userName) throws RuntimeException {

		for (FacebookUser i : this.users) {
			if (i.toString().equals(userName))
				return i;
		}
		throw new RuntimeException("User does not exist");
	}

	// Return copy of Users arraylist
	ArrayList<FacebookUser> listUsers() throws CloneNotSupportedException {
		ArrayList<FacebookUser> tmpUsers = new ArrayList<>();
		for (FacebookUser i : this.users) {
			tmpUsers.add(i.clone());
		}

		return tmpUsers;

	}

}
