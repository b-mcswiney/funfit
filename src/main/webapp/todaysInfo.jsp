<%@page import="dao.ParticipantDatabase"%>
<%@page import="dao.BatchDatabase"%>
<%@page import="model.Participant"%>
<%@page import="model.Batch"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Today's data</title>
<style type="text/css">
	th, td {
  		padding: 15px;
	}
	table, th, td{
		border: 1px solid black;
		border-collapse: collapse;

	}
	table{
		margin-left: auto;
  		margin-right: auto;
	}
</style>
</head>
<body>
<a href="index.html">Home page</a>
<div style="text-align: center;">
	<h1>TODAY'S INFO</h1>
	<table>
		<tr>
			<th>Pid</th>
			<th>Participant Name</th>
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
					Date today = new Date();
					
					if(today.after(b.getStarttime()) && today.before(b.getEndtime())){
					
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
		<%
					}
				}
		%>
	</table>
</div>
</body>
</html>