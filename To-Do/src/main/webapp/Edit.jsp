<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Page</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%	int taskId=Integer.parseInt(request.getParameter("id")); %>
	<form action="UpdateTask" method="post">
		<div>
		<label>TaskId:</label>
		<input type="number" name="id" value="<%=taskId%>" readonly required/>
		</div>
		<div>
		<label>Task Name:</label>
		<input type="text" name="tname" placeholder="Updated Task-Name" required/>
		</div>
		<div>
		<label>Due:</label>
		<input type="date" id="due" name="due" required/>
		</div>
		<div>
		<label>Status</label>
		<input type="radio" name="status" value="true" required/>Completed
		<input type="radio" name="status" value="false" required/>In-Complete
		<br>
		<button type="submit">Submit</button>
		<button type="reset">Reset</button>
		</div>
	</form>
</body>
</html>