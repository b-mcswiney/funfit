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
		<form action="AddBatch" method="post">
			<div>
		      <label for="startTime">Start Time:</label>
		      <input type="date" id="startTime" name="startTime">
		    </div>
			<div>
		      <label for="endTime">End Time:</label>
		      <input type="date" id="endTime" name="endTime">
		    </div>
			<div>
		      <label for="trainerName">Trainer name:</label>
		      <input type="text" id="trainerName" placeholder="Enter name" name="trainerName">
		    </div>
		    <div>
		    	<input type="submit">
		    </div>
		</form>
	</div>
</body>
</html>