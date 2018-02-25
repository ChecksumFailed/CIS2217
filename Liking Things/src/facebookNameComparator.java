import java.util.Comparator;

public class facebookNameComparator implements Comparator<FacebookUser>, java.io.Serializable {
	// Sort by username, case insensitive

	private static final long serialVersionUID = 8570046615114389092L;

	@Override
	public int compare(FacebookUser f1, FacebookUser f2) {
		return f1.getUsername().compareToIgnoreCase(f2.getUsername());
	}

}