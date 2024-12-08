package com.crud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Controller.TodoDao;

/**
 * Servlet implementation class Delete
 */
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method=request.getParameter("_method");
		int taskId=Integer.parseInt(request.getParameter("taskId"));
		List<Task> list=null;
//		PrintWriter out=response.getWriter();
		List<String> errors=new ArrayList<>();
		int stat=0;
		if(method.equals("DELETE")){
			Task tsk=new Task(taskId);
			try{
				stat=TodoDao.DeleteTask(tsk.getId());
			}
			catch(SQLException e) {
				 e.printStackTrace(); 
			}
			
		}String message;
		if(stat>0) {
			message="Record Deleted Successfully";
			request.setAttribute("Errors",errors);
        	
        	request.setAttribute("tasks",list);
		}
		else {
			message="Record Deletion Failed";
			
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("View").include(request, response);
//		doGet(request, response);
	}

}
