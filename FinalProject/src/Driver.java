import java.io.IOException;
//
import java.util.ArrayList;
;

public class Driver {

	public static void main(String[] args) throws CloneNotSupportedException {
		Twitter twitterObj = new Twitter();
		System.out.println("Loading twitter data(Please be patient)...");

		 Long startTime = System.nanoTime(); 
		try {
		
			twitterObj.loadDB();
			
	       	        
		} catch (IOException|RuntimeException e) {
			Utilities.showError( e.getMessage().toString(),"ERROR!!!");
			return;
		}
		 Long endTime = System.nanoTime();
	     int elapsedTime = (int) ((endTime - startTime) / 1000000000);
	     
		int testUser = 0;
		
		System.out.println("Loaded " + twitterObj.getList().size() + " Unique users, their associated followed users, and followers from flat file in " + elapsedTime + " seconds\n" );
		TwitterUser tmpUser = twitterObj.binarySearch(testUser);
		System.out.println("Testing Clone of User " + testUser + "");
		TwitterUser cloneUser = tmpUser.clone();
		
		System.out.println("User " + testUser + " follows: " );
		System.out.println(tmpUser.getFollowed() + "\n");
		
		System.out.println("Cloned user follows: " );
		System.out.println(cloneUser.getFollowed() + "\n");
		
		System.out.println("Clearing all followed users of CLoned User\n");
		cloneUser.followed.clear();
		
		System.out.println("User " + testUser + " follows: " );
		System.out.println(tmpUser.getFollowed() + "\n");
		
		System.out.println("Cloned user follows: " );
		System.out.println(cloneUser.getFollowed() + "\n");
		
		
		
		

		System.out.println("Testing GetNeighborhood function:");
		for (int i =1;i <= 5;i++) {
			System.out.println("Getting neighbors of User " + testUser + ", with a depth of " + i);
			System.out.println(twitterObj.getNeighborhood(testUser, i));
		}

		

		

	}

}
