/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Jan 31, 2023
*/
package model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import controller.DogHelper;
import controller.PetOwnerHelper;

/**
 * CIS 175 - Spring 2023
 * Week 3 - JPA Artifact & MySQL DB Project Assessment
 * Example of a vet clinic type database/program project.
 * Program uses MySQL pet database and interacts with the Dogs table
 * to enter in each instance of Dog class object/Entity containing
 * details about that animal in a row of the table. The program
 * can interact with the attributes/instance variables of a Dog class
 * object/Entity to update/add/delete objects & their values for each
 * of its attributes.
 */
@Entity
@Table(name="dogs")
public class Dog {
	// private attributes/instance variables (fields) -
	@Id
	@GeneratedValue
	@Column(name = "DOG_ID")
	private int id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "BREED")
	private String breed;
	@Column(name = "GENDER")
	private String gender;
	// ? consider in future for age to be a calculated dynamic variable value
	// that recalculates age in years based on current date & DOB - using
	// the @Transient annotation - same with creating a variable to calculate/track 
	// whether a vaccine is due
	//@Column(name = "AGE")
	//private int age;
	@Column(name = "OWNERNAME")
	private String ownerName;
		
	@Column(name="OWNER_ID")
	private int ownerId;
	
	@Column(name="dogDOB")
	private LocalDate dogDOB;
	
	@Column(name = "PRIMARYVET")
	private String primaryVet;
	// in future add variables for:
	// ownerID to match owner data in a 1 (owner) to many pet(s) DB table relationship
	// re: which pets/animals they may own
	// date of last vet visit
	// if vaccinations up to date
	// ? maybe add specific vaccine names with date given
	// ?have yes/no boolean type attribute whether
	// animal is sterilized or not
	
	/**
	 * Default - no args constructor
	 */
	public Dog() {
		super();
		//this.ownerId = getOwnerId();
	}
	
	
	
	/**
	 * Non-Default constructor - takes all args except for dog & owner's ID 
	 * @param name
	 * @param breed
	 * @param gender
	 * @param ownerName
	 * @param primaryVet
	 */
	// commented out version containing age since will implement later
	//public Dog(String name, String breed, String gender, int age, String ownerName, String primaryVet) {
	//public Dog(String name, String breed, String gender, String ownerName, String primaryVet) {
	public Dog(String name, String breed, String gender, LocalDate dogDOB, String ownerName, String primaryVet) {
		super();
		this.name = name;
		this.breed = breed;
		this.gender = gender;
		//this.age = age;
		this.dogDOB = dogDOB;
		this.ownerName = ownerName;
		// ??? add way to get the PetOwner ownerId from PetOwner object/entity
		// ???in all constructors where it's not passed in except for Default???
		//this.ownerId = getOwnerId();
		this.primaryVet = primaryVet;
	}


	/**
	 * Created constructor to accept all args including ownerID, - (but not Dog's id) to be called
	 * by AddDogServlet when creating new dog & new owner objects/entities to added to database
	 * @param name - dog's name
	 * @param breed
	 * @param gender
	 * @param dogDOB
	 * @param ownerName
	 * @param ownerId
	 * @param primaryVet
	 * Dog dog = new Dog(dogName, breed, gender, ld, ownerName, ownerId, vetName);
	 */
	public Dog(String name, String breed, String gender, LocalDate dogDOB, String ownerName, int ownerId,
			String primaryVet) {
		super();
		this.name = name;
		this.breed = breed;
		this.gender = gender;
		//this.age = age;
		this.dogDOB = dogDOB;
		this.ownerName = ownerName;
		this.ownerId = ownerId;
		//this.ownerId = getOwnerId();
		this.primaryVet = primaryVet;
	}
	
	
	/**
	 * Non-Default constructor - takes all args (except ownerId) - may comment this out in future
	 * to protect data so primary key - id cannot be passed in since really prefer
	 * it is auto generated in DB table
	 * @param id
	 * @param name
	 * @param breed
	 * @param gender
	 * @param ownerName
	 * @param primaryVet
	 */
	// commented out version containing age since will implement later
	//public Dog(int id, String name, String breed, String gender, int age, String ownerName, String primaryVet) {
	//public Dog(int id, String name, String breed, String gender, String ownerName, String primaryVet) {
	public Dog(int id, String name, String breed, String gender, LocalDate dogDOB, String ownerName, String primaryVet) {
		super();
		this.id = id;
		this.name = name;
		this.breed = breed;
		this.gender = gender;
		//this.age = age;
		this.dogDOB = dogDOB;
		this.ownerName = ownerName;
		//this.ownerId = getOwnerId();
		this.primaryVet = primaryVet;
	}


	/**
	 * Non-Default constructor - takes all args - may comment this out in future
	 * to protect data so primary key - id cannot be passed in since really prefer
	 * it is auto generated in DB table
	 * @param id
	 * @param name
	 * @param breed
	 * @param gender
	 * @param ownerName
	 * @param primaryVet
	 */
	// commented out version containing age since will implement later
	//public Dog(int id, String name, String breed, String gender, int age, String ownerName, String primaryVet) {
	//public Dog(int id, String name, String breed, String gender, String ownerName, String primaryVet) {
	public Dog(int id, String name, String breed, String gender, LocalDate dogDOB, String ownerName, int ownerId, String primaryVet) {
		super();
		this.id = id;
		this.name = name;
		this.breed = breed;
		this.gender = gender;
		//this.age = age;
		this.dogDOB = dogDOB;
		this.ownerName = ownerName;
		this.ownerId = ownerId;
		this.primaryVet = primaryVet;
	}




	// Getters/Setters
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the breed
	 */
	public String getBreed() {
		return breed;
	}



	/**
	 * @param breed the breed to set
	 */
	public void setBreed(String breed) {
		this.breed = breed;
	}



	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}



	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	


	// removed pet age for now since makes more sense to create this as
	// a dynamic variable that recalculates/changes value depending on current date
	// and animals DOB - 
//	/**
//	 * @return the age
//	 */
//	public int getAge() {
//		return age;
//	}
//
//
//
//	/**
//	 * @param age the age to set
//	 */
//	public void setAge(int age) {
//		this.age = age;
//	}



	/**
	 * @return the dogDOB
	 */
	public LocalDate getDogDOB() {
		return dogDOB;
	}



	/**
	 * @param dogDOB the dogDOB to set
	 */
	public void setDogDOB(LocalDate dogDOB) {
		this.dogDOB = dogDOB;
	}



	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}



	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	

	/**
	 * @return the ownerId
	 */
	public int getOwnerId() {
		return ownerId;
	}


	/**
	 * ???since trying to only add new Dog and PetOwner object/entities after
	 * ???PetOwner created so have ownerId from that - so in theory will always 
	 * ???only be adding a new dog to an existing owner???
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(int ownerId) {
	//public void setOwnerId(String OwnerName) {	
		this.ownerId = ownerId;
	}

	// ???commented out on this version of setOwnerId since trying to only 
	// ???add new dog and petowner object/entities together after petowner created
	// ???so have ownerId from that - so always only adding a new dog to an existing owner
//	/**
//	 * @param ownerId the ownerId to set
//	 */
//	//public void setOwnerId(int ownerId) {
//	public void setOwnerId(String OwnerName) {
//		PetOwnerHelper ownerDao = new PetOwnerHelper();
//		//PetOwner owner = ownerDao.toString();
//		List<PetOwner> owners = ownerDao.searchForOwnerByName(ownerName);
//		//PetOwner owner = ownerDao.searchForOwnerByName(ownerName);
//		//DogHelper dao = new DogHelper();
//		//List<Dog> allDogs = dao.showAllDogs();
//		//showAllOwners()
//		//List<PetOwner> allOwners = petownh.showAllOwners();
//		for (PetOwner singleOwner : owners) {
//			System.out.println(singleOwner.returnOwnerDetails());
//			int selectedId = singleOwner.getOwnerId();
//			this.ownerId = selectedId;
//			}	
//		//this.ownerId = PetOwner.getOwnerId();
//	}



	/**
	 * @return the primaryVet
	 */
	public String getPrimaryVet() {
		return primaryVet;
	}



	/**
	 * @param primaryVet the primaryVet to set
	 */
	public void setPrimaryVet(String primaryVet) {
		this.primaryVet = primaryVet;
	}


//	@Override
//	public String toString() {
//		return "Dog [id=" + id + ", name=" + name + ", breed=" + breed + ", gender=" + gender + ", ownerName="
//				+ ownerName + ", ownerId=" + ownerId + ", primaryVet=" + primaryVet + "]";
//	}
	
	
		
	public String returnDogDetails() {
		// commented out version containing age since will implement later
		//return this.name + " is a " + this.age + " year old " + this.gender + " " + this.breed + " owned by: " + this.ownerName;
		return this.name + " is a " + this.gender + " " + this.breed + " owned by: " + this.ownerName;
	}



	@Override
	public String toString() {
		return "Dog [id=" + id + ", name=" + name + ", breed=" + breed + ", gender=" + gender + ", ownerName="
				+ ownerName + ", ownerId=" + ownerId + ", dogDOB=" + dogDOB + ", primaryVet=" + primaryVet + "]";
	}



	public String formatDogInformation() {
		String formatter = "Dog's ID: %d; Dog's Name: %s; Breed: %s; Gender: %s; Owner's Name: %s; Owner's ID: %d; Primary Vet: %s";
		return String.format(formatter, id, name, breed, gender, ownerName, primaryVet);
	}
	

}
