;

public class Driver {

	public static void main(String[] args) {
		Twitter twitterObj = new Twitter();
		System.out.println("Loading twitter data...");
		twitterObj.loadDB();
		
	}

}
