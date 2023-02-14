/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Feb 14, 2023
*/
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dog;

/**
 * Servlet implementation class EditDogServlet
 */
@WebServlet("/editDogServlet")
public class EditDogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DogHelper dao = new DogHelper();
		
		String dogName = request.getParameter("dogNameInput");
		String breed = request.getParameter("breedInput");
		String gender = request.getParameter("genderInput");
		String ownerName = request.getParameter("ownerNameInput");
		String vetName = request.getParameter("vetNameInput");
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		
		Dog dogToUpdate = dao.searchForDogById(tempId);
		dogToUpdate.setName(dogName);
		dogToUpdate.setBreed(breed);
		dogToUpdate.setGender(gender);
		dogToUpdate.setOwnerName(ownerName);
		dogToUpdate.setPrimaryVet(vetName);
		
		dao.updateDog(dogToUpdate);
		
		getServletContext().getRequestDispatcher("/viewAllDogsServlet").forward(request, response);
	}

}
