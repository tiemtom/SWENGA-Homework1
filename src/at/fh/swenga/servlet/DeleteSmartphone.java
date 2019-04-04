package at.fh.swenga.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import at.fh.swenga.model.SmartphoneService;

/**
 * Servlet implementation class DeleteSmartphone
 */
@WebServlet("/deleteSmartphone")
public class DeleteSmartphone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSmartphone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		 
		int id = Integer.parseInt(idString);
 
		// get the http session object for the user
		HttpSession session = request.getSession(true);
 
		// get the smartphoneService out of the session
		SmartphoneService smartphoneService = (SmartphoneService) session.getAttribute("smartphoneService");
		smartphoneService.remove(id);
 
		request.setAttribute("warningMessage", "Smartphone " + id + " deleted");
 
		RequestDispatcher rd = request.getRequestDispatcher("./listSmartphones");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
