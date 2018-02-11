import java.util.ArrayList;


public class Utilities<E extends Comparable<E>> {
	

	public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
		if (list.size() == 0)
			return list;
		
		ArrayList<E> tmpList = new ArrayList<>();
		for (E i: list) {
			if (!tmpList.contains(i))
				tmpList.add(i);
		}
		return tmpList;
		
	}
	
	public static <E extends Comparable<E>>
	int linearSearch(E[] list, E key) {
		return 0;
		
	}
	
	
	public static void main(String[] args) {
		
		//Test String arraylist
		System.out.println("String Test Case");
		ArrayList<String> stringArr = new ArrayList<>();
		stringArr.add("string1");
		stringArr.add("string1");
		stringArr.add("string1");
		stringArr.add("string2");
		stringArr.add("string3");
		stringArr.add("string4");
		
		System.out.println(stringArr);
		System.out.println("Remove Duplicates");
		stringArr = removeDuplicates(stringArr);
		System.out.println(stringArr);
		
		//Test arraylist with all values the same
		System.out.println("Testing arr with 100% duplicates");
		stringArr.clear();
		stringArr.add("string1");
		stringArr.add("string1");
		stringArr.add("string1");
		stringArr.add("string1");
		System.out.println(stringArr);
		System.out.println("Remove Duplicates");
		stringArr = removeDuplicates(stringArr);
		System.out.println(stringArr);
		
		System.out.println("Testing empty arraylist");
		stringArr.clear();
		stringArr = removeDuplicates(stringArr);
		System.out.println(stringArr);
		
		System.out.println("Testing Arraylist of facebook user objects");
		ArrayList<FacebookUser> objArr = new ArrayList<>();
		objArr.add(new FacebookUser("a","a"));
		objArr.add(new FacebookUser("b","b"));
		objArr.add(new FacebookUser("c","c"));
		objArr.add(new FacebookUser("d","d"));
		objArr.add(new FacebookUser("e","e"));
		objArr.add(new FacebookUser("e","e"));
		System.out.println(objArr);
		System.out.println("Remove Duplicates");
		objArr = removeDuplicates(objArr);
		System.out.println(objArr);
		
		
		
		
		
		
		

	}
	
	
}
