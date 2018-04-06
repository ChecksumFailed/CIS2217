import java.util.ArrayList;

public class Driver {
	public static <E> void main(String[] args) {
/*
		// Test String arraylist
		System.out.println("String Test Case");
		ArrayList<String> stringArr = new ArrayList<>();
		stringArr.add("string1");
		stringArr.add("string1");
		stringArr.add("string1");
		stringArr.add("string2");
		stringArr.add("string3");
		stringArr.add("string4");

		System.out.println("Testing Arraylist of facebook user objects");
		ArrayList<FacebookUser> objArr = new ArrayList<>();
		objArr.add(new FacebookUser("a", "a"));
		objArr.add(new FacebookUser("b", "b"));
		objArr.add(new FacebookUser("c", "c"));
		objArr.add(new FacebookUser("d", "d"));
		objArr.add(new FacebookUser("e", "e"));
		objArr.add(new FacebookUser("e", "e"));
		System.out.println(objArr);
		System.out.println("Remove Duplicates");

		System.out.println(objArr);
		*/
	Integer[] intArr = {34,31,2,0,4,3,1};
	
	Heap<Integer> heap = new Heap<Integer>(intArr);
	
	}
}
