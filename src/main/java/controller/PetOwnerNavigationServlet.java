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
 * Servlet implementation class PetOwnerNavigationServlet
 */
@WebServlet("/petOwnerNavigationServlet")
public class PetOwnerNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetOwnerNavigationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		PetOwnerHelper pohdao = new PetOwnerHelper();
		String act = request.getParameter("doThisToOwner");
		
		String path="/viewAllOwnersServlet";
				
		if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				PetOwner ownerToDelete = pohdao.searchForOwnerById(tempId);
				pohdao.deleteOwner(ownerToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select an owner");
			}
		} else if (act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				PetOwner ownerToEdit = pohdao.searchForOwnerById(tempId);
				request.setAttribute("ownerToEdit", ownerToEdit);
				path="/edit-owner.jsp";
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select an owner");
			}
		} else if (act.equals("add")) {
			// ??? change path for add to an add-owner.jsp ?or go to edit-owner.jsp?
			// ??? or need to change index.html to also take in owner data when first
			// ??? add dog - ? way to create two separate entities/objects in 2 different
			// ??? tables in the database from same page?
			try {
				//path="/index.html";
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				PetOwner ownerToEdit = pohdao.searchForOwnerById(tempId);
				request.setAttribute("ownerToEdit", ownerToEdit);
				// ???rather than edit-owner.jsp or /index.html go to ?new-petlist.jsp
				// ??? to add a new pet??? or create a new jsp for this edit-petlist
				//path="/edit-owner.jsp";
				//path="/edit-petlist.jsp";
				path = "/new-petlist.jsp";
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select an owner");
			}
		}
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
