import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import javax.swing.JFileChooser;
//asdf
public class Utilities<E extends Comparable<E>> {

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

	// Remove Duplicates from ArrayList
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

	// Basic Linear Search of list
	public static <E extends Comparable<E>> int linearSearch(E[] list, E key) {
		return 0;

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
