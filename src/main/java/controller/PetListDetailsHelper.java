/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Feb 21, 2023
*/
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Dog;
import model.PetListDetails;
import model.PetOwner;

/**
 * PetListDetails doa - 
 * ??? Change whole class so can only view PetListDetails list 
 * ??? Use showAllPets() method in this class to just show pets
 * where dog has specified owner_ID ???  Only leave showAllPets()
 * and searchForPetListById() methods functional? 
 * ??? Remove/comment out update, delete & both insert methods??  
 */
public class PetListDetailsHelper {
	
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebPetListWithJoin");

	public void insertNewPetDetails(PetListDetails newPetList) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(newPetList);
		em.getTransaction().commit();
		em.close();
	}
	
	public void insertNewPetToList(Dog newPetToAdd) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(newPetToAdd);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<PetListDetails> showAllPets() {
		EntityManager em = emfactory.createEntityManager();
		List<PetListDetails> allPets = em.createQuery("SELECT petlistdetails FROM PetListDetails petlistdetails").getResultList();
		return allPets;
	}
	
	public PetListDetails searchForPetListById(int petListIdToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		PetListDetails found = em.find(PetListDetails.class, petListIdToEdit);
		em.close();
		return found;
	}
	
	public void updatePetListDetails(PetListDetails petListIdToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(petListIdToEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public void deletePetListDetails(PetListDetails petListToDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		// changed below 2 lines since only want to be able to select petlist by its ID
		//TypedQuery<PetListDetails> typedQuery = em.createQuery("SELECT petlistdetails FROM PetListDetails petlistdetails WHERE petlistdetails.petowner = :selectedpetowner and petlistdetails.petListId = :selectedPetListId", PetListDetails.class);
		//typedQuery.setParameter("selectedpetowner", petListToDelete.getPetOwner());
		//typedQuery.setParameter("selectedPetListId", petListToDelete.getPetListId());
		
		TypedQuery<PetListDetails> typedQuery = em.createQuery("SELECT petlistdetails FROM PetListDetails petlistdetails WHERE petlistdetails.petListId = :selectedPetListId", PetListDetails.class);
		typedQuery.setParameter("selectedPetListId", petListToDelete.getPetListId());
		
		// specify only want one result
		typedQuery.setMaxResults(1);
		
		// get the result & use it to remove the selected petlist
		PetListDetails result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
