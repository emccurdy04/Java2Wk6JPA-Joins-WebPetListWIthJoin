package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dog;
import model.PetOwner;

/**
 * Servlet implementation class EditOwnerServlet
 */
@WebServlet("/editOwnerServlet")
public class EditOwnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditOwnerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PetOwnerHelper pohdao = new PetOwnerHelper();
		
		//String dogName = request.getParameter("dogNameInput");
		//String breed = request.getParameter("breedInput");
		//String gender = request.getParameter("genderInput");
		String ownerName = request.getParameter("ownerNameInput");
		//String vetName = request.getParameter("vetNameInput");
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		
		PetOwner ownerToUpdate = pohdao.searchForOwnerById(tempId);
		//dogToUpdate.setName(dogName);
		//dogToUpdate.setBreed(breed);
		//dogToUpdate.setGender(gender);
		ownerToUpdate.setOwnerName(ownerName);
		//dogToUpdate.setPrimaryVet(vetName);
		
		pohdao.updateOwner(ownerToUpdate);
		
		getServletContext().getRequestDispatcher("/viewAllOwnersServlet").forward(request, response);
		
	}

}
