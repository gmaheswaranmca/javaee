package example02.flightapp01.flight.web;

import java.io.IOException;

import example02.flightapp01.flight.Flight;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageFlightCreateForm
 */
@WebServlet(name = "AdminApp01_PageFlightCreateForm",
urlPatterns = {"/adminapp01/flight/create-form.html"})
public class PageFlightCreateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageFlightCreateForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Flight flight = new Flight();
		flight.setId(0);
		flight.setNumber("");
		flight.setAirlineName("");
		flight.setSource("");
		flight.setDestination("");
		request.setAttribute("flight", flight);
		
		request.getRequestDispatcher("header.html").include(request, response);
		request.getRequestDispatcher("base-form.html").include(request, response);
		request.getRequestDispatcher("footer.html").include(request, response);	
	}

}
