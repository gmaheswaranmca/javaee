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
 * Servlet implementation class PageFlightTable
 */
@WebServlet(name = "AdminApp01_PageFlightTable",
urlPatterns = {"/adminapp01/flight/table.html"})
public class PageFlightTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public PageFlightTable() {
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
			String liElement = "						<tr>\r\n"
					+ "					        <td>" + flight.getNumber() + "</td>\r\n"
					+ "					        <td>" + flight.getAirlineName() + "</td>\r\n"
					+ "					        <td>" + flight.getSource() + "</td>\r\n"
					+ "					        <td>" +  flight.getDestination() + "</td>\r\n"
					+ "					        <td>\r\n"
					+ "							<form action=\"" + pagePath + "/edit-form.html\" method=\"post\"/>\r\n"
					+ "								<input type=\"hidden\" \r\n"
					+ "									name=\"id\" "
					+ "									value=\"" + flight.getId() + "\">\r\n"
					+ "								<button\r\n"
					+ "									type=\"submit\"\r\n"
					+ "									class=\"badge badge-success\">Edit</button>\r\n"
					+ "							</form>\r\n"
					+ "					        </td>\r\n"
					+ "					      </tr>";
		
			//
			uiInner += liElement;
		}
		
		//...
		PrintWriter out = response.getWriter();
		String pageHtml = "				<div class=\"container mt-3\">\r\n"
				+ "					<h4>Flight List</h4>\r\n"
				+ "					  <table class=\"table table-sm table-hover table-bordered\">\r\n"
				+ "					    <thead>\r\n"
				+ "					      <tr class=\"table-primary\">\r\n"
				+ "					        <th>Flight#</th>\r\n"
				+ "					        <th>Airline</th>\r\n"
				+ "					        <th>Source</th>\r\n"
				+ "					        <th>Destination</th>\r\n"
				+ "					        <th>&nbsp;</th>\r\n"
				+ "					      </tr>\r\n"
				+ "					    </thead>\r\n"
				+ "					    <tbody>"
				+ uiInner
				+ "					    </tbody>\r\n"
				+ "					  </table>"		
				+ "				</div><!-- container mt-3 -->";
		
		//
		request.getRequestDispatcher("header.html").include(request, response);
		out.println(pageHtml);
		request.getRequestDispatcher("footer.html").include(request, response);		
	}

}
