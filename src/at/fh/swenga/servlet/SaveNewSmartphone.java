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
 * Servlet implementation class SaveNewSmartphone
 */
@WebServlet("/saveNewSmartphone")
public class SaveNewSmartphone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveNewSmartphone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		String name = request.getParameter("name");
		String brand = request.getParameter("brand");
		String dateString = request.getParameter("releaseDate");
		String priceString = request.getParameter("price");
		
		String errorMessage = "";
		boolean errorOccurred = false;

		//---- Convert Id ----
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
			releaseDate=sdf.parse(dateString);
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
		
		if (!errorOccurred) {
			 
			// get the http session object for the user
			HttpSession session = request.getSession(true);
 
			// smartphoneService in the session? if not, create a new SmartphoneService and put it into the session
			SmartphoneService smartphoneService = (SmartphoneService) session.getAttribute("smartphoneService");
			if (smartphoneService==null) {
				smartphoneService=new SmartphoneService();
				session.setAttribute("smartphoneService", smartphoneService);
			}
 
			SmartphoneModel smartphone = smartphoneService.getSmartphoneById(id);
 
			if (smartphone != null) {
				errorMessage += "Smartphone already exists!<br>";
				errorOccurred = true;
			} else {
				SmartphoneModel sm = new SmartphoneModel(id, name, brand, releaseDate, price);
				smartphoneService.addSmartphone(sm);
			}
		}
			
		if (!errorOccurred) {
			request.setAttribute("message", "New Smartphone " + id + " added.");
		}
		else {
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
