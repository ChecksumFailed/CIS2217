import java.util.ArrayList;
import java.util.Comparator;

public class Utilities<E extends Comparable<E>> {

	public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
		if (list.size() == 0)
			return list;

		ArrayList<E> tmpList = new ArrayList<>();
		for (E i : list) {
			if (!tmpList.contains(i))
				tmpList.add(i);
		}
		return tmpList;

	}

	public static <E extends Comparable<E>> int linearSearch(E[] list, E key) {
		return 0;

	}

	public static <E extends Comparable<E>> void heapSort(E[] list) {
		Heap<E> heap = new Heap<>();
		
		//add elements to heap
		for (int i = 0; i < list.length;i++) {
			heap.add(list[i]);
		}
		
		//remove elements from heap
		for (int i = list.length -1; i >= 0; i--) {
			list[i] = heap.remove();
		}
		
	}

	public static <E> void heapSort(E[] list, Comparator<? super E> comparator) {
		Heap<E> heap = new Heap<>(comparator);
		
		//add elements to heap
		for (int i = 0; i < list.length;i++) {
			heap.add(list[i]);
		}
		
		//remove elements from heap
		for (int i = list.length -1; i >= 0; i--) {
			list[i] = heap.remove();
		}
	}

//test
}
