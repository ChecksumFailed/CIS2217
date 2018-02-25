import java.util.Comparator;

public class facebookFriendsComparator implements Comparator<FacebookUser>, java.io.Serializable {

	//sort by number of friends
	private static final long serialVersionUID = 4812197106735010291L;

	@Override
	public int compare(FacebookUser f1, FacebookUser f2) {
		return f1.friends.size() - f2.friends.size();
	}

}
