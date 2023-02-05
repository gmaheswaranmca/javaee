package example01.flightapp01.flight.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import example01.flightapp01.flight.Flight;
import example01.flightapp01.flight.FlightService;

/**
 * Servlet implementation class PageActionDelete
 */
@WebServlet(name = "AdminApp01_PageActionDelete",
urlPatterns = {"/adminapp01/flight/delete-action.html"})
public class PageActionDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageActionDelete() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		FlightService service = new FlightService();
		service.deleteById(id);
		
		response.sendRedirect("list.html");
	}

}
