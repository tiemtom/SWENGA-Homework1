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
 * Servlet implementation class ListSmartphones
 */
@WebServlet("/listSmartphones")
public class ListSmartphones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListSmartphones() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the http session object for the user
		HttpSession session = request.getSession(true);
		
		// SmartphoneService in the session? if not, create a new SmartphoneService and put it into the session
		SmartphoneService smartphoneService =(SmartphoneService)session.getAttribute("smartphoneService");	
		
		if (smartphoneService==null) {
			smartphoneService=new SmartphoneService();
			session.setAttribute("smartphoneService", smartphoneService);
		}
		request.setAttribute("smartphones", smartphoneService.getAllSmartphones());
		
		RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
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
