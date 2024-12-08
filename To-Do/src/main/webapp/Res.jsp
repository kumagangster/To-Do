<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="models.Task" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result Page</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h3><% out.println(request.getAttribute("message")); %></h3>
	<%	
        List<String> errors = (List<String>) request.getAttribute("Errors");
        if (errors != null && !errors.isEmpty()) {
            for (String error : errors) {
                out.println(error);
                
            }
        }
    %>
    <h1>List Of Tasks</h1>
    <span id="list">
    	<%
    	
    	 List<Task> tasks = (List<Task>) request.getAttribute("tasks");
    		if(tasks==null || tasks.isEmpty()){
    			out.println("<h3 id='notaskbar'>No Task In Due/Add More Task</h3>");
    		}
    		else{ 
    		%>
    			<table id="tasktab">
                <thead>
                    <tr>
                        <th>Task ID</th>
                        <th>Task Name</th>
                        <th>Due Date</th>
                        <th>Status</th>
                        <th>Operation</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Task task : tasks) {
                    %>
                   		<%-- <tr <% if(task.get_status()==true)out.println("id='completed'");%>> --%>
                   		<tr>
                            <td><%= task.getId()%></td>
                            <td><%= task.getTask_name() %></td>
                            <td><%= task.get_Due() %></td>
                            <td><%= task.get_status() %></td>
                            <td>
                            <div><form action="Update" method="POST">
                            <input type="hidden" name="_method" value="PUT"/>
                            <input type="hidden" name="taskId" value="<%=task.getId()%>">
                            <input type="hidden" name="status" value="<%=task.get_status()%>">
                            <button type="submit" id="Completion">Mark as <%if(task.get_status()==true){ out.println("In-Complete");}else out.println("Complete"); %>
                            </button>
                            </form>
                            <form action="Delete" method="POST">
                            <input type="hidden" name="_method" value="DELETE"/>
                            <input type="hidden" name="taskId" value="<%=task.getId()%>">
                            <button type="submit" id="Deletion">Delete</button>
                            </form>
                            <form action="UpdateTask" method="GET">
                            <input type="hidden" name="id" value="<%=task.getId() %>"/>
                            <button type="submit" id="UpdateTask">Edit</button>
                            </form>
                            </div>
                            </td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
    			</table>
    			
    	<%	}
    	%>
    </span>
</body>
</html>