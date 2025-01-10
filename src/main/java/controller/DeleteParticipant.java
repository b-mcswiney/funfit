package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import dao.ParticipantDatabase;

/**
 * Servlet implementation class DeleteParticipant
 */
@WebServlet("/DeleteParticipant")
public class DeleteParticipant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteParticipant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid= request.getParameter("pid");
		response.setContentType("text/html");
		if((!pid.isEmpty() && pid != null)){
			ParticipantDatabase database = new ParticipantDatabase();
			
			if(database.deleteParticipant(Integer.parseInt(pid)))
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
