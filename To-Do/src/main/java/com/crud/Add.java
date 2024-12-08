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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import Controller.TodoDao;
@WebServlet("/Add")
public class Add extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

    public Add() {
        super();
        
    }
   protected void processRequest(HttpServletRequest request,HttpServletResponse response) 
            		throws ServletException, IOException { 

	   		response.setContentType("text/html;charset=UTF-8"); 
   	} 

    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		  //List<String> errors = new ArrayList<>();
//		  request.getRequestDispatcher("Update").forward(request, response);
		 
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	processRequest(request, response); 
    	String taskName=request.getParameter("tname");
//    	PrintWriter out=response.getWriter();
    	List<String> errors=new ArrayList<>();
//    	boolean status=request.getParameter("");
    	String due=request.getParameter("due");
    	Date dueDate = null;
        try {
            dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(due);
            if (dueDate.before(new Date())) {
                errors.add("Due date cannot be in the past");
            }
        } catch (Exception e) {
            errors.add("Invalid due date format");
        }
    	Task tsk=new Task();
    	tsk.setTask_name(taskName);
    	tsk.set_Due(new java.sql.Date(dueDate.getTime()));
        tsk.set_status(false);
        List<Task> list=null;
        
        int stat=0;
        try {
        	
        	stat=TodoDao.AddTask(tsk);
        	list=TodoDao.GetAllTask();
//        	out.println("Hi");
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
        
        if(stat>0) {
        	String message="Record Saved Successfully";
        	request.setAttribute("Errors",errors);
        	request.setAttribute("message", message);
        	request.setAttribute("tasks",list);
        }
        request.getRequestDispatcher("View").include(request, response);

//    	request.getRequestDispatcher("Res.jsp").include(request, response);
    }
    
    
}

