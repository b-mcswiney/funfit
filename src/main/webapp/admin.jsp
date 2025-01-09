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
<title>Insert title here</title>
</head>
<body>
	<h1>Participant's List :</h1>
	<table class='table'>
		<tr>
			<th>Pid</th>
			<th>Name</th>
			<th>Phone</th>
			<th>Email</th>
			<th>Bid</th>
			<th>Start time</th>
			<th>End time</th>
			<th>Trainer Name</th>
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
			<td><%=b.getStarttime()%></td>
			<td><%=b.getEndtime()%></td>
			<td><%=b.getTrainerName()%></td>
		</tr>
		<%} %>
	</table>
</body>
</html>