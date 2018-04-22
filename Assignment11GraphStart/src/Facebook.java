import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;


public class Facebook implements Serializable {

	private static final long serialVersionUID = -4435754123241896132L;

	private ArrayList<FacebookUser> users;
	private TreeSet<Like> likes;
	private Stack<Action> actions;

	public Facebook() {
		users = new ArrayList<>();
		likes = new TreeSet<Like>();
		actions = new Stack<Action>();
	}


	public void likeSomething() {

		Scanner input = new Scanner(System.in);
		System.out.print("What is the username of the user adding the like? ");
		String name = input.next();

		FacebookUser theUser = null;
		for (FacebookUser u: users) {
			if (u.toString().equals(name)) {
				theUser = u;
				break;
			}
		}

		if (theUser == null) {
			System.out.println(name + " could not be found.");

		} else {

			System.out.print("What is that user's password? ");
			String password = input.next();

			if (!theUser.checkPassword(password)) {
				System.out.println("The password was incorrect.");

			} else {

				System.out.print("What is the thing to be liked? ");
				String likeThis = input.next();

				Like newLike = new Like(likeThis);
				
				if (likes.contains(newLike)) {
					if (likes.floor(newLike).likedBy(theUser)) {
						System.out.println(theUser + " now likes " + likeThis);
						actions.add(new Action("likeSomething", theUser, newLike));
					} else {
						System.out.println(theUser + " already likes " + likeThis);
					}
				} else {
					newLike.likedBy(theUser);
					likes.add(newLike);
					actions.add(new Action("likeSomething", theUser, newLike));
					System.out.println(theUser + " now likes " + likeThis);
				}
			}
		}
	}

	public void listLikes() {
		for (Like like: likes) {
			System.out.println(like);
		}
	}

	public void addFriend() {

		Scanner input = new Scanner(System.in);
		System.out.print("What is the username of the user adding the friend? ");
		String name = input.next();

		FacebookUser theUser = null;
		for (FacebookUser u: users) {
			if (u.toString().equals(name)) {
				theUser = u;
				break;
			}
		}

		if (theUser == null) {
			System.out.println(name + " could not be found.");

		} else {

			System.out.print("What is that user's password? ");
			String password = input.next();

			if (!theUser.checkPassword(password)) {
				System.out.println("The password was incorrect.");

			} else {

				System.out.print("What is the username of the friend to be added? ");
				String friendName = input.next();

				FacebookUser theFriend = null;
				for (FacebookUser u: users) {
					if (u.toString().equals(friendName)) {
						theFriend = u;
						break;
					}
				}

				if (theFriend == null) {
					System.out.println(friendName + " could not be found.");
				} else {
					actions.add(new Action("addFriend", theUser, theFriend));
					theUser.friend(theFriend);
				}
			}
		}
	}


	public void removeFriend() {

		Scanner input = new Scanner(System.in);
		System.out.print("What is the username of the user adding the friend? ");
		String name = input.next();

		FacebookUser theUser = null;
		for (FacebookUser u: users) {
			if (u.toString().equals(name)) {
				theUser = u;
				break;
			}
		}

		if (theUser == null) {
			System.out.println(name + " could not be found.");

		} else {

			System.out.print("What is that user's password? ");
			String password = input.next();

			if (!theUser.checkPassword(password)) {
				System.out.println("The password was incorrect.");

			} else {

				System.out.print("What is the username of the friend to be removed? ");
				String friendName = input.next();

				FacebookUser theFriend = null;
				for (FacebookUser u: users) {
					if (u.toString().equals(friendName)) {
						theFriend = u;
						break;
					}
				}

				if (theFriend == null) {
					System.out.println(friendName + " could not be found.");
				} else {
					theUser.defriend(theFriend);
					actions.add(new Action("removeFriend", theUser, theFriend));
				}
			}
		}
	}


	public void listFriends() {

		Scanner input = new Scanner(System.in);
		System.out.print("What is the username? ");
		String name = input.next();

		FacebookUser theUser = null;
		for (FacebookUser u: users) {
			if (u.toString().equals(name)) {
				theUser = u;
				break;
			}
		}

		if (theUser == null) {
			System.out.println(name + " could not be found.");

		} else {

			System.out.print("What is that user's password? ");
			String password = input.next();

			if (!theUser.checkPassword(password)) {
				System.out.println("The password was incorrect.");
			} else {
				System.out.println(theUser.getFriends());
			}
		}
	}


	public void recommendFriends() {

		Scanner input = new Scanner(System.in);
		System.out.print("What is the username? ");
		String name = input.next();

		FacebookUser theUser = null;
		for (FacebookUser u: users) {
			if (u.toString().equals(name)) {
				theUser = u;
				break;
			}
		}

		if (theUser == null) {
			System.out.println(name + " could not be found.");

		} else {

			System.out.print("What is that user's password? ");
			String password = input.next();

			if (!theUser.checkPassword(password)) {
				System.out.println("The password was incorrect.");
			} else {
				ArrayList<FacebookUser> recs = getRecommendations(theUser);
				Collections.sort(recs, new FriendsComparator());
				System.out.println(recs);
			}
		}
	}

	public ArrayList<FacebookUser> getRecommendations(FacebookUser user) {

		ArrayList<FacebookUser> recommendations = new ArrayList<>();

		for (FacebookUser u: user.getFriends()) {
			if (!recommendations.contains(u)) {
				recommendations.add(u);
			}

			ArrayList<FacebookUser> others = u.getFriends();
			for (FacebookUser o: others) {
				if (!recommendations.contains(o)) {
					recommendations.add(o);
				}
			}
		}

		return recommendations;
	}


	public void addUser() {
		Scanner input = new Scanner(System.in);
		System.out.print("What is the username? ");
		String name = input.nextLine();

		FacebookUser newUser = null;

		for (FacebookUser u: users) {
			if (u.toString().equals(name)) {
				newUser = u;
				break;
			}
		}

		if (newUser != null) {
			System.out.println("That user already exists.");
		} else {
			System.out.print("What is the password? ");
			String password = input.nextLine();

			newUser = new FacebookUser(name, password);

			System.out.print("What is the password hint? ");
			String hint = input.nextLine();

			newUser.setPasswordHint(hint);

			users.add(newUser);
		}
	}

	public void deleteUser() {
		Scanner input = new Scanner(System.in);
		System.out.print("What is the username? ");
		String name = input.nextLine();

		FacebookUser newUser = null;

		for (FacebookUser u: users) {
			if (u.toString().equals(name)) {
				newUser = u;
				break;
			}
		}

		if (newUser == null) {
			System.out.println("That user could not be found.");
		} else {
			System.out.print("What is the password? ");
			String password = input.nextLine();

			if (newUser.checkPassword(password)) {
				users.remove(newUser);
			} else {
				System.out.println("Incorrect password, the user will not be deleted.");
			}
		}
	}

	public void listUsersAlphabetically() {
		Collections.sort(users);
		for (FacebookUser user: users) {
			System.out.println(user);
		}
	}

	public void listUsersByFriends() {
		Collections.sort(users, new FriendsComparator());
		for (FacebookUser user: users) {
			System.out.println(user);
		}
	}

	public void getPasswordHint() {
		Scanner input = new Scanner(System.in);
		System.out.print("What is the username of the user? ");
		String name = input.nextLine();

		FacebookUser user = null;

		for (FacebookUser u: users) {
			if (u.toString().equals(name)) {
				user = u;
				break;
			}
		}

		if (user != null) {
			user.getPasswordHelp();
		} else {
			System.out.println("The user could not be found.");
		}
	}
	
	public void undo() {
		if (actions.isEmpty()) {
			System.out.println("There is nothing to undo.");
			return;
		}
		
		Action action = actions.peek();
		if (action.undo()) {
			actions.pop();
		}
	}
	
	// Function to retrieve integer input. Performs input validation to only allow
	// int
	static int getInt(String msg, int lowRange) {
		boolean isInt = false;
		Scanner scannerObj =  new Scanner(System.in);
		int tmpInt = 0;
		while (!isInt) {
			try {
				System.out.println(msg);
				tmpInt = scannerObj.nextInt();
				if (tmpInt >= lowRange) 
					isInt = true;
				else
					System.out.println("Please input a number greater than or equal to: " + lowRange);
			} catch (InputMismatchException ex) {
				System.out.println("Please Enter integers Only");
				scannerObj.next();
			}

		}
		return tmpInt;

	}
	
	// added
	public GraphViewer display() {
		Scanner input = new Scanner(System.in);
		System.out.print("What is the username of the user? ");
		String name = input.nextLine();

		FacebookUser user = null;

		for (FacebookUser u: users) {
			if (u.toString().equalsIgnoreCase(name)) {
				user = u;
				break;
			}
		}

		if (user != null) {
			System.out.print("What is the password? ");
			String password = input.nextLine();

			if (user.checkPassword(password)) {
				
				int maxDepth = getInt("Enter the maximum number of levels to display",1);
				System.out.println("Displaying graph for " + user.toString());
				return new GraphViewer(user,maxDepth);
			} else {
				System.out.println("Incorrect password.");
				return null;
			}

		} else {
			System.out.println("The user could not be found.");
			return null;
		}
	}
}
