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

@WebServlet("/EditParticipant")
public class EditParticipant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditParticipant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		System.out.println(pid);
		response.setContentType("text/html");
		if((!pid.isEmpty() && pid != null)){
			ParticipantDatabase db = new ParticipantDatabase();
			
			Participant participant = db.getParticipant(Integer.parseInt(pid));
			if(participant!=null)
			{
				RequestDispatcher rd = request.getRequestDispatcher("editParticipant.jsp");
				request.setAttribute("participant", participant);
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
		System.out.println("Post - Edit participant");
		String pid = request.getParameter("pid");
		String name = request.getParameter("name");
		String phone = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String bid = request.getParameter("bid");
		System.out.println(pid);
		System.out.println(name);
		System.out.println(phone);
		System.out.println(email);
		System.out.println(bid);
		
		if((name.isEmpty()|| name != null)&&(phone.isEmpty() || phone != null)&&(email.isEmpty() || email != null)&&(bid.isEmpty() || bid != null)) {
			Participant updatedParticipant = new Participant(Integer.parseInt(pid), name, phone, email, Integer.parseInt(bid));
			
			ParticipantDatabase db = new ParticipantDatabase();
			if(db.updateParticipant(updatedParticipant)) {
				System.out.println("Update Success");
				RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
				rd.include(request, response);
			}else {
				System.out.println("Upate failed");
				RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
				PrintWriter out = response.getWriter();
				out.println("Insert Failed");
				rd.include(request, response);
			}
		} else {
			System.out.println("Request not fully filled");
			RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
			request.setAttribute("error","Please fill in all details");
			rd.include(request, response);
		}
		
		response.sendRedirect("admin.jsp");
	}

}
