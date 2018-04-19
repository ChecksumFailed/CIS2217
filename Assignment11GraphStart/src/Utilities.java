import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Utilities {

	public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {

		ArrayList<E> unique = new ArrayList<E>();

		for (E e: list) {
			if (!unique.contains(e)) {
				unique.add(e);
			}
		}

		return unique;
	}


	public static <E extends Comparable<E>> void insertionSort(E[] list) {
		
		for (int i=1; i<list.length; i++) {
			
			E currentElement = list[i];
			
			System.out.println("\tPass " + i + ", placing " + currentElement);
			
			int k;
			for (k=i-1; k>=0; k--) {
				
				System.out.print("\t\tComparing " + currentElement + " to " + list[k]);
				if (list[k].compareTo(currentElement) > 0) {
					System.out.print(" -- " + list[k] + " is greater, shifting " + list[k] + " to the right");
					list[k+1] = list[k];
				} else {
					System.out.println(" -- " + list[k] + " is not greater, stopping");
					break;
				}
				System.out.println();
			}
			
			System.out.println("\t" + currentElement + " goes at index " + (k+1));
			list[k+1] = currentElement;
			
			System.out.println("\t" + Arrays.toString(list));
		}
	}
	
	
	public static <E extends Comparable<E>> void quickSort(E[] list) {
		quickSort(list, 0, list.length-1, 0);
	}
	
	
	private static <E extends Comparable<E>> void quickSort(E[] list, int first, int last, int depth) {
		if (last > first) {
			int pivotIndex = partition(list, first, last);
			
			for (int i=0; i<= depth; i++) {
				System.out.print("\t");
			}
			System.out.println("The pivot is " + list[pivotIndex]);
			
			for (int i=0; i<= depth; i++) {
				System.out.print("\t");
			}
			System.out.print("After partitioning, the array is ");
			for (int i=first; i<=last; i++) {
				System.out.print(list[i] + ", ");
			} 
			System.out.println();
			
			for (int i=0; i<= depth+1; i++) {
				System.out.print("\t");
			}
			if (pivotIndex-1 <= first) {
				System.out.println("The left side is now done");
			} else {
				System.out.print("Working on the left side: ");
				for (int i=first; i<=pivotIndex-1; i++) {
					System.out.print(list[i] + ", ");
				}
				System.out.println();
			}
			quickSort(list, first, pivotIndex-1, depth+1);
			
			for (int i=0; i<= depth+1; i++) {
				System.out.print("\t");
			}
			if (last <= pivotIndex+1) {
				System.out.println("The right side is now done");
			} else {
				System.out.print("Working on the right side: ");
				for (int i=pivotIndex+1; i<=last; i++) {
					System.out.print(list[i] + ", ");
				}
				System.out.println();
			}
			quickSort(list, pivotIndex+1, last, depth+1);
		}
	}
	
	
	private static <E extends Comparable<E>> int partition(E[] list, int first, int last) {
		
//		int pivotIndex = first;
		int pivotIndex = getPivot(list, first, last);
		E pivot = list[pivotIndex];
		
		int low = first;
		int high = last;
		
		while (high > low) {
			while (low <= high && list[low].compareTo(pivot) <= 0)
				low++;
			
			while (low <= high && list[high].compareTo(pivot) > 0)
				high--;
			
			if (high > low) {
				E temp = list[high];
				list[high] = list[low];
				list[low] = temp;
				
				if (high == pivotIndex) {
					pivotIndex = low;
				}
			}
		}
		
		while (high > pivotIndex && list[high].compareTo(pivot) >= 0)
			high--;
		
		if (pivot.compareTo(list[high]) > 0) {
			list[pivotIndex] = list[high];
			list[high] = pivot;
			return high;
		} else {
			return pivotIndex;
		}
		
	}
	
	
	private static <E extends Comparable<E>> int getPivot(E[] list, int first, int last) {
		int pivotIndex = first;
		
		if (last - first >= 2) {
			if (list[first].compareTo(list[first+1]) >= 0 && list[first].compareTo(list[first+2]) >= 0) {
				// list[first] is greatest
				if (list[first+1].compareTo(list[first+2]) > 0) { // list[first+1] is the median
					pivotIndex = first+1;
				} else { // list[first+2] is the median
					pivotIndex = first+2;
				}
			} else if (list[first+1].compareTo(list[first]) >= 0 && list[first+1].compareTo(list[first+2]) >= 0) {
				// list[first+1] is greatest
				if (list[first].compareTo(list[first+2]) > 0) { // list[first] is the median
					pivotIndex = first;
				} else { // list[first+2] is the median
					pivotIndex = first+2;
				}
			} else {
				// list[first+2] is greatest
				if (list[first].compareTo(list[first+1]) > 0) { // list[first] is the median
					pivotIndex = first;
				} else { // list[first+1] is the median
					pivotIndex = first+1;
				}
			}
		}
		
		return pivotIndex;
	}


	public static void main(String[] args) {
		
		Integer[] intList = {9, 28, 333, 0, -45, 23, 28};
		String[] stringList = {"hello", "goodbye", "apple", "briefcase", "ukulele", "football", "iron"};
		FacebookUser[] userList = {new FacebookUser("george", "12345"), 
								   new FacebookUser("george", "1111"), 
								   new FacebookUser("michael", "12345"),
								   new FacebookUser("george", "12345"),
								   new FacebookUser("michael", "12345")};
		
		System.out.println(Arrays.toString(intList));
		insertionSort(intList);
		System.out.println(Arrays.toString(intList));
		
		System.out.println("\n\n");
		
		System.out.println(Arrays.toString(stringList));
		insertionSort(stringList);
		System.out.println(Arrays.toString(stringList));
		
		System.out.println("\n\n");
		
		System.out.println(Arrays.toString(userList));
		insertionSort(userList);
		System.out.println(Arrays.toString(userList));
		
		System.out.println("\n\n");
		
		Collections.shuffle(Arrays.asList(intList));
		Collections.shuffle(Arrays.asList(stringList));
		Collections.shuffle(Arrays.asList(userList));
		
		System.out.println(Arrays.toString(intList));
		quickSort(intList);
		System.out.println(Arrays.toString(intList));
		
		System.out.println("\n\n");
		
		System.out.println(Arrays.toString(stringList));
		quickSort(stringList);
		System.out.println(Arrays.toString(stringList));
		
		System.out.println("\n\n");
		
		System.out.println(Arrays.toString(userList));
		quickSort(userList);
		System.out.println(Arrays.toString(userList));
		
		System.out.println("\n\n");
		
	}
}
