import java.time.LocalDate;
import java.util.List;

import controller.PetListDetailsHelper;
import controller.PetOwnerHelper;
import model.Dog;
import model.PetListDetails;
import model.PetOwner;

/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Feb 21, 2023
*/

/**
 * PetOwnerTester class to test persistence w/o web form
 */
public class PetOwnerTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//PetOwner gretal = new PetOwner("Gretal", "Hanzel");
		PetOwner gretal = new PetOwner("Gretal");
		
		PetOwnerHelper poh = new PetOwnerHelper();
				
		poh.insertOwner(gretal);
		
		//PetOwner gunter = new PetOwner("Gunter", "Heintz");
		PetOwner gunter = new PetOwner("Gunter");
		poh.insertOwner(gunter);
		
		//PetOwner francis = new PetOwner("Francis", "Franklin");
		PetOwner francis = new PetOwner("Francis");
		poh.insertOwner(francis);
		
		//PetOwner harietta = new PetOwner("Harietta", "Haris");
		PetOwner harietta = new PetOwner("Harietta");
		poh.insertOwner(harietta);
		
		//PetOwner jenna = new PetOwner("Jenna", "Fitzpatrick");
		PetOwner jenna = new PetOwner("Jenna");
		poh.insertOwner(jenna);
		
		//PetOwner heinrich = new PetOwner("Heinrich", "Henderson");
		PetOwner heinrich = new PetOwner("Heinrich");
		poh.insertOwner(heinrich);
		
		PetListDetailsHelper pldh = new PetListDetailsHelper();
		//String name, String breed, String gender, LocalDate dogDOB, String ownerName, String primaryVet
		Dog hans = new Dog("Hans", "Siberian Husky", "male", LocalDate.now(), "Gunter", "Dr. Caterson");
		Dog belle = new Dog("Belle", "Corgi", "female", LocalDate.now(), "Jenna", "Dr. Caterson");
		
		PetListDetails petList = new PetListDetails(gunter);
		pldh.insertNewPetToList(hans);
		pldh.insertNewPetToList(belle);
		//pldh.insertNewPetDetails(hans);
		System.out.println(petList.toString());
		
		List<PetOwner> allOwners = poh.showAllOwners();
		for(PetOwner owner : allOwners) {
			System.out.println(owner.toString());
		}

	}

}
