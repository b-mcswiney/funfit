package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Batch;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dao.BatchDatabase;


@WebServlet("/AddBatch")
public class AddBatch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post - Add Batch");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String trainerName = request.getParameter("trainerName");
		Date startDate = null;
		Date endDate = null;
		System.out.println(startTime);
		System.out.println(endTime);
		System.out.println(trainerName);
		
		if((startTime.isEmpty() || startTime != null)&&(endTime.isEmpty() || endTime != null)&&(trainerName.isEmpty() || trainerName != null)) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
			try {
				startDate = formatter.parse(startTime);
				endDate = formatter.parse(endTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Batch newBatch = new Batch(startDate, endDate, trainerName);
			BatchDatabase db = new BatchDatabase();
			if(db.addBatch(newBatch)) {
				RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
				rd.include(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("addBatch.jsp");
				PrintWriter out = response.getWriter();
				out.println("Insert Failed");
				rd.include(request, response);
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("addBatch.jsp");
			request.setAttribute("error","Please fill in all details");
			rd.include(request, response);
		}
		
		response.sendRedirect("admin.jsp");
	}

}
