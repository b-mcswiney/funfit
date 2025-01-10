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

/**
 * Servlet implementation class EditBatch
 */
@WebServlet("/EditBatch")
public class EditBatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bid = request.getParameter("bid");
		System.out.println(bid);
		response.setContentType("text/html");
		if((!bid.isEmpty() && bid != null)){
			BatchDatabase db = new BatchDatabase();
			
			Batch batch = db.getBatch(Integer.parseInt(bid));
			if(batch!=null)
			{
				RequestDispatcher rd = request.getRequestDispatcher("editBatch.jsp");
				request.setAttribute("batch", batch);
				rd.include(request, response);
			}
		}
		else{
			response.sendRedirect("admin.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bid = request.getParameter("bid");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String trainerName = request.getParameter("trainerName");
		Date startDate = null;
		Date endDate = null;
		System.out.println(bid);
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
			
			Batch updatedBatch = new Batch(Integer.parseInt(bid), startDate, endDate, trainerName);
			BatchDatabase db = new BatchDatabase();
			if(db.updateBatch(updatedBatch)) {
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
			
		}
		
		response.sendRedirect("admin.jsp");
		
	}

}
