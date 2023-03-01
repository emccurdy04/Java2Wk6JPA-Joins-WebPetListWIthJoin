package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dog;
import model.PetListDetails;
import model.PetOwner;

/**
 * Servlet implementation class ViewPetListServlet
 */
@WebServlet("/viewPetListServlet")
public class ViewPetListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPetListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//PetListDetailsHelper pldh = new PetListDetailsHelper();
		//List<PetListDetails> listOfPets = pldh.showAllPets();
		//ArrayList<Dog> listOfPets = pldh.showAllPets();
		//request.setAttribute("listOfPets", listOfPets);
		
		PetOwnerHelper pohdao = new PetOwnerHelper();
		String path = "/petlist-by-owner.jsp";
		
		try {
			//PetOwnerHelper pohdao = new PetOwnerHelper();
			//String path = "/petowner-list.jsp";
			//String path = "/petlist-by-owner.jsp";
			Integer tempId = Integer.parseInt(request.getParameter("id"));
			
			//PetOwnerHelper pohdao = new PetOwnerHelper();
			PetOwner ownerListToDisplay = pohdao.searchForOwnerById(tempId);
			//PetOwner ownerToUpdate = pohdao.searchForOwnerById(tempId);
			
			ArrayList<Dog> listOfPets = ownerListToDisplay.getListOfPets();
			//private ArrayList<Dog> listOfPets = pohdao.findOwnerPetList(ownerListToDisplay)
			request.setAttribute("listOfPets", listOfPets);
			
			if (listOfPets.isEmpty()) {
				//request.setAttribute("listOfPets", "");
				path = "/index.html";
			}
		} catch (NumberFormatException e) {
			System.out.println("Forgot to select an owner");
		} finally {
			//getServletContext().getRequestDispatcher("/petlist-by-owner.jsp").forward(request, response);
			getServletContext().getRequestDispatcher(path).forward(request, response);
		}
		//getServletContext().getRequestDispatcher("/petlist-by-owner.jsp").forward(request, response);
		//getServletContext().getRequestDispatcher(path).forward(request, response);

		//???Change this servlet more to an edit pet list type of servlet 
		//PetOwnerHelper pohdao = new PetOwnerHelper();
		
//		Integer tempId = Integer.parseInt(request.getParameter("id"));
//		
//		PetOwner ownerToUpdate = pohdao.searchForOwnerById(tempId);
//		//dogToUpdate.setName(dogName);
//		//dogToUpdate.setBreed(breed);
//		//dogToUpdate.setGender(gender);
//		ownerToUpdate.setOwnerName(ownerName);
//		//dogToUpdate.setPrimaryVet(vetName);
//		
//		pohdao.updateOwner(ownerToUpdate);
//		PetOwnerHelper pohdao = new PetOwnerHelper();
//		
//		request.setAttribute("allOwners", pohdao.showAllOwners());
//		
//		//String path = "/owner-list.jsp";
//		String path = "/petowner-list.jsp";
//		
//		if (pohdao.showAllOwners().isEmpty()) {
//			path = "/index.html";
//		}
//		
//		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
