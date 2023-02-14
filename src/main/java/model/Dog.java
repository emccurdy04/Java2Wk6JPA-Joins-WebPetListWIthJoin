/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Jan 31, 2023
*/
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@Column(name = "ID")
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
	}
	
	
	
	/**
	 * Non-Default constructor - takes all args except for ID 
	 * @param name
	 * @param breed
	 * @param gender
	 * @param ownerName
	 * @param primaryVet
	 */
	// commented out version containing age since will implement later
	//public Dog(String name, String breed, String gender, int age, String ownerName, String primaryVet) {
	public Dog(String name, String breed, String gender, String ownerName, String primaryVet) {
		super();
		this.name = name;
		this.breed = breed;
		this.gender = gender;
		//this.age = age;
		this.ownerName = ownerName;
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
	public Dog(int id, String name, String breed, String gender, String ownerName, String primaryVet) {
		super();
		this.id = id;
		this.name = name;
		this.breed = breed;
		this.gender = gender;
		//this.age = age;
		this.ownerName = ownerName;
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


	// Helper methods - 
	@Override
	public String toString() {
		// commented out version containing age since will implement later
		//return "Dog [id=" + id + ", name=" + name + ", breed=" + breed + ", gender=" + gender + ", age=" + age + ", ownerName=" + ownerName + ", primaryVet=" + primaryVet + "]";
		return "Dog [id=" + id + ", name=" + name + ", breed=" + breed + ", gender=" + gender + ", ownerName=" + ownerName + ", primaryVet=" + primaryVet + "]";
	}
	
	public String returnDogDetails() {
		// commented out version containing age since will implement later
		//return this.name + " is a " + this.age + " year old " + this.gender + " " + this.breed + " owned by: " + this.ownerName;
		return this.name + " is a " + this.gender + " " + this.breed + " owned by: " + this.ownerName;
	}
	

}
