package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Participant;

import java.io.IOException;
import java.io.PrintWriter;

import dao.ParticipantDatabase;

/**
 * Servlet implementation class AddParticipant
 */
@WebServlet("/AddParticipant")
public class AddParticipant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post - Add Participant");
		String name = request.getParameter("name");
		String phone = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String bid = request.getParameter("bid");
		System.out.println(name);
		System.out.println(phone);
		System.out.println(email);
		System.out.println(bid);
		
		response.setContentType("text/html");
		
		if((name.isEmpty()|| name != null)&&(phone.isEmpty() || phone != null)&&(email.isEmpty() || email != null)&&(bid.isEmpty() || bid != null)) {
			Participant newParticipant = new Participant(name, phone, email, Integer.parseInt(bid));
			
			ParticipantDatabase db = new ParticipantDatabase();
			if(db.addParticipant(newParticipant)) {
				RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
				rd.include(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("addParticipant.jsp");
				PrintWriter out = response.getWriter();
				out.println("Insert Failed");
				rd.include(request, response);
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("addParticipant.jsp");
			request.setAttribute("error","Please fill in all details");
			rd.include(request, response);
		}
		
		response.sendRedirect("admin.jsp");
	}

}
