package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dog;
import model.PetListDetails;
import model.PetOwner;

/**
 * Servlet implementation class AddPetsForPetListServlet
 * ???Change this Servlet so only used to to add a pet to
 * ??? an existing PetOwner's listOfPets??? - 
 */
@WebServlet("/addPetsForPetListServlet")
public class AddPetsForPetListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPetsForPetListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		//response.getWriter().append("Served at: ").append(request.getContextPath());
//		
//		//???If change this Servlet so only used to to add a pet to an existing 
//		//???PetOwner's listOfPets??? - add way of finding owner by selected id, then
//		//???grabbing pet/dog inputs & calling DogHelper to then create new Dog object/entity
//		//???and add that new Dog object/entity to the PetOwner's listOfPets - then change
//		//???getRequestDispactcher() to direct to ?petlist-by-owner if just showing one
//		//???owner & their listOfPets or have the new-petlist do that to show 'refreshed'
//		//???version of the owner's listOfPets after added new Dog object/entity
//		
//		//DogHelper dhdao = new DogHelper();
//		PetListDetailsHelper pldhdao = new PetListDetailsHelper();
//		//request.setAttribute("allPets", dhdao.showAllDogs());
//		request.setAttribute("allPets", pldhdao.showAllPets());
//		
//		if(pldhdao.showAllPets().isEmpty()) {
//			request.setAttribute("allPets", "");
//		}
		
		String act = request.getParameter("doThisToPetList");
		
		DogHelper dao = new DogHelper();
		PetOwnerHelper pohdao = new PetOwnerHelper();
		
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		
		PetOwner ownerToUpdate = pohdao.searchForOwnerById(tempId);
		//String path="/viewAllOwnersServlet";
		
		if (act == null) {
			// no button has been selected - return to view new-petlist.jsp rather than
			//viewPetListServlet page
			//getServletContext().getRequestDispatcher("/viewPetListServlet").forward(request, response);
			getServletContext().getRequestDispatcher("/new-petlist.jsp").forward(request, response);
		} else if (act.equals("delete")) {
			try {
				Integer tempDogId = Integer.parseInt(request.getParameter("dogId"));
				Dog dogToDelete = dao.searchForDogById(tempDogId);
				dao.deleteDog(dogToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select an item");
			} finally {
				getServletContext().getRequestDispatcher("/new-petlist.jsp").forward(request, response);
			}
		} else if (act.equals("add")) {
			try {
				
				//PetOwner ownerToDelete = pohdao.searchForOwnerById(tempId);
				//PetListDetails petListToDelete = pldhdao.searchForPetListById(tempId);
				//pohdao.deleteOwner(ownerToDelete);
				//pldhdao.deletePetListDetails(petListToDelete);		
		
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
				//PetOwner owner = new PetOwner(ownerName);
				
				//PetOwnerHelper pohdao = new PetOwnerHelper();
				
				//Integer tempId = Integer.parseInt(request.getParameter("id"));
				
				//PetOwner ownerToUpdate = pohdao.searchForOwnerById(tempId);
				ownerToUpdate.setOwnerName(ownerName);
				
				pohdao.updateOwner(ownerToUpdate);
				//pohdao.insertOwner(owner);
				// create method to add dog to owner's listOfPets in PetOwnerHelper class
				// 1st get pet owner's id from newly created pet owner
				//int ownerId = owner.getOwnerId();
				
				// ??? changed order so PetOwner created 1st, then Dog object/entity created &
				// ??? pass in PetOwner ownerId to constructor call?
				//Dog dog = new Dog(dogName, breed, gender, ownerName, vetName);
				//Dog dog = new Dog(dogName, breed, gender, ld, ownerName, vetName);
				//Dog dog = new Dog(dogName, breed, gender, ld, ownerName, ownerId, vetName);
				Dog dog = new Dog(dogName, breed, gender, ld, ownerName, tempId, vetName);
				//DogHelper dao = new DogHelper();
				dao.insertDog(dog);
				
				// call PetOwnerHelper to get this specific PetOwner object by ownerId
				//PetOwner addToOwner = pohdao.searchForOwnerById(ownerId);
				// then use this object when add dog to owner's pet list
				pohdao.addDogToList(dog, ownerToUpdate);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select a pet list");
			} finally {
				getServletContext().getRequestDispatcher("/new-petlist.jsp").forward(request, response);
				//getServletContext().getRequestDispatcher("/viewPetListServlet").forward(request, response);
			}
		}
		//getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		//getServletContext().getRequestDispatcher("/new-petlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
