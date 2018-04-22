

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JOptionPane;

import javax.swing.JFileChooser;


public class Utilities<E extends Comparable<E>> {

	public final Comparator<String> BY_STRING_IGNORECASE = new string_IgnoreCase();
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

		// add elements to heap
		for (int i = 0; i < list.length; i++) {
			heap.add(list[i]);
		}

		// remove elements from heap
		for (int i = list.length - 1; i >= 0; i--) {
			list[i] = heap.remove();
		}

	}

	public <E extends Comparable<E>> void heapSort(E[] list, Comparator<? super E> comparator) {
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

	// Prompt user to choose file and return file path. Throws exception if user
	// cancels
	public static File getFile() throws IOException {

		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		} else {
			throw new IOException("Cancelled, exiting program");
		}

	}

	// Message PopUp
	public static void showMessage(String msg, String title) {
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);

	}

	// Message PopUp
	public static void showError(String msg, String title) {
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);

	}

}
