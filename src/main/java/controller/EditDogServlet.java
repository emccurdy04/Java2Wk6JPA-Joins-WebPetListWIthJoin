/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Feb 14, 2023
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

/**
 * Servlet implementation class EditDogServlet -
 * "editDogServlet" is called by "edit-dog.jsp" when click to save edited dog 
 * object/entity to the database and is called/directed to by NavigationServlet
 * if selected to edit dog entity/object on dog-list.jsp page. 
 * After EditDogServlet done it returns to "/viewAllDogsServlet" page - which
 * gets the DogHelper.showAllDogs() doglist if list not empty & then goes to
 * "/dog-list.jsp" to display it to user.  If list empty it directs to index.html
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
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		// ld is used for DOB
//		LocalDate ld;
//		try {
//			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
//		} catch(NumberFormatException ex) {
//			//ld = LocalDate.now();
//			// change above so if new dog DOB not entered on edit-dog.jsp page that it uses the
//			// original dogDOB value
//			ld = dogToUpdate.getDogDOB();
//		}
		String ownerName = request.getParameter("ownerNameInput");
		String vetName = request.getParameter("vetNameInput");
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		
		Dog dogToUpdate = dao.searchForDogById(tempId);
		// ld is used for DOB
		// moved this to after Dog object/entity found by selected dog's ID so could use
		// original dog DOB if user didn't edit it w/ a new date - original version reset
		// dog's DOB to today's date if none was entered into text input box
		LocalDate ld;
		try {
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		} catch(NumberFormatException ex) {
			//ld = LocalDate.now();
			// change above so if new dog DOB not entered on edit-dog.jsp page that it uses the
			// original dogDOB value
			ld = dogToUpdate.getDogDOB();
		}
		
		dogToUpdate.setName(dogName);
		dogToUpdate.setBreed(breed);
		dogToUpdate.setGender(gender);
		dogToUpdate.setDogDOB(ld);
		dogToUpdate.setOwnerName(ownerName);
		dogToUpdate.setPrimaryVet(vetName);
		
		dao.updateDog(dogToUpdate);
		
		getServletContext().getRequestDispatcher("/viewAllDogsServlet").forward(request, response);
	}

}
