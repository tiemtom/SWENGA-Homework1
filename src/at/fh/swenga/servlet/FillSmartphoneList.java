package at.fh.swenga.servlet;

import java.io.IOException;
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
 * Servlet implementation class FillSmartphoneList
 */
@WebServlet("/fillSmartphoneList")
public class FillSmartphoneList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FillSmartphoneList() {
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
		if (smartphoneService == null) {
			smartphoneService = new SmartphoneService();
			session.setAttribute("smartphoneService", smartphoneService);
		}
		Date now = new Date();
										  //SmartphoneModel(id, name, brand, releaseDate, price)
		smartphoneService.addSmartphone(new SmartphoneModel(smartphoneService.getSize()+1,"Galaxy S10","Samsung", now, 1));
		smartphoneService.addSmartphone(new SmartphoneModel(smartphoneService.getSize()+1,"P30 Pro","Huawei", now, 1));
		smartphoneService.addSmartphone(new SmartphoneModel(smartphoneService.getSize()+1,"Mi 9","Xiaomi", now, 1));
		smartphoneService.addSmartphone(new SmartphoneModel(smartphoneService.getSize()+1,"iPhone 10","Apple", now, 1));
		smartphoneService.addSmartphone(new SmartphoneModel(smartphoneService.getSize()+1,"P20 Pro","Huawei", now, 1));
		smartphoneService.addSmartphone(new SmartphoneModel(smartphoneService.getSize()+1,"Galaxy A7","Samsung", now, 1));
		
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
