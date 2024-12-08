package com.crud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Task;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Controller.TodoDao;

/**
 * Servlet implementation class UpdateTask
 */
public class UpdateTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTask() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request,HttpServletResponse response) 
    		throws ServletException, IOException { 

		response.setContentType("text/html;charset=UTF-8"); 
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher("Edit.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int id=Integer.parseInt(request.getParameter("id"));
//		processRequest(request,response);
//		PrintWriter out=response.getWriter();
		List<String> errors=new ArrayList<String>();
		String task_name=request.getParameter("tname");
		String due=request.getParameter("due");
		boolean status=Boolean.parseBoolean(request.getParameter("status"));
		
		Date dueDate=null;
		 try {
	            dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(due);
	            if (dueDate.before(new Date())){
	                errors.add("Due date cannot be in the past");
	            }
	        } catch (Exception e) {
	            errors.add("Invalid due date format");
	        }
		
		Task tsk=new Task(id);
		tsk.setTask_name(task_name);
		tsk.set_status(status);
		tsk.set_Due(new java.sql.Date(dueDate.getTime()));
		int stat=0;
		try {
			stat=TodoDao.UpdateTask(tsk);
//			out.println("Hi");
			
		}catch(SQLException e) {
//			out.println("Hello");
//			out.println(e);
		}
		if(stat>0) {
			String message="Record Updated and Saved Successfully";
			request.setAttribute("Errors",errors);
        	request.setAttribute("message", message);
		}
		
		else errors.add("Record Updation Failed");
		request.getRequestDispatcher("View").forward(request, response);
//		doGet(request, response);
	}
}
