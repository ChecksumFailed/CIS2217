import java.io.Serializable;
import java.util.ArrayList;


public class FacebookUser extends UserAccount implements Comparable<FacebookUser>, Serializable {
	
	private static final long serialVersionUID = 5726148661034014442L;
	
	private String passwordHint;
	private ArrayList<FacebookUser> friends;

	public FacebookUser(String username, String password) {
		super(username, password);
		friends = new ArrayList<>();
	}
	
	public void setPasswordHint(String hint) {
		passwordHint = hint;
	}
	
	public void friend(FacebookUser friend) {
		if (!friends.contains(friend)) {
			friends.add(friend);
		} else {
			System.out.println(this.toString() + " is already friends with " + friend);
		}
	}
	
	public void defriend(FacebookUser friend) {
		if (friends.contains(friend)) {
			friends.remove(friend);
		} else {
			System.out.println(this.toString() + " is not friends with " + friend);
		}
	}
	
	public ArrayList<FacebookUser> getFriends() {
		ArrayList<FacebookUser> copy = new ArrayList<>();
		copy.addAll(friends);
		return copy;
	}

	@Override
	public void getPasswordHelp() {
		System.out.println("Your password hint is " + passwordHint);
	}

	@Override
	public int compareTo(FacebookUser o) {
		return this.toString().compareToIgnoreCase(o.toString());
	}
}
