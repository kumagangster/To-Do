package com.crud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Task;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Controller.TodoDao;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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
		
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		processRequest(request,response);
		String method=request.getParameter("_method");
		List<Task> list=null;
//		PrintWriter out=response.getWriter();
		List<String> errors=new ArrayList<>();
		int stat=0;
		int taskId=Integer.parseInt(request.getParameter("taskId"));
		boolean status=Boolean.parseBoolean(request.getParameter("status"));
//		out.println(method);
//		out.println(taskId);
//		out.println(status);
		if(method.equals("PUT")) {
//			PrintWriter out=response.getWriter();
			
			Task tsk=new Task(taskId);
			tsk.set_status(!status);
	        try {
//	        	out.println(taskId);  
	        	stat=TodoDao.UpdateStatus(tsk.getId(),tsk.get_status());
//	        	list=TodoDao.GetAllTask();
//	        	out.println("Hi");
	        } catch (SQLException e) { 
	            e.printStackTrace(); 
	        } 
		}
		
		if(stat>0) {
			String message="Record Saved Successfully";
			request.setAttribute("Errors",errors);
        	request.setAttribute("message", message);
        	request.setAttribute("tasks",list);
		}
		else errors.add("Record Updation Failed");
        request.getRequestDispatcher("View").include(request, response);
//		doGet(request, response);
	}

}
