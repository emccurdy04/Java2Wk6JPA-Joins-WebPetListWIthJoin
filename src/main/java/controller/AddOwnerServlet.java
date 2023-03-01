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
 * Servlet implementation class AddOwnerServlet
 * ???Is this Servlet redundant since won't be adding
 * ???a new petOwner w/o an associated pet???
 */
@WebServlet("/addOwnerServlet")
public class AddOwnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOwnerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    //???Addded in doGet w/ @Override to see if would resolve the "HTTP Status 405 - 
    //???HTTP method is not supported by this URL - error getting when this this
    //???servlet was called from any of the html or jsp pages
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Use request.getParameter("formFieldName") method - to retrieve values from
		// the form's entry fields/buttons
		
		// ???add in pet data input - or is this Servlet redundant since won't be adding
		// ???a new petOwner w/o an associated pet???
		//String dogName = request.getParameter("dogNameInput");
		//String breed = request.getParameter("breedInput");
		//String gender = request.getParameter("genderInput");
		//String dob = request.getParameter("dogDobInput")
		String ownerName = request.getParameter("ownerNameInput");
		//String vetName = request.getParameter("vetNameInput");
		
		//Dog dog = new Dog(dogName, breed, gender, ownerName, vetName);
		PetOwner owner = new PetOwner(ownerName);
		PetOwnerHelper pohdao = new PetOwnerHelper();
		pohdao.insertOwner(owner);
		
		getServletContext().getRequestDispatcher("/index.html").forward(request, response);
	}

}
