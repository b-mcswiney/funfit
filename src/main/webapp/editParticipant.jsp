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
	<h1>Edit Participant!</h1>
	<form action="EditParticipant" method="post">
		<div>
			<label for="pid">Pid:</label> <input type="text" id="pid"
				value="${participant.pid }" name="pid" readonly="readonly">
		</div>
		<div>
			<label for="name">Name:</label> <input type="text" id="name"
				value="${participant.name }" name="name">
		</div>
		<div>
			<label for="phoneNumber">Phone Number:</label> <input type="text"
				id="phoneNumber" value="${participant.phone }" name="phoneNumber">
		</div>
		<div>
			<label for="email">Email:</label> <input type="text" id="email"
				value="${participant.email }" name="email">
		</div>
		<div>
		      <label for="bid">Batch ID: </label>
		      <select id="bid" name="bid">
		      	<%
		      		ArrayList<Batch> batches = new BatchDatabase().getBatches();
		    		Participant participant = new ParticipantDatabase().getParticipant(Integer.parseInt(request.getParameter("pid")));
		    		
		    		for(Batch b : batches){
		    			if(b.getBid() == participant.getBid()){
		      	%>
		      				<option selected value="<%=b.getBid()%>"><%=b.getBid()%></option>
		      	<%
		    			}else{
		      	%>
		      				<option value="<%=b.getBid()%>"><%=b.getBid()%></option>
		      	<%
		      			}
		    		}
		      	%>
		      </select>
		    </div>
		<input type="submit">
	</form>
</body>
</html>