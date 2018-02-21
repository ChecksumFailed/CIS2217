import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Twitter {

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

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(twitterFile));) {

			while ((lineRead = bufferedReader.readLine()) != null) {

				Matcher m = p.matcher(lineRead);

				if (m.matches()) {
					int userID = Integer.parseInt(m.group("userid"));
					int userID2 = Integer.parseInt(m.group("followed"));
					// System.out.println(userID);
					// If userid changed, check if user exists in

					if (curUser == null || (curUser.getUserID() != userID)) {
						curUser = new TwitterUser(userID); // New UserID
						if ((userIndex = Collections.binarySearch(this.twitterUsers, curUser)) >= 0)
							curUser = this.twitterUsers.get(userIndex);
						else
							this.twitterUsers.add(curUser); // add to list
					}

					// Check if user to follow needs created
					followedUser = new TwitterUser(userID2);
					if ((userIndex = Collections.binarySearch(this.twitterUsers, followedUser)) >= 0)
						followedUser = this.twitterUsers.get(userIndex);

					// Follow User
					if (!curUser.isFollowed(followedUser))
						curUser.follow(followedUser);

				}

			}

		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			throw new IOException(e.getMessage());
		}

	}

	ArrayList<TwitterUser> getNeighborhood(int userID) throws RuntimeException {
		TwitterUser tmpUser = binarySearch(this.twitterUsers, userID);
		if (tmpUser == null)
			throw new RuntimeException("Error: User " + userID + " does not exist");

		ArrayList<TwitterUser> listToReturn = new ArrayList<TwitterUser>();

		listToReturn = getNeighborhood(tmpUser, listToReturn, tmpUser, 0, 4);
		return listToReturn;

	}

	ArrayList<TwitterUser> getNeighborhood(TwitterUser baseUser, ArrayList<TwitterUser> listToReturn,
			TwitterUser tmpUser, int depth, int maxDepth) {

		for (TwitterUser i : tmpUser.getFollowed()) {
			// Add to list if it does not already exist and is not the initial user we are
			// making recommendation for
			if (baseUser != i && !listToReturn.contains(i)) {
				listToReturn.add(i);
				getNeighborhood(tmpUser, listToReturn, tmpUser, depth++, maxDepth);
			}
			
		

		}

		return listToReturn;

	}

	// binary search of twitteruser array, using binary search
	TwitterUser binarySearch(ArrayList<TwitterUser> twitterArr, int x) {
		int left = 0;
		int right = twitterArr.size() - 1;
		while (left <= right) {
			int middle = left + (right - left) / 2;
			TwitterUser tmpUser = twitterArr.get(middle);
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
}
