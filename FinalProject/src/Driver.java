import java.io.IOException;
//
import java.util.ArrayList;
;

public class Driver {

	public static void main(String[] args) throws CloneNotSupportedException {
		Twitter twitterObj = new Twitter();
		System.out.println("Loading twitter data(Please be patient)...");
		int nanoInSecond = 1000000000;
		 Long startTime = System.nanoTime(); 
		try {
		
			twitterObj.loadDB();
			
	       	        
		} catch (IOException|RuntimeException e) {
			Utilities.showError( e.getMessage().toString(),"ERROR!!!");
			return;
		}
		 Long endTime = System.nanoTime();
	     int elapsedTime = (int) ((endTime - startTime) / nanoInSecond);
	     
		
		
		System.out.println("Loaded " + twitterObj.getList().size() + " Unique users, their associated followed users, and followers from flat file in " + elapsedTime + " seconds\n" );
		TwitterUser tmpUser = twitterObj.twitterUsers.get(58954); //Not too many followers or followed.  Do not want to blow up console with output
		System.out.println("Testing Clone of User " + tmpUser + "");
		TwitterUser cloneUser = tmpUser.clone();
		
		System.out.println("User " + tmpUser + " follows: " );
		System.out.println(tmpUser.getFollowed() + "\n");
		
		System.out.println("Cloned user follows: " );
		System.out.println(cloneUser.getFollowed() + "\n");
		
		System.out.println("Clearing all followed users of CLoned User\n");
		cloneUser.followed.clear();
		
		System.out.println("User " + tmpUser + " follows: " );
		System.out.println(tmpUser.getFollowed() + "\n");
		
		System.out.println("Cloned user follows: " );
		System.out.println(cloneUser.getFollowed() + "\n");
		
		
		
		

		System.out.println("Testing GetNeighborhood function:");
		for (int i =1;i <= 5;i++) {
			System.out.println("Getting neighbors of User " + tmpUser + ", with a depth of " + i);
			System.out.println(twitterObj.getNeighborhood(tmpUser, i));
		}

		
		System.out.println("\nTesting get followers method");
		System.out.println("Getting followers for twitter user: " + tmpUser);
		System.out.println(twitterObj.getFollowing(tmpUser));
		
		
		System.out.println("\nGetting users and their number of followers/followed to demonstrate getPopularity method. Performing in increments of 500");
		System.out.println("ID\tFollowers\tFollowed");
		for (int i=0;i <= 10000;i += 500) {
			TwitterUser t = twitterObj.getByPopularity(i);
			System.out.println(t.getUserID() + "\t" + t.followers.size() + "\t" + t.followed.size());
		}
		
		
		System.out.println("\nGetting users and their number of followers/followed to demonstrate getPopularity method. Using end of list to to demonstrate tie breaker in comparator");
		System.out.println("ID\tFollowers\tFollowed");
		int numTwitterUsers = twitterObj.twitterUsers.size() - 1;
		for (int i= numTwitterUsers - 100000;i <= numTwitterUsers;i += 10000) {
			TwitterUser t = twitterObj.getByPopularity(i);
			System.out.println(t.getUserID() + "\t" + t.followers.size() + "\t" + t.followed.size());
		}
		
		
    elapsedTime = (int) ((System.nanoTime() - startTime) / nanoInSecond);
	System.out.println("\nTotal execution time: " + elapsedTime + " seconds");	

	}

}
