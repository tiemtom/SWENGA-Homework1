package at.fh.swenga.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import at.fh.swenga.model.SmartphoneModel;
import at.fh.swenga.model.SmartphoneService;



/**
 * Servlet implementation class ChangeSmartphone
 */
@WebServlet("/changeSmartphone")
public class ChangeSmartphone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeSmartphone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// get the http session object for the user
		HttpSession session = request.getSession(true);
 
		// get the smartphoneService out of the session
		SmartphoneService smartphoneService = (SmartphoneService) session.getAttribute("smartphoneService");
 
		// get all form data out of the request	
		String idString = request.getParameter("id");
		String name = request.getParameter("name");
		String brand = request.getParameter("brand");
		String dateString = request.getParameter("releaseDate");
		String priceString = request.getParameter("price");
 
		// provide a String to collect all error messages
		String errorMessage = "";
		boolean errorOccurred = false;
 
		// ---- Convert Id ----
		// if Id is not a number -> add an error text to the error message
		int id = 0;
		try {
			id = Integer.parseInt(idString);
		} catch (Exception e) {
			errorMessage += "ID invalid<br>";
			errorOccurred = true;
		}
 
		// ---- Convert Release Date -----
		// if it is not a date-> add an error text to the error message
		Date releaseDate = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			releaseDate = sdf.parse(dateString);
		} catch (Exception e) {
			errorMessage += "Date invalid<br>";
			errorOccurred = true;
		}
		
		//---- Convert Price ----
		int price = 0;
		try {
			price = Integer.parseInt(priceString);
		} catch (Exception e) {
			errorMessage += "Price invalid<br>";
			errorOccurred = true;
		}
 
		// Data Conversion ok? -> Change Smartphone
		if (!errorOccurred) {
			SmartphoneModel smartphone = smartphoneService.getSmartphoneById(id);
		
			if (smartphone == null) {
				errorMessage += "Smartphone doesn't exist!<br>";
				errorOccurred = true;
			} else { 
				// overwrite data in the existing smartphone object
				smartphone.setName(name);
				smartphone.setBrand(brand);
				smartphone.setReleaseDate(releaseDate);
				smartphone.setPrice(price);
			}
		}
		// --- Create a message for the JSP
		if (!errorOccurred) {
			// No error? Then put a message on the page that smartphone has changed
			request.setAttribute("message", "Smartphone " + id + " changed.");
		} else {
			// Errors happened? -> put all collected error messages in the request for the JSP
			request.setAttribute("errorMessage", errorMessage);
		}
	
		RequestDispatcher rd = request.getRequestDispatcher("./listSmartphones");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
