/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Feb 1, 2023
*/
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Dog;

/**
 * The controller/helper - a.k.a - DAO - Data Access Object - does all the
 * persistence to the database (DB) It will take the object passed from the
 * 'view' & run the appropriate method. This class uses the StartPetListProgram.java
 * class for the interface to gather the objects to pass over to the
 * DogHelper methods for persistence.
 */
public class DogHelper {
	// create a global instance of the EntityManagerFactory -
	// which is then used to created the EntityManager - which
	// manages the Entities/Entity object operations to & from the DB -
	// such as finding objects, committing/saving them to the DB,
	// removing them from the DB, etc.
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebPetListWithJoin");
	
	
	/**
	 * Method - accepts a Dog object/Entity to add to row of dogs table in pet DB
	 * 
	 * @param - toAdd - instance/object/Entity of Dog class
	 */
	public void insertDog(Dog toAdd) {
		// create an instance of EntityManager object - em
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(toAdd);
		em.getTransaction().commit();
		em.close();
	}
	
	
	public List<Dog> showAllDogs() {
		// create an instance of EntityManager object - em
		EntityManager em = emfactory.createEntityManager();
		List<Dog> allDogs = em.createQuery("SELECT dog FROM Dog dog").getResultList();
		return allDogs;
	}

	/**
	 * Method take the specified Dog object/Entity passed in, searches database table
	 * for object/row with matching name/ownerName Strings and ID int, then deletes it 
	 * from the DB table - since specified maxResult of 1 - if there is a duplicate
	 * dog object/entitiy it will only select, then delete the 1st one found.
	 * 
	 * @param - toDelete Dog class object containing want to Delete from DB table
	 */
	public void deleteDog(Dog toDelete) {
		// create an instance of EntityManager object - em
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Dog> typedQuery = em.createQuery("SELECT dog FROM Dog dog where dog.name = :selectedName and dog.ownerName = :selectedOwnerName and dog.id = :selectedId", Dog.class);
		// Substitute named parameter with actual data from the passed in toDelete dog object
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedOwnerName", toDelete.getOwnerName());
		typedQuery.setParameter("selectedId", toDelete.getId());
		// specify number of results desired
		typedQuery.setMaxResults(1);
		
		// use getSingleResult() fxn to call & get result from table, then assign/save
		// it to a new Dog object called result
		Dog result = typedQuery.getSingleResult();
		// remove Dog result object/entity from pet DB dog table, commit to save it, then close em
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Method searches for Dog object/Entity using specified dog ID - the dogs table's primary
	 * key for pet DB
	 * 
	 * @param idToEdit - int - dog ID - dogs table's primary key
	 * @return found - Dog object/Entity for specified row of dogs table in pet db
	 */
	public Dog searchForDogById(int idToEdit) {
		// create an instance of EntityManager object - em
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Dog found = em.find(Dog.class, idToEdit);
		em.close();
		return found;
	}

	/**
	 * Method connects to pet DB, then updates the Dog class Entity/Object in the
	 * dogs table - using the passed in updated/edited dog object/entity, using the
	 * em.merge(updatedEntity) method, then commits the changes
	 * 
	 * @param toEdit - Dog class object/entity that was/is to be updated
	 */
	public void updateDog(Dog toEdit) {
		// create an instance of EntityManager object - em
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	
	public List<Dog> searchForDogByName(String dogName) {
		// create an instance of EntityManager object - em
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Dog> typedQuery = em.createQuery("SELECT dog FROM Dog dog WHERE dog.name = :selectedName", Dog.class);
		typedQuery.setParameter("selectedName", dogName);
		
		List<Dog> foundDogs = typedQuery.getResultList();
		em.close();
		return foundDogs;
	}

	
	public List<Dog> searchForDogByOwner(String ownerName) {
		// create an instance of EntityManager object - em
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Dog> typedQuery = em.createQuery("SELECT dog FROM Dog dog WHERE dog.ownerName = :selectedOwnerName", Dog.class);
		typedQuery.setParameter("selectedOwnerName", ownerName);
		List<Dog> foundDogs = typedQuery.getResultList();
		em.close();
		return foundDogs;
	}

	
	public List<Dog> searchForDogByID(int id) {
		// create an instance of EntityManager object - em
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Dog> typedQuery = em.createQuery("SELECT dog FROM Dog dog WHERE dog.id = :selectedId", Dog.class);
		typedQuery.setParameter("selectedId", id);
		List<Dog> foundDogs = typedQuery.getResultList();
		em.close();
		return foundDogs;
	}



	/**
	 * Method to close the EntityManagerFactory connection after finished making
	 * changes to the database - since do not want to leave unused connections open
	 * to the database. It is called by the runMenu() method in the StartPetListProgram
	 * class
	 */
	public void cleanUp() {
		emfactory.close();
	}

}
