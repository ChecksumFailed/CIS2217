import java.util.ArrayList;
import java.util.Collections;

public class Utilities<E extends Comparable<E>> {
	
	private ArrayList<E> items = new ArrayList<>();
	
	// The name of the constructor is still the name of the class -- without
	// the parameterization
	
	public Utilities() {
		
	}
	
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
	
	public void add(E newItem) {
		items.add(newItem);
		Collections.sort(items);
	}

	
	public void remove(E removeItem) {
		items.remove(removeItem);
	}
	
}
