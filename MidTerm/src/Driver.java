;

public class Driver {

	public static void main(String[] args) {
		Twitter twitterObj = new Twitter();
		System.out.println("Loading twitter data...");
		twitterObj.loadDB();
		
		for (TwitterUser i : twitterObj.getList()) {
			System.out.println(i.getUserID() + " - " + i.getFollowedCount());
		}
		
	}

}
