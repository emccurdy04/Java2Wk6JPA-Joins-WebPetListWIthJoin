/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Feb 21, 2023
*/
package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import controller.DogHelper;
import controller.PetOwnerHelper;

/**
 * Owner class blue print for creating pet owner
 * with possible One-to-Many join relationship to dog class
 * since one owner can have multiple pets
 */
@Entity
//@Table(name="petowner")
@Table(name="PETOWNER")
public class PetOwner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OWNER_ID")
	private int ownerId;
	
	// one owner can have multiple pets
	//@ManyToOne(cascade=CascadeType.PERSIST)
	//private int petId;
	//@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER, targetEntity=Dog.class)
	
	// ???changed cascadeType from Persist to Merge when getting errors of  
	//@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinTable(name="OWNER_DOG_IDS", joinColumns= @JoinColumn(name="OWNER_ID"), inverseJoinColumns = @JoinColumn(name="DOG_ID"))
	//@JoinColumn(name="OWNER_ID", referencedColumnName="ID")
	//@JoinColumn(name="OWNER_ID", referencedColumnName = @JoinColumn(name="ID")
	//private ArrayList<AllPets> listOfPets;
	//private List<Dog> listOfPets;
	//@Column(name="LIST_OF_PETS")
	private ArrayList<Dog> listOfPets = new ArrayList<>();
	
	//private String firstName;
	//private String lastName;
	// create displayOwnerName method instead
	//private String fullName;
	// ?? getting error when inserting into petowner table that "unknown column 'OWNERNAME' 
	// in 'field list'" so tried switching column name to same as string variable name
	@Column(name="OWNERNAME")
	//@Column(name="ownerName")
	private String ownerName;
	
	//commented out - may do later but trying to make assignment too complex
	//private String streetAddress;
	//private String streetAddress2;
	//private String city;
	//private String state;
	//private String zipcode;
	//private String phoneNumber;
	
	// Constructors
	/**
	 * Default - no args constructor
	 */
	public PetOwner() {
		super();
	}
	
	
	/**
	 * Non-Default - constructor takes all args except for ownerId
	 * @param listOfPets
	 * @param firstName
	 * @param lastName
	 */
	//public PetOwner(ArrayList<Dog> listOfPets, String firstName, String lastName) {
	public PetOwner(ArrayList<Dog> listOfPets, String ownerName) {
		super();
		this.listOfPets = listOfPets;
		//this.firstName = firstName;
		//this.lastName = lastName;
		this.ownerName = ownerName;
	}
	
	/**
	 * Non-Default - constructor takes all args except for ownerId and listOfPets
	 * @param firstName
	 * @param lastName
	 */
	//public PetOwner(String firstName, String lastName) {
	public PetOwner(String ownerName) {
		super();
		this.listOfPets = getListOfPets();
		//this.firstName = firstName;
		//this.lastName = lastName;
		this.ownerName = ownerName;
	}
	
	
	/**
	 * @param ownerId
	 * @param listOfPets
	 * @param firstName
	 * @param lastName
	 */
	//public PetOwner(int ownerId, ArrayList<Dog> listOfPets, String firstName, String lastName) {
	public PetOwner(int ownerId, ArrayList<Dog> listOfPets, String ownerName) {
		super();
		this.ownerId = ownerId;
		this.listOfPets = listOfPets;
		//this.firstName = firstName;
		//this.lastName = lastName;
		this.ownerName = ownerName;
	}
	
	
	
	/**
	 * @return the ownerId
	 */
	public int getOwnerId() {
		return ownerId;
	}


	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}


	/**
	 * @return the listOfPets
	 */
	public ArrayList<Dog> getListOfPets() {
		return listOfPets;
	}


	/**
	 * @param listOfPets the listOfPets to set
	 */
	//public void setListOfPets() {
	//	this.listOfPets = PetArrayList.getListOfPets(this.ownerId);
	public void setListOfPets(ArrayList<Dog> listOfPets) {
		this.listOfPets = listOfPets;
	}
	
	/**
	 * @param listOfPets the listOfPets to set
	 */
	public void setListOfPets(int ownerId) {
		ArrayList<Dog> listOfPets = new ArrayList<>();
		PetOwnerHelper ownerDao = new PetOwnerHelper();
		//PetOwner owner = ownerDao.toString();
		PetOwner owner = ownerDao.searchForOwnerById(ownerId);
		DogHelper dao = new DogHelper();
		List<Dog> allDogs = dao.showAllDogs();
		if (dao.showAllDogs().isEmpty()) {
			System.out.println("Owner has no pets assigned");
			this.listOfPets = null;
		} else {
			for (Dog singleDog : allDogs) {
				if (owner.getOwnerId() == singleDog.getOwnerId()) {
					listOfPets.add(singleDog);
				}
			}
			this.listOfPets = listOfPets;
		}
		//this.listOfPets = listOfPets;
	}
	
	public void addPetToList(Dog dog) {
		this.listOfPets.add(dog);
	}


//	/**
//	 * @return the firstName
//	 */
//	public String getFirstName() {
//		return firstName;
//	}
//
//
//	/**
//	 * @param firstName the firstName to set
//	 */
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//
//	/**
//	 * @return the lastName
//	 */
//	public String getLastName() {
//		return lastName;
//	}
//
//
//	/**
//	 * @param lastName the lastName to set
//	 */
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}

//	public static int findOwnerId(String fName) {
//		if (firstName.equalsIgnoreCase(fName)) {
//			
//		}
//		ownerId = this.ownerId;
//		return ownerId;
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


//	public String displayOwnerName() {
//		String fullName = "";
//		String fname = this.getFirstName();
//		String lname = this.getLastName();
//		fullName += fname + " " + lname;
//		return fullName;
//	}


//	@Override
//	public String toString() {
//		return "PetOwner [ownerId=" + ownerId + ", listOfPets=" + listOfPets + ", firstName=" + firstName
//				+ ", lastName=" + lastName + "]";
//	}
	
	
	
	public String returnOwnerDetails() {
		// commented out version containing age since will implement later
		//return this.name + " is a " + this.age + " year old " + this.gender + " " + this.breed + " owned by: " + this.ownerName;
		return "Owner Name: " + this.ownerName + "; Owner ID: " + this.ownerId;
		//return "Owner Name: " + this.firstName + " " + this.lastName + "; Owner ID: " + this.ownerId;
		//return "Owner Name: " + this.firstName + " " + this.lastName + "; Owner ID: " + this.ownerId + "; Owns Pets: " + this.ownerName;
	}
	
	public String returnAllOwnerDetails() {
		// commented out version containing age since will implement later
		//return this.name + " is a " + this.age + " year old " + this.gender + " " + this.breed + " owned by: " + this.ownerName;
		ArrayList<Dog> listOfPets = getListOfPets();
		StringBuilder sb = new StringBuilder();
		sb.append("----" + getClass().getName() + "----\n");
		sb.append("Owner Name: " + this.ownerName + "; Owner ID: " + this.ownerId + "\n");
		sb.append("Owners's List of Pets: \n");
		for (Dog dog : listOfPets) {
			//sb.append(dog.formatDogInformation() + "\n");
			sb.append(dog.toString() + "\n");
		}
		//return "Owner Name: " + this.ownerName + "; Owner ID: " + this.ownerId;
		return sb.toString();
	}

	public String displayListOfPets() {
		//PetOwner petOwner = this.petOwner;
		//ArrayList<Dog> listOfPets = petOwner.getListOfPets();
		ArrayList<Dog> listOfPets = getListOfPets();
		//this.listOfPets = listOfPets;
		StringBuilder sb = new StringBuilder();
		sb.append("----" + getClass().getName() + "----");
		for (Dog dog : listOfPets) {
			sb.append(dog.formatDogInformation() + "\n");
			sb.append(dog.toString() + "\n");
		}
		//return "PetListDetails [petListId=" + petListId + ", petOwner=" + petOwner + ", ownerId=" + ownerId + ", listOfPets=" + listOfPets + "]";
		return sb.toString();
	}
	

	@Override
	public String toString() {
		return "PetOwner [ownerId=" + ownerId + ", listOfPets=" + listOfPets + ", ownerName=" + ownerName + "]";
	}
		
		// May do later
//		/**
//		 * Helper method returns a String with address on one line
//		 */
//		public String displayAddress() {
//			String fullAddress = "";
//			String address = this.getStreetAddress();
//			String address2 = this.getStreetAddress2();
//			String city = this.getCity();
//			String state = this.getState();
//			String postalCode = this.getZipcode();
//			if (address2 != null) {
//				fullAddress += address + " " + address2 + " " + city + ", " + state + " " + postalCode;
//			} else {
//				fullAddress += address + " " + city + ", " + state + " " + postalCode;
//			}
//			return fullAddress;
//		}
	//
	
}
