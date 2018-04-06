import java.util.ArrayList;

public class Heap <E extends Comparable<E>>{
	private ArrayList<E> list = new ArrayList<>();
	
	//Default Constructor
	public Heap() {
		
	}
	
	
	//Array of objects
	public Heap(E[] objects) {
		System.out.println(objects);
		for (int i = 0; i <  objects.length; i++) {
			add(objects[i]);
		}
	}
	
	
	//ArrayList of objects
	public Heap(ArrayList<E> objects) {
		for (E object : objects) {
			add(object);
			
		}
	}
	
	
	//5,2,3,1
	//Add object to Heap
	// Loop though. If current value is greater than current value, swap.
	public void add(E newObject) {
		
		list.add(newObject);
		System.out.println(this.list);
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
		System.out.println(this.list);
		
	}

//Remove Object from Heap
//public E remove() {
	
//}
}