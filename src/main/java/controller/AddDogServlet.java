/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Feb 13, 2023
*/
package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dog;
import model.PetOwner;

/**
 * Servlet implementation class AddDogServlet - called by index.html
 * when select button to add new dog & pet owner to database. The AddDogServlet
 * then uses the doPost() method to get the parameter values entered on the 
 * index.html page & uses them to call constructors & create new Dog &
 * PetOwner object/entities, before returning user to index.html page. 
 */
@WebServlet("/addDogServlet")
public class AddDogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Use request.getParameter("formFieldName") method - to retrieve values from
		// the form's entry fields/buttons
		String dogName = request.getParameter("dogNameInput");
		String breed = request.getParameter("breedInput");
		String gender = request.getParameter("genderInput");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		// ld is used for DOB
		LocalDate ld;
		try {
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		} catch(NumberFormatException ex) {
			ld = LocalDate.now();
		}
		//String dob = request.getParameter("dogDobInput")
		String ownerName = request.getParameter("ownerNameInput");
		String vetName = request.getParameter("vetNameInput");
		
		
//		// ??? changed order so PetOwner created 1st, then Dog object/entity created &
//		// ??? pass in PetOwner ownerId to constructor call?
//		//Dog dog = new Dog(dogName, breed, gender, ownerName, vetName);
//		Dog dog = new Dog(dogName, breed, gender, ld, ownerName, vetName);
//		DogHelper dao = new DogHelper();
//		dao.insertDog(dog);
		
		// ???Take in info passed in from index.html and also create new owner object/entity?
		// ???passing in parameters/values from html page to create? ?will owner_Id automatically
		// ???populate into dogs table d/t join?
		
		// ??? Need to create PetOwner object/entity first so ownerId created then get the
		// ??? generated PrimaryKey ownerId to then pass in when create the new Dog object/entity?
		// ??? Will also need to add new constructor to Dog class that accepts ownerId as a
		// ??? passed in parameter/argument
		//PetOwner owner = new PetOwner(ownerName);
		PetOwnerHelper pohdao = new PetOwnerHelper();
		//pohdao.insertOwner(owner);
		
		// Changed order of above & rather than create PetOwner object owner
		// by passing in 1st name - call PetOwnerHelper to look up to see if
		// owner already exists in database & return that PetOwner object/entity
		// if it does, otherwise the findPetOwner() method calls insertOwner()
		// method to add newly created PetOwner entity/object to database before
		// returning it to caller in AddDogServlet???
		PetOwner owner = pohdao.findPetOwner(ownerName);
		
		//pohdao.insertOwner(owner);
		
		// create method to add dog to owner's listOfPets in PetOwnerHelper class
		// 1st get pet owner's id from newly created pet owner
		int ownerId = owner.getOwnerId();
		
		// ??? changed order so PetOwner created 1st, then Dog object/entity created &
		// ??? pass in PetOwner ownerId to constructor call?
		//Dog dog = new Dog(dogName, breed, gender, ownerName, vetName);
		//Dog dog = new Dog(dogName, breed, gender, ld, ownerName, vetName);
		Dog dog = new Dog(dogName, breed, gender, ld, ownerName, ownerId, vetName);
		DogHelper dao = new DogHelper();
		dao.insertDog(dog);
		
		
		// call PetOwnerHelper to get this specific PetOwner object by ownerId
		PetOwner addToOwner = pohdao.searchForOwnerById(ownerId);
		// then use this object when add dog to owner's pet list
		pohdao.addDogToList(dog, addToOwner);
		// pohdao.addDogToList(dog, ownerId);
		
		getServletContext().getRequestDispatcher("/index.html").forward(request, response);
	}

}
