package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import dao.BatchDatabase;
import dao.ParticipantDatabase;

/**
 * Servlet implementation class deleteBatch
 */
@WebServlet("/DeleteBatch")
public class DeleteBatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bid= request.getParameter("bid");
		response.setContentType("text/html");
		if((!bid.isEmpty() && bid != null)){
			BatchDatabase database = new BatchDatabase();
			
			if(database.deleteBatch(Integer.parseInt(bid)))
			{
				response.sendRedirect("admin.jsp");
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
				PrintWriter out = response.getWriter();
				out.println("Could not delete");
				rd.include(request, response);
			}
		}
		else{
			response.sendRedirect("admin.jsp");
		}
	}

}
