import java.util.List;
import java.util.Scanner;

import controller.DogHelper;
import controller.PetOwnerHelper;
import model.Dog;
import model.PetOwner;

/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Feb 1, 2023
*/

/**
 * Used basic skeleton StartProgram file from instructor as instructed in the
 * lab - then modified/customized program to start console version of PetList
 * Program to add Dog class objects/Entities to dogs table in MySQL pet database
 * - in future would also have other animal type tables such as cat, bird, etc.
 * Then also add tables for pet owner data, contact information, veterinarian
 * information, etc. StartPetListProgram class will get input/find/create the
 * Dog Entity/object, then pass it to the DogHelper class - helper/controller -
 * DOA (Data Access Object) which then will persist/merge Dog Entity/object to a
 * row in the dogs table in the pet database.
 */

public class StartPetListProgram {

	static Scanner in = new Scanner(System.in);
	static DogHelper dogh = new DogHelper();
	static PetOwnerHelper petownh = new PetOwnerHelper();

	/**
	 * The addADog() method - takes user inputs, changing to all lower case, then
	 * calls Dog class constructor with user's input passed in to create an
	 * instance/object/entity of Dog class, then calls DogHelper class insertPet()
	 * method to pass this Dog object/entity just created so can be added to the
	 * MySQl pet database's dogs table. example of constructor with
	 * parameters/arguments needed for Dog class object: Dog(String name, String
	 * breed, String gender, String ownerName, String primaryVet)
	 */
	private static void addADog() {
		// Get String input from user to add to table - taking all user
		// input and changing to lower case throughout program to help
		// eliminate variations of upper case/lower case/capitalization
		// by user(s) that could create issues for not finding a match
		// in database tables
		System.out.print("Enter dog's name: ");
		String dogName = (in.nextLine()).toLowerCase();
		System.out.print("Enter dog's breed: ");
		String breed = (in.nextLine()).toLowerCase();
		System.out.print("Enter if dog is female or male: ");
		String gender = (in.nextLine()).toLowerCase();
		System.out.print("Enter dog owner's name: ");
		String ownerName = (in.nextLine()).toLowerCase();
		System.out.print("Enter pet's primary veterinarian's name: ");
		String vet = (in.nextLine()).toLowerCase();
		// create an instance/object of Dog class passing in user's inputs
		Dog toAdd = new Dog(dogName, breed, gender, ownerName, vet);
		// call to DogHelper class insertPet() method passing in Dog object/Entity
		// created with user's input so it can be added to dogs table in the
		// pet database
		dogh.insertDog(toAdd);
		System.out.println("Dog added to database successfully.");
	}

	// ? consider commenting out this whole method and just use editADog() method
	// instead to select correct Dog object/Entity from pet DB dog table want
	// to edit/update/delete, then have user select which option they want
	// to do with selected object - if pick delete then also have a
	// confirmation check before deleting
	private static void deleteADog() {
		// Get input from user for dog to delete from DB table - change user input
		// to lower case to help eliminate that as issue for not finding a match
		System.out.print("Enter dog's name to delete: ");
		String dogName = (in.nextLine()).toLowerCase();
		System.out.print("Enter the own's name of the dog to delete: ");
		String ownerName = (in.nextLine()).toLowerCase();
		System.out.print("Enter dog's breed: ");
		String breed = (in.nextLine()).toLowerCase();
		System.out.print("Enter if dog is female or male: ");
		String gender = (in.nextLine()).toLowerCase();
		System.out.print("Enter pet's primary veterinarian's name: ");
		String vet = (in.nextLine()).toLowerCase();
		// create an instance/object of Dog class passing in user's inputs
		Dog toDelete = new Dog(dogName, breed, gender, ownerName, vet);
		dogh.deleteDog(toDelete);
		// Note: alternative method to delete - can use EntityManager find() method
		// to search for a specific dog entity by it's ID if known using syntax:
		// Dog found = em.find(Dog.class, 3);
		// where 3 is the id of the Dog object/entity
	}

	// ? made more sense to just search for dog to edit/delete from table by
	// pet's name, owner's name &/or ID then returning list to pick which animal to
	// edit/update/delete rather than above deleteADog() method
	// ? in future add search option for vet name so can get list of pets they take
	// care of from the pet DB tables
	/**
	 * The editADog() method called when user selects edit a pet or delete a pet
	 * from the main menu. It takes user input for how want to search for pet/dog -
	 * once verified valid int input it then uses if/else if statements to call the
	 * correct DogHelper class method after it prompts user for String with dog's or
	 * owner's name or an int for the dog's ID to use when calls the DogHelper class
	 * method to search for a List of Dog class objects/ entities that match the
	 * user's passed in input. If a list is returned then method goes on to display
	 * the list & asks user to select the ID for which one they want to edit or
	 * delete - passing in the selected id to the DogHelper class
	 * searchForDogById(int id) method which returns/displays the matching Dog
	 * object/entity, then prompts user to select option from menu about what
	 * field/attribute they want to edit or if they want to delete it - then uses
	 * that input to select which branch of if/else if/else statements to prompt
	 * user for new value for that instance of the Dog object/entity's
	 * attribute/field - which it passes to the specific Dog class setter method for
	 * that attribute/field - then the new version of the dog object is passed to
	 * the DogHelper class updateDog(Dog toEdit) method so it can be updated in the
	 * DB table or if delete is chosen then the program confirms user wishes to
	 * delete before calls the DogHelper class's deleteDog(Dog toEdit) method to
	 * remove the specified Dog object/entity from the DB table. If user enters 'n'
	 * or "no" this method then returns user to main menu. If search returns a List
	 * of Dog class objects that is empty/null then user informed no results found &
	 * method exits back to main menu.
	 */
	private static void editADog() {
		List<Dog> foundDogs = null;
		// use a while loop for input validation that an int was entered - re-prompts
		// user if input was not an int
		// ? consider taking this section out & putting it into it's own method,
		// that does the validation & returns an int?
		// declare variable to hold user's selected searchBy choice
		int searchBy;
		// Get input from user for dog to edit/delete - 1st get valid integer
		// input for method user wants to use to search table - search by dog's name,
		// owner's name or id - are 3 current options - add vet in future
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by dog's name");
		System.out.println("2 : Search by owner's name");
		System.out.println("3 : Search by ID number");
		// while loop makes sure String/non-int input was not entered by user
		// - re-prompt user if did - if correct user int input the if statement
		// is used to break out of the while loop and assign's user input to
		// searchBy variable
		while (!in.hasNextInt()) {
			String invalidInput = in.next();
			in.nextLine();
			System.out.printf("%s - is not a valid input: please enter 1, 2, or 3 %n", invalidInput);
			if (in.hasNextInt()) {
				// below line of code was an attempt to make sure only 1, 2, or 3 were accepted
				// as valid integer inputs before breaking out of
				// while loop - but didn't work - will keep
				// researching on ways to strengthening validation checks - also attempted a do
				// while loop version of above check but couldn't get it to work
				// correctly
				// if ((in.hasNextInt()) && ((searchBy == 1) || (searchBy == 2) || (searchBy ==
				// 3))) {
				break;
			}
		}
		searchBy = in.nextInt();
		in.nextLine();

		// also attempted a do while loop version of above validation check but couldn't
		// get it to work correctly - so commented it out while I continue to research
		// how to code it correctly to strengthen the validation check
//		do {
//			// Get input from user for dog to edit/delete - 1st get valid integer 
//			// input for method user wants to use to search table - search by dog's name,
//			// owner's name or ?id - are 3 options
//			System.out.println("How would you like to search? ");
//			System.out.println("1 : Search by dog's name");
//			System.out.println("2 : Search by owner's name");
//			System.out.println("3 : Search by ID number");
//			// make sure String input not entered by user - reprompt user if did
//			while (!in.hasNextInt()) {
//				String invalidInput = in.next();
//				in.nextLine();
//				System.out.printf("%s - is not a valid input: please enter 1, 2, or 3 %n", invalidInput);
//			}
//			searchBy = in.nextInt();
//			in.nextLine();
//		//} while ((searchBy != 1) || (searchBy != 2) || (searchBy != 3));
//		} while (!in.hasNextInt());
//		// int searchBy = in.nextInt();
//		// in.nextLine();

		// moved - declared and initialized List<Dog> foundDogs above - ? consider
		// moving back here so closer to code where it is used?
		// List<Dog> foundDogs;
		if (searchBy == 1) {
			System.out.print("Enter dog's name for search: ");
			String dogName = (in.nextLine()).toLowerCase();
			foundDogs = dogh.searchForDogByName(dogName);
		} else if (searchBy == 2) {
			System.out.print("Enter the dog owner's name for search: ");
			String ownerName = (in.nextLine()).toLowerCase();
			foundDogs = dogh.searchForDogByOwner(ownerName);
		} else if (searchBy == 3) {
			// changed below to add in a do while loop version for input
			// validation check that re-prompts user for input until a positive
			// int is entered by user for the dog's ID number.
			// System.out.println("Enter dog's ID number for search: ");
			// if (in.hasNextInt()) {
			// int ID = (in.nextInt());
			// foundDogs = dogh.searchForDogByID(ID);
			// in.nextLine();
			int ID;
			do {
				System.out.println("Enter dog's ID number for search: ");
				while (!in.hasNextInt()) {
					String invalidID = in.next();
					System.out.printf("%s is not a valid ID number. %n", invalidID);
				}
				ID = (in.nextInt());
				in.nextLine();
			} while (ID < 0);
			foundDogs = dogh.searchForDogByID(ID);
		}

		if (!foundDogs.isEmpty()) {
			// if item was found returned list of Dog class objects/entities is
			// printed w/ IDs - by iterating through list using an enhanced for loop
			// to call the Dog class toString() method for each object & printing it.
			System.out.println("Found Results.");
			for (Dog dog : foundDogs) {
				System.out.println(dog.getId() + " : " + dog.toString());
			}

			// get input from user re: which ID they want to edit/update/delete
			// use a do while loop to validate a positive int is entered by user
			// before breaking out of the do while loop - re-prompts user until
			// valid input is given
			int idToEdit;
			do {
				System.out.print("Which ID to edit or delete: ");
				while (!in.hasNextInt()) {
					String invalidID = in.next();
					System.out.printf("%s is not a valid ID number. %n", invalidID);
					if (in.hasNextInt()) {
						break;
					}
				}
				idToEdit = (in.nextInt());
				in.nextLine();
			} while (idToEdit < 0);
			// moved below two lines to the do while validation check above
			// System.out.print("Which ID to edit: ");
			// int idToEdit = in.nextInt();

			// search for dog by ID user entered - by calling DogHelper class's
			// searchForDogById() method & passing in the dog's ID
			Dog toEdit = dogh.searchForDogById(idToEdit);
			// print returned found dog object as output to user, then user asked to
			// select which field/instance variable attribute value they want to update
			// for this dog object/entity-
			// ? made more sense to also add in menu option to delete at this point
			// and to print out call to Dog class's toString() method so user can see
			// current field values for this dog object/entity
			System.out.println("Retrieved: " + toEdit.toString());
			// System.out.println("Retrieved " + toEdit.getName() + " from " +
			// toEdit.getOwnerName());

			// get input from user re: which menu option they picked for field to
			// edit/update or if they wanted to delete this object, then
			// use a while loop to validate an int is entered by user
			// before breaking out of the while loop - re-prompts user until
			// valid int input is given
			int valueToEdit;
			System.out
					.println("Which value do you want to edit/update or choose 6 to delete entire entry from table:  ");
			System.out.println("1 : Update dog's name");
			System.out.println("2 : Update dog's breed");
			System.out.println("3 : Update dog's gender - male or female");
			System.out.println("4 : Update dog owner's name");
			System.out.println("5 : Update dog's primary veternarian's name");
			System.out.println("6 : Delete dog from database table");
			while (!in.hasNextInt()) {
				String invalidInput2 = in.next();
				System.out.printf("%s is not input: please enter 1-6 %n", invalidInput2);
				if (in.hasNextInt()) {
					break;
				}
			}
			valueToEdit = in.nextInt();
			in.nextLine();

			// Commented out my attempt below to use a do while loop to only
			// allow int values of 1, 2, 3, 4, 5, or 6 as valid input by user
			// before breaking out of the do while loop - re-prompting user
			// until valid input is given - couldn't get it to work correctly
			// - so commented it out while I continue to research how to
			// code it correctly to strengthen the validation check
//			do {
//				System.out.print(
//						"Which value do you want to edit/update or choose 6 to delete entire entry from table:  ");
//				System.out.println("1 : Update dog's name");
//				System.out.println("2 : Update dog's breed");
//				System.out.println("3 : Update dog's gender - male or female");
//				System.out.println("4 : Update dog owner's name");
//				System.out.println("5 : Update dog's primary veternarian's name");
//				System.out.println("6 : Delete dog from database table");
//				while (!in.hasNextInt()) {
//					String invalidInput2 = in.next();
//					System.out.printf("%s is not input: please enter 1-6 %n", invalidInput2);
//				}
//				valueToEdit = in.nextInt();
//				in.nextLine();
//			} while (valueToEdit != 1 || valueToEdit != 2 || valueToEdit != 3 || valueToEdit != 4 || valueToEdit != 5
//					|| valueToEdit != 6);
			// System.out.println("1 : Update dog's name");
			// System.out.println("2 : Update dog's breed");
			// System.out.println("3 : Update dog's gender - male or female");
			// System.out.println("4 : Update dog owner's name");
			// System.out.println("5 : Update dog's primary veternarian's name");
			// System.out.println("6 : Delete dog from database table");
			// int update = in.nextInt();
			// int valueToEdit = in.nextInt();
			// in.nextLine();

			// valueToEdit integer value selected by user then used as condition
			// check in if/else if/else statements block to decide which is the
			// appropriate Dog class setter method to be called to enter new value
			// into selected field/instance variable or delete method called
			// to remove whole dog entity/object from pet DB table after user
			// confirms or goes back to main menu if user enters 'n' or "no"
			// does not want to delete it
			if (valueToEdit == 1) {
				System.out.print("New name: ");
				String newName = in.nextLine();
				toEdit.setName(newName);
				dogh.updateDog(toEdit);
				System.out.println("Pet name updated successfully.");
			} else if (valueToEdit == 2) {
				System.out.print("New breed: ");
				String newBreed = in.nextLine();
				toEdit.setBreed(newBreed);
				dogh.updateDog(toEdit);
				System.out.println("Pet breed updated successfully.");
			} else if (valueToEdit == 3) {
				System.out.print("New gender - male or female: ");
				String newGender = in.nextLine();
				toEdit.setGender(newGender);
				dogh.updateDog(toEdit);
				System.out.println("Pet's gender updated successfully.");
			} else if (valueToEdit == 4) {
				System.out.print("New owner's name: ");
				String newOwnerName = in.nextLine();
				toEdit.setOwnerName(newOwnerName);
				dogh.updateDog(toEdit);
				System.out.println("Pet owner's name updated successfully.");
			} else if (valueToEdit == 5) {
				System.out.print("New primary veternarian's name: ");
				String newVet = in.nextLine();
				toEdit.setPrimaryVet(newVet);
				dogh.updateDog(toEdit);
				System.out.println("Pet's primary veternarian updated successfully.");
			} else if (valueToEdit == 6) {
				System.out.print("Are you sure you want to delete this pet from table/database? : ");
				String confirmDelete = (in.nextLine().toLowerCase());
				if ((confirmDelete.equalsIgnoreCase("y")) || (confirmDelete.equalsIgnoreCase("yes"))) {
					// toEdit.setBreed(newBreed);
					dogh.deleteDog(toEdit);
					System.out.println("Pet deleted from database successfully.");
				} else {
					// return to main menu if user doesn't want to delete
					System.out.println("Returning to main menu.");
					runMenu();
				}
			}
			// after specific Dog attribute/field data is changed - DogHelper class's
			// updateDog() method is called passing in the updated/edited Dog object/Entity
			// which will merge & then commit the changes to the DB table
			// - moved this line of code to each if/else if statement branch except for
			// the 6 - delete option -
			// if left below line of code here the program called the DogHelper class and
			// added the Dog toEdit object/entity into/back into the pet db table after
			// deleting it
			// dogh.updateDog(toEdit);

		} else {
			System.out.println("---- No results found");
		}
	}

	public static void main(String[] args) {
		// main method that calls the StartPetListProgram class's runMenu() method
		runMenu();

	}

	/**
	 * The StartPetListProgram class's runMenu() method is called by the main method
	 * to display the main menu to the user when program starts & it is also called
	 * if user changes mind about deleting an object/entity from the pet
	 * database(db). This method creates a continuous loop with the goAgain boolean
	 * variable being set to true so the main menu displays & awaits user input
	 * until the user selects option 5 to exit the program (or any input other than
	 * 1, 2, 3, or 4) with the way the if/else if/else statement block is written -
	 * which the else branch then executes/calls the DogHelper class's cleanUp()
	 * method to close the emfactory - before output 'goodbye' message to user and
	 * changing goAgain boolean value to 'false' - added in validation check that
	 * int is entered here to strengthen resilience of program if string input
	 * entered in error by the user -
	 */
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to Main Menu of Pet list Program! ---");
		while (goAgain) {
			System.out.println("*  Select one of the following:");
			System.out.println("*  1 -- Add a pet (dog)");
			System.out.println("*  2 -- Edit a pet (dog)");
			System.out.println("*  3 -- Delete a pet (dog)");
			System.out.println("*  4 -- View the pet (dog) list");
			System.out.println("*  5 -- Exit the Pet list program");
			System.out.print("*  Your selection: ");
			// added in validation check that only int's are entered here to strengthen
			// resilience of program in case string input is entered in error by the user
			int selection;
			while (!in.hasNextInt()) {
				String invalidMenuInput = in.next();
				System.out.printf("%s is not input: please enter 1-5 %n", invalidMenuInput);
				if (in.hasNextInt()) {
					break;
				}
			}
			selection = in.nextInt();
			in.nextLine();
			// replaced below 2 lines of code w/ above while loop for input validation
			// check
			// int selection = in.nextInt();
			// in.nextLine();

			if (selection == 1) {
				addADog();
			} else if (selection == 2) {
				editADog();
			} else if (selection == 3) {
				// since changed editADog to include option for deleting change so
				// option 3 calls editADog() method rather than deleteADog() method
				// deleteADog();
				editADog();
			} else if (selection == 4) {
				viewDogList();
			} else {
				// call to cleanUp() method in ListItemHelper class
				// to close connections to DB after finished w/ application
				dogh.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}

	}

	/**
	 * Method will take List<Dog> allDogs list, then using an enhanced for loop to
	 * iterate through the list, it will call the returnDogDetails() method in Dog
	 * class for each dog in the list, then print out the dog's attribute details as
	 * specified by the method.
	 */
	private static void viewDogList() {
		// iterate through List<Dog> allDogs list & display
		// it using an enhanced for loop
		List<Dog> allDogs = dogh.showAllDogs();
		for (Dog singleDog : allDogs) {
			System.out.println(singleDog.returnDogDetails());
		}

	}
	
	private static void viewOwnerList() {
		// iterate through List<Dog> allDogs list & display
		// it using an enhanced for loop
		List<PetOwner> allOwners = petownh.showAllOwners();
		for (PetOwner singleOwner : allOwners) {
			System.out.println(singleOwner.returnOwnerDetails());
		}

	}

}
