import java.util.Comparator;


public class FriendsComparator implements Comparator<FacebookUser> {

	@Override
	public int compare(FacebookUser o1, FacebookUser o2) {
		return o2.getFriends().size() - o1.getFriends().size();
	}

}
