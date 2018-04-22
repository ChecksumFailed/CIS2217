//Heap class.   Supports add/remove/getsize methods.  Can be used for efficient heap sorts.

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<E extends Comparable<E>> {
	private ArrayList<E> list = new ArrayList<>();
	private Comparator<? super E> comparator;

	// Default Constructor
	public Heap() {

	}
	
	public Heap(Comparator<? super E> comparator) {
		
	}



	
	

	// Add object to Heap
	// Loop though. If current value is greater than current value, swap.
	public void add(E newObject) {

		list.add(newObject);
		//System.out.println(this.list);
		int currentIndex = list.size() - 1;
		
		while (currentIndex > 0) {
			int parentIndex = (currentIndex - 1) / 2; // (i -1) /2; parent node
	
			if (compareObjects(list.get(currentIndex),list.get(parentIndex)) > 0) {
				E temp = list.get(currentIndex);
				list.set(currentIndex, list.get(parentIndex));
				list.set(parentIndex, temp);
			} else {
				break;
			}
			currentIndex = parentIndex;
		}
		//System.out.println(this.list);

	}
	
	//Compare two objects.   Checks if heap was initialized with comparator.  Otherwise uses comparable.
	int compareObjects(E object1, E object2) {
		if (this.comparator != null)
			return  comparator.compare(object1, object2);
		else
			return object1.compareTo(object2);
	}
	
	// Remove root element from Heap
	public E remove() {
		if (list.size() == 0)
			return null;

		E removedObject = list.get(0); // root of tree

		list.set(0, list.get(list.size() - 1)); // new root value
		list.remove(list.size() - 1); // remove the last element

		int currentIndex = 0;
		while (currentIndex < list.size()) { //adjust tree
			int leftChildIndex = 2 * currentIndex + 1; // 2i + 1, left child
			int rightChildIndex = 2 * currentIndex + 2; // 2i + 2, right child

			// find the max between two children
			if (leftChildIndex >= list.size())
				break; // the tree is a heap
			int maxIndex = leftChildIndex;
			if (rightChildIndex < list.size()) {
			//	if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0) {
				if (compareObjects(list.get(maxIndex),list.get(rightChildIndex)) < 0) {
					maxIndex = rightChildIndex;
				}
			}

			// swap if the current node is less than the maximum
			//if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
			if (compareObjects(list.get(currentIndex),list.get(maxIndex)) < 0) {
				E temp = list.get(maxIndex);
				list.set(maxIndex, list.get(currentIndex));
				list.set(currentIndex, temp);
				currentIndex = maxIndex;
			} else {
				break;
			}

		}
		return removedObject;

	}
	
	
	
	public int getSize() {
		return list.size();
	}
	
	
	
}