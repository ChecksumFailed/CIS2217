import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Twitter {
	private ArrayList<TwitterUser> twitterUsers = new ArrayList<TwitterUser>();
	
	public Twitter() {
		
	}
	
	void loadDB() {
		File asdf;
		// TODO Auto-generated method stub
		try {
			asdf = Utilities.getFile();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}
	//File asdf = getFile();
	
}
