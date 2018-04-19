import java.io.Serializable;
import java.util.HashSet;


public class Like implements Comparable<Like>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String theThing;
	private HashSet<FacebookUser> likers;
	
	public Like(String theThing) {
		this.theThing = theThing;
		likers = new HashSet<>();
	}
	
	public boolean likedBy(FacebookUser user) {
		int oldSize = likers.size();
		likers.add(user);
		return likers.size() != oldSize;
	}
	
	public int getNumber() {
		return likers.size();
	}
	
	public String getTheThing() {
		return theThing;
	}
	
	public String toString() {
		return theThing + "\t" + (likers.size() + ": " + likers);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((theThing == null) ? 0 : theThing.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Like other = (Like) obj;
		if (theThing == null) {
			if (other.theThing != null)
				return false;
		} else if (!theThing.equals(other.theThing))
			return false;
		return true;
	}

	@Override
	public int compareTo(Like o) {
		return this.getTheThing().compareTo(o.getTheThing());
	}
}
