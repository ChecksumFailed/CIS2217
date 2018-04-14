import java.util.ArrayList;
import java.util.Comparator;



public class Utilities<E extends Comparable<E>> {
	
	public  final Comparator<String> BY_STRING_IGNORECASE = new string_IgnoreCase();
	public final Comparator<Integer> BY_INTEGER_REVERSE = new int_Reverse();

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



	public <E extends Comparable<E>> void heapSort(E[] list) {
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
 
	public <E> void heapSort(E[] list, Comparator<? super E> comparator) {
		Heap2<E> heap = new Heap2<>(comparator);
		
		//add elements to heap
		for (int i = 0; i < list.length;i++) {
			heap.add(list[i]);
		}
		
		//remove elements from heap
		for (int i = list.length -1; i >= 0; i--) {
			list[i] = heap.remove();
		}
	}
	
	private static class int_Reverse implements Comparator<Integer> {

		@Override
		public int compare(Integer int1, Integer int2) {
			
			return int2.compareTo(int1);
		}
		
	}
	
	private static class string_IgnoreCase implements Comparator<String> {
		@Override
		
		public int compare(String string1, String string2) {
			
			return string1.compareToIgnoreCase(string2);
		}



		
	}
	

//test
}
