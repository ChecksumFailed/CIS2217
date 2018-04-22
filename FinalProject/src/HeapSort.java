import java.util.Comparator;
import java.util.List;

public class HeapSort {
	//list heap sort using comparable. 
	public static <E extends Comparable<E>> void fromArray(E[] list) {
		Heap<E> heap = new Heap<>();

		// add elements to heap
		for (int i = 0; i < list.length; i++) {
			heap.add(list[i]);
		}

		// remove elements from heap
		for (int i = list.length - 1; i >= 0; i--) {
			list[i] = heap.remove();
		}

	}
	
	//list heap sort using comparable. 
	public static <E extends Comparable<E>> void fromList(List<E> list) {
		Heap<E> heap = new Heap<>();

		// add elements to heap
		for (E i: list) {
			heap.add(i);
		}

		// remove elements from heap
		for (int i = list.size() - 1; i >= 0; i--) {
			list.set(i, heap.remove());
		}

	}
	
	//list heap sort using comparable. 
	public static <E extends Comparable<E>> void fromList(List<E> list, Comparator<? super E> comparator) {
		Heap<E> heap = new Heap<E>(comparator);

		// add elements to heap
		for (E i: list) {
			heap.add(i);
		}

		// remove elements from heap
		for (int i = list.size() - 1; i >= 0; i--) {
			list.set(i, heap.remove());
		}

	}



	//list heap sort using comparator.
	public static <E extends Comparable<E>>  void fromArray(E[] list, Comparator<? super E> comparator) {
		Heap<E> heap = new Heap<E>(comparator);

		// add elements to heap
		for (int i = 0; i < list.length; i++) {
			heap.add(list[i]);
		}

		// remove elements from heap
		for (int i = list.length - 1; i >= 0; i--) {
			list[i] = heap.remove();
		}
	}
}
