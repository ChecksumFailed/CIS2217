import java.io.IOException;

;

public class Driver {

	public static void main(String[] args) {
		Twitter twitterObj = new Twitter();
		System.out.println("Loading twitter data(Please be patient)...");
		try {
			twitterObj.loadDB();
		} catch (IOException e) {
			Utilities.showMessage( e.getMessage().toString(),"ERROR!!!");
		}
		

		for (TwitterUser i : twitterObj.getList()) {
			System.out.println(i.getUserID() + " - " + i.getFollowedCount());
		}

	}

}
