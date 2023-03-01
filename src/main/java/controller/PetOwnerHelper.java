/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Feb 21, 2023
*/
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Dog;
import model.PetOwner;

/**
 * DAO to work with PetOwner class and database
 */
public class PetOwnerHelper {
	
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebPetListWithJoin");
	
	public void insertOwner(PetOwner ownerToAdd) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(ownerToAdd);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<PetOwner> showAllOwners() {
		EntityManager em = emfactory.createEntityManager();
		List<PetOwner> allOwners = em.createQuery("SELECT petowner FROM PetOwner petowner").getResultList();
		return allOwners;
	}
	
	//public List<Dog> 
	
//	public void deleteOwner(PetOwner ownerToDelete) {
//		EntityManager em = emfactory.createEntityManager();
//		em.getTransaction().begin();
//		TypedQuery<PetOwner> typedQuery = em.createQuery("SELECT petowner FROM PetOwner petowner WHERE petowner.firstName = :selectedFirstName and petowner.lastName = :selectedLastName and petowner.ownerId = :selectedOwnerId", PetOwner.class);
//		typedQuery.setParameter("selectedFirstName", ownerToDelete.getFirstName());
//		typedQuery.setParameter("selectedLastName", ownerToDelete.getLastName());
//		typedQuery.setParameter("selectedOwnerId", ownerToDelete.getOwnerId());
//		typedQuery.setMaxResults(1);
//		
//		PetOwner result = typedQuery.getSingleResult();
//		
//		em.remove(result);
//		em.getTransaction().commit();
//		em.close();
//	}
	
	public void deleteOwner(PetOwner ownerToDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetOwner> typedQuery = em.createQuery("SELECT petowner FROM PetOwner petowner WHERE petowner.ownerName = :selectedOwnerName and petowner.ownerId = :selectedOwnerId", PetOwner.class);
		typedQuery.setParameter("selectedOwnerName", ownerToDelete.getOwnerName());
		typedQuery.setParameter("selectedOwnerId", ownerToDelete.getOwnerId());
		typedQuery.setMaxResults(1);
		
		PetOwner result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public PetOwner searchForOwnerById(int ownerIdToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		PetOwner found = em.find(PetOwner.class, ownerIdToEdit);
		em.close();
		return found;
	}
	
	/**
	 * Method to look up an existing PetOwner by name.  If found result is returned.
	 * If not found, new PetOwner created w/ name passed into method, then returned
	 * to caller. 
	 */
	public PetOwner findPetOwner(String ownerToFind) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetOwner> typedQuery = em.createQuery("SELECT petOwner FROM PetOwner petOwner WHERE petOwner.ownerName = :selectedName", PetOwner.class);
		typedQuery.setParameter("selectedName", ownerToFind);
		typedQuery.setMaxResults(1);
		//TypedQuery<PetOwner> typedQuery = em.createQuery("SELECT petowner FROM PetOwner petowner WHERE petowner.ownerName = : selectedName", PetOwner.class);
		//typedQuery.setParameter("selectedName", ownerToFind);
		//typedQuery.setMaxResults(1);
		
		PetOwner foundOwner;
		try {
			foundOwner = typedQuery.getSingleResult();
		} catch (NoResultException ex) {
			foundOwner = new PetOwner(ownerToFind);
			insertOwner(foundOwner);
		}
		em.close();
		return foundOwner;
	}
	
	public void updateOwner(PetOwner ownerToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(ownerToEdit);
		em.getTransaction().commit();
		em.close();
	}
	
//	public List<PetOwner> searchForOwnerByDog(String dogName) {
//		EntityManager em = emfactory.createEntityManager();
//		em.getTransaction().begin();
//		TypedQuery<PetOwner> typedQuery = em.createQuery("SELECT petowner FROM PetOwner petowner WHERE petOwner.dogName =: selectedDogName", PetOwner.class);
//		typedQuery.setParameter("selectedOwnerId", dogName);
//		List<PetOwner> foundOwners = typedQuery.getResultList();
//		em.close();
//		return foundOwners;
//	}
	
	
	public List<PetOwner> searchForOwnerByName(String ownerName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetOwner> typedQuery = em.createQuery("SELECT petowner FROM PetOwner petowner WHERE petOwner.ownerName =: selectedOwnerName", PetOwner.class);
		typedQuery.setParameter("selectedOwnerName", ownerName);
		List<PetOwner> foundOwners = typedQuery.getResultList();
		em.close();
		return foundOwners;
	}
	
	public List<PetOwner> searchForOwnerByID(int ownerId) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetOwner> typedQuery = em.createQuery("SELECT petowner FROM PetOwner petowner WHERE petOwner.ownerId =: selectedOwnerId", PetOwner.class);
		typedQuery.setParameter("selectedOwnerId", ownerId);
		List<PetOwner> foundOwners = typedQuery.getResultList();
		em.close();
		return foundOwners;
	}
	
//	//private ArrayList<Dog> listOfPets = new ArrayList<>();
//	//public ArrayList<Dog> findOwnerPetList(PetOwner ownerListToDisplay) {
//	public ArrayList<Dog> findOwnerPetList(int ownerId) {
//		EntityManager em = emfactory.createEntityManager();
//		em.getTransaction().begin();
//		//List<PetOwner> allOwners = em.createQuery("SELECT petowner FROM PetOwner petowner").getResultList();
//		//TypedQuery<PetOwner> typedQuery = em.createQuery("SELECT petowner FROM PetOwner petowner WHERE petOwner.ownerId =: selectedOwnerId", PetOwner.class);
//		//TypedQuery<PetOwner> typedQuery = em.createQuery("SELECT listOfPets FROM PetOwner listOfPets WHERE petOwner.ownerId =: selectedOwnerId", PetOwner.class);
//		TypedQuery<PetOwner> typedQuery = em.createQuery("SELECT listOfPets FROM PetOwner listOfPets WHERE petOwner.ownerId =: selectedOwnerId", PetOwner.class);
//		//typedQuery.setParameter("selectedOwnerId", ownerListToDisplay);
//		typedQuery.setParameter("selectedOwnerId", ownerId);
//		ArrayList<Dog> foundPets = typedQuery.getResultList();
//		em.close();
//		return foundPets;
//	}
	
	//public PetOwner addDogToList(Dog dogToAdd, int ownerId) {
	//public void addDogToList(Dog dogToAdd, int ownerId) {
	public void addDogToList(Dog dogToAdd, PetOwner ownerToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		//TypedQuery<PetOwner> typedQuery = em.createQuery("SELECT petowner FROM PetOwner petowner WHERE petOwner.ownerId =: selectedOwnerId", PetOwner.class);
		//typedQuery.setParameter("selectedOwnerId", ownerId);
		//TypedQuery<PetOwner> typedQuery = em.createQuery("SELECT petowner FROM PetOwner petowner WHERE petOwner.ownerId = : selectedOwnerId", PetOwner.class);
		//typedQuery.setParameter("selectedOwnerId", ownerId);
		//typedQuery.setMaxResults(1);
		//PetOwner result = typedQuery.getSingleResult();
		//PetOwner ownerUpdated = ownerToEdit.addPetToList(dogToAdd);
		ownerToEdit.addPetToList(dogToAdd);
		//em.merge(result.addPetToList(dogToAdd));
		//updateOwner(PetOwner ownerToEdit)
		//em.persist(result.addPetToList(dogToAdd));
		//em.merge(ownerUpdated);
		//em.merge(ownerToEdit.addPetToList(dogToAdd));
		em.merge(ownerToEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public void cleanUp() {
		emfactory.close();
	}

}
