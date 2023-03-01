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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * ? class hold details of all owner's pets
 * ??? This class may not be needed?  Already have a listOfPets arrayList in 
 * PetOwner class??? And MySQL
 * is generating a owner_dog_ids table w/ the info of pet owner id
 * to pet id. ???
 * ??? Is it useful to keep to use to display an owner's listOfPets??? or is
 * it already done adequately using the PetOwner class w/ it's PetOwnerHelper 
 * dao class ???
 */
@Entity
@Table(name="PETLISTDETAILS")
public class PetListDetails {
	@Id
	@GeneratedValue
	(strategy = GenerationType.IDENTITY)
	@Column(name="PETLIST_ID")
	private int petListId;
	// ???use OneToOne Join since one owner has one petlist??? Not sure if
	// ???this class is even needed? Getting error re: OWNER_ID doesn't exist
	// ???in table, ? does it that column need to be explicitly given as an 
	// ???instance variable for this class if that's what I want it to match to 
	// ???in the PetOwner table with the PetOwner object in this class-see below???
	// ??? change below cascadeType from Persist to Merge?
	//@OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER, targetEntity=PetOwner.class)
	@OneToOne(cascade=CascadeType.MERGE, fetch=FetchType.EAGER, targetEntity=PetOwner.class)
	//@JoinTable(name="OWNER_PETLIST", joinColumns= @JoinColumn(name="OWNER_ID"), inverseJoinColumns = @JoinColumn(name="PETLIST_ID"))
	// below JoinTable JoinColumn version works
	@JoinTable(name="PETLISTDETAILS", joinColumns= @JoinColumn(name="OWNER_ID"), inverseJoinColumns = @JoinColumn(name="PETLIST_ID"))
	//@JoinTable(name="PETOWNER", joinColumns= @JoinColumn(name="OWNER_ID"), inverseJoinColumns = @JoinColumn(name="PETLIST_ID"))
	private PetOwner petOwner;
	//private List<Dog> listOfPets;
	// ??? added in below to see if would resolve key column "OWNER_ID' doesn't exist
	// ??? in table error code: 1072 issue
	@Column(name="OWNER_ID")
	private int ownerId;
	// Change below line to Merge version later - once up & running on servlets?
	//@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	private ArrayList<Dog> listOfPets;
	
	
	
	/**
	 * 
	 */
	public PetListDetails() {
		super();
	}
	
	/**
	 * @param petOwner
	 */
	public PetListDetails(PetOwner petOwner) {
		super();
		this.petOwner = petOwner;
		this.listOfPets = petOwner.getListOfPets();
		this.ownerId = petOwner.getOwnerId();
	}

	/**
	 * @param petOwner
	 * @param listOfPets
	 */
	//public PetListDetails(PetOwner petOwner, List<Dog> listOfPets) {
	public PetListDetails(PetOwner petOwner, ArrayList<Dog> listOfPets) {
		super();
		this.petOwner = petOwner;
		this.listOfPets = listOfPets;
		this.ownerId = petOwner.getOwnerId();
	}
	
	
	/**
	 * @param petListId
	 * @param petOwner
	 * @param listOfPets
	 */
	//public PetListDetails(int petListId, PetOwner petOwner, List<Dog> listOfPets) {
	public PetListDetails(int petListId, PetOwner petOwner, ArrayList<Dog> listOfPets) {
		super();
		this.petListId = petListId;
		this.petOwner = petOwner;
		this.listOfPets = listOfPets;
		this.ownerId = petOwner.getOwnerId();
	}


	/**
	 * @return the petListId
	 */
	public int getPetListId() {
		return petListId;
	}


	/**
	 * @param petListId the petListId to set
	 */
	public void setPetListId(int petListId) {
		this.petListId = petListId;
	}


	/**
	 * @return the petOwner
	 */
	public PetOwner getPetOwner() {
		return petOwner;
	}


	/**
	 * @param petOwner the petOwner to set
	 */
	public void setPetOwner(PetOwner petOwner) {
		this.petOwner = petOwner;
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
	//public void setOwnerId(int ownerId) {
	public void setOwnerId() {
		PetOwner petOwner = this.petOwner;
		int ownerId = petOwner.getOwnerId();
		this.ownerId = ownerId;
	}

	/**
	 * @return the listOfPets
	 */
	public List<Dog> getListOfPets() {
		return listOfPets;
	}


	/**
	 * @param listOfPets the listOfPets to set
	 */
	//public void setListOfPets(List<Dog> listOfPets) {
	//public void setListOfPets(ArrayList<Dog> listOfPets) {
	public void setListOfPets() {
		PetOwner petOwner = this.petOwner;
		ArrayList<Dog> listOfPets = petOwner.getListOfPets();
		this.listOfPets = listOfPets;
	}

	@Override
	public String toString() {
		return "PetListDetails [petListId=" + petListId + ", petOwner=" + petOwner + ", ownerId=" + ownerId
				+ ", listOfPets=" + listOfPets + "]";
	}


//	@Override
//	public String toString() {
//		return "PetListDetails [petListId=" + petListId + ", petOwner=" + petOwner + ", listOfPets=" + listOfPets + "]";
//	}
	
	

}
