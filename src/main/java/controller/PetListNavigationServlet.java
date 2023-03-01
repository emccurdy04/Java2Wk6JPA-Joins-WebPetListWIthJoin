package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PetListDetails;
import model.PetOwner;

/**
 * Servlet implementation class PetListNavigationServlet
 * ??? Not sure this Servlet makes sense in my project as it was
 * ??? demonstrated in the lab example?  
 * ??? Need to change this servlet completely so it just directs
 * to dog or owner for edits, adds or deletes since pet list created
 * from data in those object/entities & should be updated when
 * those are changed/added to??? 
 */
@WebServlet("/petListNavigationServlet")
public class PetListNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetListNavigationServlet() {
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
		PetListDetailsHelper pldhdao = new PetListDetailsHelper();
		String act = request.getParameter("doThisToPetList");
		
		//String path="/viewAllOwnersServlet";
		
		if (act == null) {
			// no button has been selected - return to view pet list servlet page
			getServletContext().getRequestDispatcher("/viewPetListServlet").forward(request, response);
		} else if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				//PetOwner ownerToDelete = pohdao.searchForOwnerById(tempId);
				PetListDetails petListToDelete = pldhdao.searchForPetListById(tempId);
				//pohdao.deleteOwner(ownerToDelete);
				pldhdao.deletePetListDetails(petListToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select a pet list");
			} finally {
				getServletContext().getRequestDispatcher("/viewPetListServlet").forward(request, response);
			}
		} else if (act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				//PetOwner ownerToEdit = pohdao.searchForOwnerById(tempId);
				PetListDetails petListToEdit = pldhdao.searchForPetListById(tempId);
				//request.setAttribute("ownerToEdit", ownerToEdit);
				request.setAttribute("petListToEdit", petListToEdit);
				// Add in any additional request.setAttributes - for dates month, day/date, year etc
				//request.setAttribute("fieldToEdit", petListToEdit.getDogDOB().getMonthValue());
				// request.setAttribute(
				
				// Create another instance of this just for edits?
				PetListDetailsHelper pldheditdao = new PetListDetailsHelper();
				request.setAttribute("allPets", pldheditdao.showAllPets());
				//path="/edit-owner.jsp";
				// ??? edit-petlist.jsp doesn't exist ?yet? 
				// move below line since creating if block for empty list
				//getServletContext().getRequestDispatcher("/edit-petlist.jsp").forward(request, response);
				// ??? remove? 
				if (pldheditdao.showAllPets().isEmpty()) {
					request.setAttribute("allPets", "");
				}
				
				getServletContext().getRequestDispatcher("/edit-petlist.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select a pet list");
			//} finally {
				getServletContext().getRequestDispatcher("/viewPetListServlet").forward(request, response);
			}
		} else if (act.equals("add")) {
			//path="/index.html";
			getServletContext().getRequestDispatcher("/new-petlist.jsp").forward(request, response);
			// ?which page to add dog to database/owner pet list? 
			//getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		}
		// don't need since not using String path above, but directing w/ each branch of if/else
		// depending on what user selected to edit
		//getServletContext().getRequestDispatcher(path).forward(request, response);
	}
		

}
