import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

public class Twitter implements Cloneable {

	private ArrayList<TwitterUser> twitterUsers = new ArrayList<TwitterUser>(); // List of twitter accounts and data
	private String dbFile = "social_network.edgelist"; // space delimited text file of twitter data. Can be overridden
														// when constructor called

	// Constructors
	public Twitter() {

	}

	public Twitter(String fileName) {
		this.dbFile = fileName;
	}

	ArrayList<TwitterUser> getList() {
		return (ArrayList<TwitterUser>) this.twitterUsers.clone();
	}
	
	

	void loadDB() throws IOException {

		File twitterFile = new File(this.dbFile);
		if (!twitterFile.exists()) {
			Utilities.showMessage(
					"Unable to find file, " + this.dbFile
							+ ", in current working directory.  Please select the file to use",
					"Unable to find " + this.dbFile);

			try {
				twitterFile = Utilities.getFile();
			} catch (IOException e) {
				throw new IOException(e.getMessage());
			}
		}

		// Parse Data
		// Build Regex
		String twitterRegex = "^(?<userid>\\d+)\\s+(?<followed>\\d+)$";
		Pattern p = Pattern.compile(twitterRegex);
		
		
		// variables
		String lineRead; // used by bufferedreader
		TwitterUser curUser = null; // placeholder for current user being processed
		TwitterUser followedUser = null; // placeHolder for user to follow
		int userIndex; // used for binary search
		HashMap<Integer,TwitterUser> twitterMap = new HashMap<>(); //Use HashMap to easily store/retrieve values while loading flat file
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(twitterFile));) {

			while ((lineRead = bufferedReader.readLine()) != null) {

				Matcher m = p.matcher(lineRead);

				if (m.matches()) {
					int userID = Integer.parseInt(m.group("userid"));
					int userID2 = Integer.parseInt(m.group("followed"));
					// System.out.println(userID);
					// If userid changed, check if user exists in
					/*
					if (curUser == null || (curUser.getUserID() != userID)) {
						curUser = new TwitterUser(userID); // New UserID
						if ((userIndex = linearSearch( userID)) >= 0)
							
							curUser = this.twitterUsers.get(userIndex);
						else {
							this.twitterUsers.add(curUser); // add to list
							//Collections.sort(this.twitterUsers);
						}
					}*/
					curUser = twitterMap.get(userID);
					if(curUser == null) {
						curUser = new TwitterUser(userID);
						twitterMap.put(userID, curUser);
					}
					/*
					// Check if user to follow needs created
					followedUser = new TwitterUser(userID2);
					if ((userIndex = linearSearch( userID)) >= 0)
						
						followedUser = this.twitterUsers.get(userIndex);
					else {
						this.twitterUsers.add(followedUser);
						//Collections.sort(this.twitterUsers);
					}
					*/
					followedUser = twitterMap.get(userID2);
					if(followedUser == null) {
						followedUser = new TwitterUser(userID2);
						twitterMap.put(userID2,followedUser);
					}
					// Follow User
					if (!curUser.isFollowed(followedUser))
						curUser.follow(followedUser);
					
					

				}

			}
			//Add hashmap values to arraylist and sort
			this.twitterUsers.addAll(twitterMap.values());
			Collections.sort(this.twitterUsers);
		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			throw new IOException(e.getMessage());
		}

	}

	ArrayList<TwitterUser> getNeighborhood(int userID, int maxDepth) throws RuntimeException, CloneNotSupportedException {
		TwitterUser tmpUser = binarySearch( userID);
		if (tmpUser == null)
			throw new RuntimeException("Error: User " + userID + " does not exist");

		ArrayList<TwitterUser> listToReturn = new ArrayList<TwitterUser>();

		listToReturn = getNeighborhood(tmpUser, listToReturn, tmpUser, 0, maxDepth);
		return listToReturn;

	}

	ArrayList<TwitterUser> getNeighborhood(TwitterUser baseUser, ArrayList<TwitterUser> listToReturn,
			TwitterUser tmpUser, int depth, int maxDepth) throws CloneNotSupportedException {
		ArrayList<TwitterUser> asdf = tmpUser.getFollowed();
		
		for (TwitterUser i : asdf) {
			// Add to list if it does not already exist and is not the initial user we are
			// making recommendation for
			if (!listToReturn.contains(i)) {
				listToReturn.add(i);
				if (depth <= maxDepth)
					getNeighborhood(baseUser, listToReturn, i, ++depth, maxDepth);
			}
			
		

		}

		return listToReturn;

	}

	// binary search of twitteruser array, using binary search
	TwitterUser binarySearch( int x) throws CloneNotSupportedException {
		int left = 0;
		int right = this.twitterUsers.size() - 1;
		while (left <= right) {
			int middle = left + (right - left) / 2;
			TwitterUser tmpUser = this.twitterUsers.get(middle);
			// Found user
			if (tmpUser.getUserID() == x)
				return tmpUser;

			// split into right half
			if (tmpUser.getUserID() < x)
				left = middle++;

			// split into left half
			else
				right = middle--;
		}

		return null;
	}
	
	Integer linearSearch(int x) {
		for (int i = 0; i < this.twitterUsers.size();i++) {
			if (this.twitterUsers.get(i).getUserID() == x) 
				return x;
						
		}
		return -1;
			
	}
}
