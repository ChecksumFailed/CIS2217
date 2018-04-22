import java.io.Serializable;
import java.util.Comparator;

public class twitterIDComparator implements Comparator<TwitterUser>, Serializable{


	private static final long serialVersionUID = 790808028994531101L;

	@Override
	public int compare(TwitterUser o1, TwitterUser o2) {
		// TODO Auto-generated method stub
		return o1.getUserID() - o2.getUserID();
	}



}
