import java.util.ArrayList;

public class Heap <E extends Comparable<E>>{
	private ArrayList<E> list = new ArrayList<>();
	
	public Heap() {
		
	}
	
	public Heap(E[] objects) {
		for (int i = 0; i <  objects.length; i++) {
			add(objects[i]);
		}
	}
	
	public void add(E newObject) {
		list.add(newObject);
		int currentIndex = list.size() -1 ;
		
		while (currentIndex > 0 ) {
			int parentIndex = (currentIndex - 1 ) / 2;
			
			if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0 ) {
				E temp = list.get(currentIndex);
				list.set(currentIndex, list.get(parentIndex));
				list.set(parentIndex,  temp);
			}else {
				break;
			}
			currentIndex = parentIndex;
		}
		
	}

}

public E remove() {
	
}
