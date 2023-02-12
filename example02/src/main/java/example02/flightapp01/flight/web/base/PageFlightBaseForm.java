package example02.flightapp01.flight.web.base;

import java.io.IOException;
import java.io.PrintWriter;

import example02.flightapp01.flight.Flight;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageFlightBaseForm
 */
@WebServlet(name = "AdminApp01_PageFlightBaseForm",
	urlPatterns = {"/adminapp01/flight/base-form.html"})
public class PageFlightBaseForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageFlightBaseForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Flight flight = (Flight)request.getAttribute("flight");
		boolean isCreate = flight.getId() == 0;
		
		String pagePath = request.getContextPath() + request.getServletPath(); 
		pagePath = pagePath.substring(0, pagePath.lastIndexOf("/"));
		
		PrintWriter out = response.getWriter();
		String deleteButton = "						<form action=\""  + pagePath + "/delete-action.html\" method=\"post\"/ style='text-align:right;'>\r\n"
				+ "								<input type=\"hidden\" \r\n"
				+ "									name=\"id\" value=\"" + flight.getId() + "\">\r\n"
				+ "								<button\r\n"
				+ "									type=\"submit\"\r\n"
				+ "									class=\"badge badge-warning\">Delete</button>\r\n"
				+ "						</form>\r\n";
		if(isCreate) {
			deleteButton = "";
		}
		
		String createUiPre = "								<input type=\"hidden\" \r\n"
				+ "									name=\"action\" value=\"new\">\r\n";
		
		String editUiPre = "								<input type=\"hidden\" \r\n"
				+ "									name=\"action\" value=\"edit\">\r\n";
		
		String createUiPost = "							<button\r\n"
				+ "								type=\"submit\"\r\n"
				+ "								class=\"badge badge-success\">Create</button>\r\n";
		
		String editUiPost = "							<button\r\n"
				+ "								type=\"submit\"\r\n"
				+ "								class=\"badge badge-success\">Update</button>\r\n";
		
		
		
		String uiPre = isCreate ? createUiPre : editUiPre;
		String uiPost = isCreate ? createUiPost : editUiPost;
		String title = isCreate ? "Create Flight" : "Edit Flight";
		
		String pageHtml = "<div class=\"container mt-3\">\r\n"
				+ "					<div class=\"edit-form\">\r\n"
				+ "						<h4>" + title + "</h4>\r\n"
				+ "						<form action=\"" + pagePath + "/form-action.html\" method=\"post\">\r\n"
				+ "								<input type=\"hidden\" \r\n"
				+ "									name=\"id\" value=\"" + flight.getId() + "\">\r\n"
				+ uiPre
				+ "								<div class=\"form-group\">\r\n"
				+ "										<label for=\"airlineName\">Airline Name</label>\r\n"
				+ "										<input type=\"text\"\r\n"
				+ "												class=\"form-control\"\r\n"
				+ "												id=\"airlineName\"\r\n"
				+ "												name=\"airlineName\"\r\n"
				+ "												required\r\n"
				+ "												value=\""+ flight.getAirlineName() +"\"/>\r\n"
				+ "								</div>\r\n"
				+ "								<div class=\"form-group\">\r\n"
				+ "										<label for=\"number\">Flight Number</label>\r\n"
				+ "										<input type=\"text\"\r\n"
				+ "												class=\"form-control\"\r\n"
				+ "												id=\"number\"\r\n"
				+ "												name=\"number\"\r\n"
				+ "												required\r\n"
				+ "												value=\"" + flight.getNumber() + "\"/>\r\n"
				+ "								</div>\r\n"
				+ "								<div class=\"form-group\">\r\n"
				+ "									<label for=\"source\">Source</label>\r\n"
				+ "									<input type=\"text\"\r\n"
				+ "											class=\"form-control\"\r\n"
				+ "											id=\"source\"\r\n"
				+ "											required\r\n"
				+ "											value=\"" + flight.getSource() + "\"\r\n"
				+ "											name=\"source\"/>\r\n"
				+ "							</div>\r\n"
				+ "							<div class=\"form-group\">\r\n"
				+ "								<label for=\"destination\">Destination</label>\r\n"
				+ "								<input type=\"text\"\r\n"
				+ "										class=\"form-control\"\r\n"
				+ "										id=\"destination\"\r\n"
				+ "										required\r\n"
				+ "										value=\"" + flight.getDestination() + "\"\r\n"
				+ "										name=\"destination\"/>\r\n"
				+ "							</div>\r\n"
				+ uiPost
				+ "						</form>\r\n"
				+ deleteButton
				+ "					</div>\r\n"
				+ "				</div>";
		out.println(pageHtml);
	}

}
