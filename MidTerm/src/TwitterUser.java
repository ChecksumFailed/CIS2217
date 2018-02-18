import java.util.ArrayList;

public  class TwitterUser implements Comparable<TwitterUser>{
	private Integer userID;  //Twitter Userid
	private ArrayList<TwitterUser> usersFollowed = new ArrayList<TwitterUser>(); //Users followed
	
	//Constructors
	public TwitterUser() {
		
	}
	
	public TwitterUser(Integer userID) {
		this.userID = userID;
	}

	public TwitterUser(Integer userID, ArrayList<TwitterUser> usersFollowed) {
		super();
		this.userID = userID;
		this.usersFollowed = usersFollowed;
	}

	// Return UserID
	Integer getUserID() {
		return userID;
	}

	//Set UserID
	void setUserID(Integer userID) {
		this.userID = userID;
	}

	//Compare by UserID
	@Override
	public int compareTo(TwitterUser usrToCompare) {
		
		return this.userID - usrToCompare.userID;
	}
	
	//deep copy clone method
	public TwitterUser clone() throws CloneNotSupportedException {
		TwitterUser tmpUsr = new TwitterUser(this.userID);
		for (TwitterUser userObj : this.usersFollowed) {
			tmpUsr.follow(userObj);
		}
		
		return tmpUsr;

	}
	
	//Follow twitter user
	void follow(TwitterUser usrObj) {
		if (this.usersFollowed.contains(usrObj))
			throw new RuntimeException("ERROR: " + usrObj.userID + " already being followed");
		this.usersFollowed.add(usrObj);
	}
	
	//Follow twitter user
	void unFollow(TwitterUser usrObj) {
		if (!this.usersFollowed.contains(usrObj))
			throw new RuntimeException("ERROR: " + usrObj.userID + " is not being followed");
		this.usersFollowed.remove(usrObj);
	}
	
	
	
	
	
}
