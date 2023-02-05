package example01.flightapp01.flight.web;

import java.io.IOException;
import java.util.Optional;

import example01.flightapp01.flight.Flight;
import example01.flightapp01.flight.FlightService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageFlightEditForm
 */
@WebServlet(name = "AdminApp01_PageFlightEditForm",
urlPatterns = {"/adminapp01/flight/edit-form.html"})
public class PageFlightEditForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageFlightEditForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		FlightService service = new FlightService();
		Optional<Flight> flightOptional = service.findById(id);
		
		Flight flight = flightOptional.get();
		
		request.setAttribute("flight",flight);
		
		request.getRequestDispatcher("header.html").include(request, response);
		request.getRequestDispatcher("base-form.html").include(request, response);
		request.getRequestDispatcher("footer.html").include(request, response);	
	}

}
