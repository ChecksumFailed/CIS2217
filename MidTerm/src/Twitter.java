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
	private ArrayList<TwitterUser> twitterUsers = new ArrayList<TwitterUser>(); //List of twitter accounts and data
	private String dbFile = "social_network.edgelist"; //space delimited text file of twitter data. Can be overridden when constructor called
	
	//Constructors
	public Twitter() {
		
	}
	
	public Twitter(String fileName) {
		this.dbFile = fileName;
	}
	
	void loadDB() {
		
		File twitterFile = new File(this.dbFile);
		if (!twitterFile.exists()) {
			Utilities.showMessage("Unable to find file, " + this.dbFile + " in current working directory.  Please select the file to use","Unable to find " + this.dbFile);
			try {
				twitterFile = Utilities.getFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String lineRead;
		try (
				BufferedReader bufferedReader = new BufferedReader(new FileReader(twitterFile));
				) {
			while ((lineRead = bufferedReader.readLine()) != null) {
				strToTwitterUser(lineRead);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	void strToTwitterUser(String strToParse) {
		String twitterRegex = "^(?<userid>\\d+)\\s+(?<followed>\\d+)$";
		Pattern p = Pattern.compile(twitterRegex);
		Matcher m = p.matcher(strToParse);
		if (!m.matches())
			return;
		
		TwitterUser tmpUser = new TwitterUser(Integer.parseInt(m.group("UserID")));
		TwitterUser tmpFollowed = new TwitterUser(Integer.parseInt(m.group("followed")));
		
		
	}
	

}
