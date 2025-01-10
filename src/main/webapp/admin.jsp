<%@page import="dao.ParticipantDatabase"%>
<%@page import="dao.BatchDatabase"%>
<%@page import="model.Participant"%>
<%@page import="model.Batch"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
</head>
<body>
	<h1>Participant's Table :</h1>
	<a href="addParticipant.jsp">Add participant</a>
	<table class='table'>
		<tr>
			<th>Pid</th>
			<th>Name</th>
			<th>Phone</th>
			<th>Email</th>
			<th>Bid</th>
		</tr>
		<%
				BatchDatabase batchDB = new BatchDatabase();
				ArrayList<Participant> participants = new ParticipantDatabase().getParticipants();
				for (Participant p : participants) {
					Batch b = batchDB.getBatch(p.getBid());
					
		%>
		<tr>
			<td><%=p.getPid()%></td>
			<td><%=p.getName()%></td>
			<td><%=p.getPhone()%></td>
			<td><%=p.getEmail()%></td>
			<td><%=p.getBid()%></td>
			<td><a href='EditParticipant?pid=<%=p.getPid()%>'>Edit</a></td>
			<td><a href='DeleteParticipant?pid=<%=p.getPid()%>'>Delete</a></td>
		</tr>
		<%} %>
	</table>
	<h1>Batch Table :</h1>
	<a href="addBatch.jsp">Add Batch</a>
	<table>
		<tr>
			<th>Bid</th>
			<th>Start Date</th>
			<th>End Date</th>
			<th>Trainer name</th>
		</tr>
		<%
				ArrayList<Batch> batches = new BatchDatabase().getBatches();
				for (Batch b : batches) {
					
		%>
		<tr>
			<td><%= b.getBid() %></td>
			<td><%= b.getStarttime() %></td>
			<td><%= b.getEndtime() %></td>
			<td><%= b.getTrainerName() %></td>
			<td><a href='EditBatch?bid=<%=b.getBid()%>'>Edit</a></td>
			<td><a href='DeleteBatch?bid=<%=b.getBid()%>'>Delete</a></td>
		</tr>
		<%} %>
	</table>
</body>
</html>