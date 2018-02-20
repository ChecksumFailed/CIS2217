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

	void loadDB() {

		File twitterFile = new File(this.dbFile);
		if (!twitterFile.exists()) {
			Utilities.showMessage(
					"Unable to find file, " + this.dbFile
							+ " in current working directory.  Please select the file to use",
					"Unable to find " + this.dbFile);

			try {
				twitterFile = Utilities.getFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
					if (!curUser.isFriend(followedUser))
						curUser.follow(followedUser);

				}

			}

		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
