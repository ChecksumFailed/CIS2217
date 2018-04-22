import java.io.Serializable;
import java.util.ArrayList;

import java.util.Collections;
//asdf

public  class TwitterUser implements Comparable<TwitterUser>, Cloneable,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3151299893635380816L;
	private Integer userID;  //Twitter Userid
	 ArrayList<TwitterUser> followed = new ArrayList<TwitterUser>(); //Users followed
	
	//Constructors
	public TwitterUser() {
		
	}
	
	public TwitterUser(Integer userID) {
		this.userID = userID;
	}

	public TwitterUser(Integer userID, ArrayList<TwitterUser> followed) {
		super();
		this.userID = userID;
		this.followed = followed;
	}

	// Return UserID
	Integer getUserID() {
		return userID;
	}

	//Set UserID
	void setUserID(Integer userID) {
		this.userID = userID;
	}

	//Return the number of users followed
	int getFollowedCount() {
		return this.followed.size();
	}
	
	//Compare by UserID
	@Override
	public int compareTo(TwitterUser usrToCompare) {
		
		return this.userID - usrToCompare.userID;
	}
	
	
	//deep copy clone method
	public TwitterUser clone() throws CloneNotSupportedException {
		TwitterUser tmpUsr = new TwitterUser(this.userID);
		for (TwitterUser userObj : this.followed) {
			tmpUsr.follow(userObj);
		}
		
		return tmpUsr;

	}
	
	//Follow twitter user
	void follow(TwitterUser usrObj) {
		if (this.followed.contains(usrObj))
			throw new RuntimeException("ERROR: " + usrObj.userID + " already being followed");
		this.followed.add(usrObj);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TwitterUser other = (TwitterUser) obj;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

	//unFollow twitter user
	void unFollow(TwitterUser usrObj) {
		if (!this.followed.contains(usrObj))
			throw new RuntimeException("ERROR: " + usrObj.userID + " is not being followed");
		this.followed.remove(usrObj);
	}
	
	
	//toString method
	public String toString() {
		return this.userID.toString();
	}
	
	
//Check if user is already followed
	boolean isFollowed(TwitterUser user) {
		if (Collections.binarySearch(this.followed, user) >= 0)
			return true;
		else
			return false;
	}
	
	ArrayList<TwitterUser> getFollowed() throws CloneNotSupportedException {
		/*ArrayList<TwitterUser> listToReturn = new ArrayList<TwitterUser>();
		
		for (TwitterUser i: this.followed) {
			listToReturn.add(i.clone());
			
		}
		return listToReturn;
		*/
		return this.followed;
	}
	


}
