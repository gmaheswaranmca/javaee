package example02.flightapp01.flight.web;

import java.io.IOException;

import example02.flightapp01.flight.Flight;
import example02.flightapp01.flight.FlightService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageActionFlightForm
 */
@WebServlet(name = "AdminApp01_PageActionFlightForm",
urlPatterns = {"/adminapp01/flight/form-action.html"})
public class PageActionFlightForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageActionFlightForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String number = request.getParameter("number");
		String airlineName = request.getParameter("airlineName");
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		
		Flight flight = new Flight();
		flight.setId(id);
		flight.setNumber(number);
		flight.setAirlineName(airlineName);
		flight.setSource(source);
		flight.setDestination(destination);
		
		FlightService service = new FlightService();
		Flight savedFlight = service.save(flight);
		
		response.sendRedirect("table.html");
	}

}
