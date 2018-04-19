import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class Driver {
	
	public static void main(String[] args) throws 
		FileNotFoundException, IOException, ClassNotFoundException {
		
		Scanner input = new Scanner(System.in);
		int choice = 0;
		Facebook facebook = null;
		GraphViewer graph = null;
		
		// try to deserialize
		File file = new File("facebook.dat");
		
		if (file.exists()) {
			ObjectInputStream deSerialize = new ObjectInputStream(
					new FileInputStream("facebook.dat"));
			facebook = (Facebook) deSerialize.readObject();
		} else {
			facebook = new Facebook();
		}
		
		do {
			
			System.out.println();
			System.out.println("1. List users alphabetically"); 
			System.out.println("2. List users by number of friends"); 
			System.out.println("3. Add a user");
			System.out.println("4. Delete a user");
			System.out.println("5. Get password hint");
			System.out.println("6. Add a friend");
			System.out.println("7. Remove a friend");
			System.out.println("8. List friends");
			System.out.println("9. Recommend friends");
			System.out.println("10.Like something");
			System.out.println("11.Display likes");
			System.out.println("12.Undo");
			System.out.println("13.Display user graph"); // added
			System.out.println("14.Quit");
			System.out.println();
			System.out.print("What would you like to do? ");
			
			choice = input.nextInt();
			
			switch (choice) {
			case 1: facebook.listUsersAlphabetically(); break;
			case 2: facebook.listUsersByFriends(); break;
			case 3: facebook.addUser(); break;
			case 4: facebook.deleteUser(); break;
			case 5: facebook.getPasswordHint(); break;
			case 6: facebook.addFriend(); break;
			case 7: facebook.removeFriend(); break;
			case 8: facebook.listFriends(); break;
			case 9: facebook.recommendFriends(); break;
			case 10:facebook.likeSomething(); break;
			case 11:facebook.listLikes(); break;
			case 12:facebook.undo(); break;
			case 13:graph = facebook.display(); break; // added
			}
			
			if (choice == 14) break;

		} while (choice != 14);
		
		// serialize
		ObjectOutputStream serialize = new ObjectOutputStream(
				new FileOutputStream("facebook.dat"));
		serialize.writeObject(facebook);
		serialize.close();
		
		if (graph != null) {
			graph.dispose();
		}
	}

}
