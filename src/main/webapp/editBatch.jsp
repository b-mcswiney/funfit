<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Edit batch!</h1>
	<div>
		<form action="EditBatch" method="post">
			<div>
				<label for="bid">Batch id:</label> <input type="text" id="bid"
					value="${batch.bid }" name="bid" readonly="readonly">
			</div>
			<div>
				<label for="startTime">Start date: </label> <input type="date" id="startTime"
					value="${batch.starttime}" name="startTime">
			</div>
			<div>
				<label for="endTime">end date: </label> <input type="date" id="endTime"
					value="${batch.endtime}" name="endTime">
			</div>
			<div>
				<label for="trainerName">Trainer Name:</label> <input type="text" id="trainerName"
					value="${batch.trainerName}" name="trainerName">
			</div>
			<input type="submit">
		</form>
	</div>
</body>
</html>