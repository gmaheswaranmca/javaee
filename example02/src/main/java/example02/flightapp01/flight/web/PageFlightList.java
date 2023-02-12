package example02.flightapp01.flight.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import example02.flightapp01.flight.Flight;
import example02.flightapp01.flight.FlightService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FlightList
 */
@WebServlet(name = "AdminApp01_PageFlightList",
		urlPatterns = {"/adminapp01/flight/list.html"})
public class PageFlightList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageFlightList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FlightService service = new FlightService();
		List<Flight> flights = service.findAll();
		
		String pagePath = request.getContextPath() + request.getServletPath(); //	"/example01"	+ "/adminapp01/flight/list.html"
		pagePath = pagePath.substring(0, pagePath.lastIndexOf("/"));		   // 	"/example01/adminapp01/flight"
		
		String uiInner = ""; //"<p>" +  pagePath + "</p>";//???
		
		for(Flight flight: flights) {
			String liElement = "						<li\r\n"
					+ "						*ngFor=\"let flight of flights; let I = index\"\r\n"
					+ "						class=\"list-group-item\"\r\n"
					+ "						[class.active]= \"I === currentIndex\">\r\n"
					+ "							" + flight.getNumber() + " \r\n"
					+ "							(" + flight.getAirlineName() + "):\r\n"
					+ "							" + flight.getSource() + " - \r\n"
					+ "							" +  flight.getDestination() + "\r\n"
					+ "							<form action=\"" + pagePath + "/edit-form.html\" method=\"post\"/>\r\n"
					+ "								<input type=\"hidden\" \r\n"
					+ "									name=\"id\" "
					+ "									value=\"" + flight.getId() + "\">\r\n"
					+ "								<button\r\n"
					+ "									type=\"submit\"\r\n"
					+ "									class=\"badge badge-success\">Edit</button>\r\n"
					+ "							</form>"
					+ "						</li>\r\n";
			//
			uiInner += liElement;
		}
		
		//...
		PrintWriter out = response.getWriter();
		String pageHtml = "				<div class=\"container mt-3\">\r\n"
				+ "					<h4>Flight List</h4>\r\n"
				+ "					<ul class=\"list-group\">\r\n"
				+ uiInner
				+ "					</ul>\r\n"
				+ "				</div><!-- container mt-3 -->";
		
		//
		request.getRequestDispatcher("header.html").include(request, response);
		out.println(pageHtml);
		request.getRequestDispatcher("footer.html").include(request, response);		
	}

}
