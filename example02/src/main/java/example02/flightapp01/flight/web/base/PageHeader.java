package example02.flightapp01.flight.web.base;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageHeader
 */
@WebServlet(name = "AdminApp01_PageHeader",
	urlPatterns = {"/adminapp01/flight/header.html"})
public class PageHeader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageHeader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagePath = request.getContextPath() + request.getServletPath(); 
		pagePath = pagePath.substring(0, pagePath.lastIndexOf("/"));
		
		PrintWriter out = response.getWriter();
		String headerHtml = "<!doctype html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "	<meta charset=\"utf-8\">\r\n"
				+ "	<title>Aviation System - Admin App</title>\r\n"
				+ "	<base href=\"/\">\r\n"
				+ "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "	<link rel=\"icon\" type=\"image/x-icon\" href=\"favicon.ico\">\r\n"
				+ "	<link rel=\"stylesheet\" \r\n"
				+ "		href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css\" \r\n"
				+ "		integrity=\"sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N\" \r\n"
				+ "		crossorigin=\"anonymous\">\r\n"
				+ "</head>\r\n"
				+ "	<body>\r\n"
				+ "		<div id=\"root\">\r\n"
				+ "			<div id=\"main\">\r\n"
				+ "				<nav class=\"navbar navbar-expand navbar-dark bg-dark\">\r\n"
				+ "					<a href=\"" + pagePath + "/table.html\" class=\"navbar-brand\">Flights App</a>\r\n"
				+ "					<div class=\"navbar-nav mr-auto\">\r\n"
				+ "						<li class=\"nav-item\">\r\n"
				+ "							<a href=\"" + pagePath + "/table.html\" class=\"nav-link\">Flights</a>\r\n"
				+ "						</li>\r\n"
				+ "						<li class=\"nav-item\">\r\n"
				+ "							<a href=\"" + pagePath + "/list.html\" class=\"nav-link\">List</a>\r\n"
				+ "						</li>\r\n"
				+ "						<li class=\"nav-item\">\r\n"
				+ "							<a href=\"" + pagePath + "/create-form.html\" class=\"nav-link\">Add Flight</a>\r\n"
				+ "						</li>\r\n"
				+ "					</div>\r\n"
				+ "				</nav>";
		out.println(headerHtml);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
