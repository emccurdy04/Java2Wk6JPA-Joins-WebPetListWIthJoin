/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Feb 13, 2023
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
 * Servlet implementation class AddDogServlet
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
		String ownerName = request.getParameter("ownerNameInput");
		String vetName = request.getParameter("vetNameInput");
		
		Dog dog = new Dog(dogName, breed, gender, ownerName, vetName);
		DogHelper dao = new DogHelper();
		dao.insertDog(dog);
		
		getServletContext().getRequestDispatcher("/index.html").forward(request, response);
	}

}
