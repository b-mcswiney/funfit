<%@page import="dao.BatchDatabase"%>
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
	<div>
		<form action="AddParticipant" method="post">
			<div>
		      <label for="name">Name:</label>
		      <input type="text" id="name" placeholder="Enter Name" name="name">
		    </div>
			<div>
		      <label for="phoneNumber">Phone number:</label>
		      <input type="text" id="phoneNumber" placeholder="Enter Phone Number" name="phoneNumber">
		    </div>
			<div>
		      <label for="email">Email:</label>
		      <input type="text" id="email" placeholder="Enter Email" name="email">
		    </div>
			<div>
		      <label for="bid">Batch Number</label>
		      <select id="bid" name="bid">
		      	<%
		      		ArrayList<Batch> batches = new BatchDatabase().getBatches();
		    		
		    		for(Batch b : batches){
		      	%>
		      		<option value="<%=b.getBid()%>"><%=b.getBid()%></option>
		      	<%
		    		}
		      	%>
		      </select>
		    </div>
		    <div>
		    	<input type="submit">
		    </div>
		</form>
	</div>
</body>
</html>