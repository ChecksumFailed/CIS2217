import java.util.ArrayList;


public class Utilities<E extends Comparable<E>> {
	

	public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
		if (list.size() == 0)
			return list;
		
		ArrayList<E> tmpList = new ArrayList<>();
		for (E i: list) {
			if (!tmpList.contains(i))
				tmpList.add(i);
		}
		return tmpList;
		
	}
	
	public static <E extends Comparable<E>>
	int linearSearch(E[] list, E key) {
		return 0;
		
	}
	
	
	
	
	
}
