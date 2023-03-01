/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Feb 21, 2023
*/
package model;

import java.util.ArrayList;
import java.util.List;

import controller.DogHelper;
import controller.PetOwnerHelper;

/**
 * Array list of all pets for owner
 * ??? class may not be needed?  Already in PetOwner???
 */
public class PetArrayList {
	private ArrayList<Dog> listOfPets;

	/**
	 * 
	 */
	public PetArrayList() {
		super();
		listOfPets = new ArrayList<Dog>();
	}

	/**
	 * @return the listOfPets
	 */
	public ArrayList<Dog> getListOfPets() {
		return listOfPets;
	}

//	/**
//	 * @param listOfPets the listOfPets to set
//	 */
//	public void setListOfPets(ArrayList<Dog> listOfPets) {
//		DogHelper dao = new DogHelper();
//		
//		dao.showAllDogs();
//		if (dao.showAllDogs().isEmpty()) {
//			System.out.println("Owner has no pets assigned");
//		} else {
//			if 
//		}
//		//this.listOfPets = listOfPets;
//	}
	
	/**
	 * @param listOfPets the listOfPets to set
	 */
	public void setListOfPets(int ownerId) {
		PetOwnerHelper ownerDao = new PetOwnerHelper();
		PetOwner owner = ownerDao.searchForOwnerById(ownerId);
		
		DogHelper dao = new DogHelper();
		List<Dog> allDogs = dao.showAllDogs();
		if (dao.showAllDogs().isEmpty()) {
			System.out.println("Owner has no pets assigned");
		} else {
			for (Dog singleDog : allDogs) {
				if (owner.getOwnerId() == singleDog.getOwnerId()) {
					listOfPets.add(singleDog);
				}
			}
		}
		this.listOfPets = listOfPets;
	}
	
	public void addPet(Dog dogInfo) {
		listOfPets.add(dogInfo);
	}

	@Override
	public String toString() {
		return "PetArrayList []";
	}
	
}
