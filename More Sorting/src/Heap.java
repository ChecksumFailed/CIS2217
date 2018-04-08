import java.util.ArrayList;

public class Heap<E extends Comparable<E>> {
	private ArrayList<E> list = new ArrayList<>();

	// Default Constructor
	public Heap() {

	}

	// Array of objects
	public Heap(E[] objects) {
		System.out.println(objects);
		for (int i = 0; i < objects.length; i++) {
			add(objects[i]);
		}
	}

	// ArrayList of objects
	public Heap(ArrayList<E> objects) {
		for (E object : objects) {
			add(object);

		}
	}

	// Add object to Heap
	// Loop though. If current value is greater than current value, swap.
	public void add(E newObject) {

		list.add(newObject);
		System.out.println(this.list);
		int currentIndex = list.size() - 1;

		while (currentIndex > 0) {
			int parentIndex = (currentIndex - 1) / 2; // (i -1) /2; parent node

			if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
				E temp = list.get(currentIndex);
				list.set(currentIndex, list.get(parentIndex));
				list.set(parentIndex, temp);
			} else {
				break;
			}
			currentIndex = parentIndex;
		}
		System.out.println(this.list);

	}

	// Remove root element from Heap
	public E remove() {
		if (list.size() == 0)
			return null;

		E removedObject = list.get(0); // root of tree

		list.set(0, list.get(list.size() - 1)); // new root value
		list.remove(list.size() - 1); // remove teh last element

		int currentIndex = 0;
		while (currentIndex < list.size()) { //adjust tree
			int leftChildIndex = 2 * currentIndex + 1; // 2i + 1, left child
			int rightChildIndex = 2 * currentIndex + 2; // 2i + 2, right child

			// find the max between two children
			if (leftChildIndex >= list.size())
				break; // the tree is a heap
			int maxIndex = leftChildIndex;
			if (rightChildIndex < list.size()) {
				if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0) {
					maxIndex = rightChildIndex;
				}
			}

			// swap if the current node is less than the maximum
			if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
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