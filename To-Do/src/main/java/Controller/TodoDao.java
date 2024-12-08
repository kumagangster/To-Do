package Controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Task;

public class TodoDao {
		
		private static Connection conn;
	    private static final String url = "jdbc:mysql://localhost:3306/demo";
	    private static final String username = "root";
	    private static final String password = "";
		public static Connection connectDb() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection( url,username, password); 
		        } 
		        catch (Exception message) { 
		            System.out.println(message); 
		        } 
		        return conn; 
			
		}
		public static void closeConnection() {
	        try {
	            if (conn != null && !conn.isClosed()) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		public static int AddTask(Task tsk) throws SQLException {
				int res=0;
				Connection conn=TodoDao.connectDb();
				
				PreparedStatement preparedStatement 
	            = conn.prepareStatement( 
	                "insert into task(taskId,tname,due,status) values (?,?,?,?)"); 
	        
	        // set the parameter to the given Java String value 
				preparedStatement.setInt(1, tsk.getId()); 
				preparedStatement.setString(2, tsk.getTask_name());
				preparedStatement.setDate(3, tsk.get_Due());	       
				preparedStatement.setBoolean(4, false);
	        // execute SQl statement insert values to the 
	        // database 
	         res = preparedStatement.executeUpdate(); 
	      
	        closeConnection(); 
				return res;
			}
		public static int UpdateTask(Task tsk) throws SQLException{
			int res=0;
			Connection conn=TodoDao.connectDb();
			PreparedStatement preparedStatement = conn.prepareStatement("update task set tname=?,due=?,status=? where taskId=?");
			preparedStatement.setString(1,tsk.getTask_name());
			preparedStatement.setDate(2,tsk.get_Due());
			preparedStatement.setBoolean(3,tsk.get_status());
			preparedStatement.setInt(4, tsk.getId());
			res=preparedStatement.executeUpdate();
			closeConnection();
			return res;
		}
		public static int UpdateStatus(int taskId,boolean status) throws SQLException{
			int res=0;
			Connection conn=TodoDao.connectDb();
			PreparedStatement preparedStatement=conn.prepareStatement("update task set status=? where taskId=?");
			preparedStatement.setBoolean(1, status);
			preparedStatement.setInt(2,taskId);
			res=preparedStatement.executeUpdate();
			closeConnection();
			return res;
		}
		public static int DeleteTask(int taskId) throws SQLException{
			int res=0;
			Connection conn=TodoDao.connectDb();
			PreparedStatement preparedStatement=conn.prepareStatement("delete From task where taskId=?");
			preparedStatement.setInt(1,taskId);
			res=preparedStatement.executeUpdate();
			closeConnection();
			return res;
		}
		public static Task GetTask(int taskId) throws SQLException{
			Task tsk=new Task();
			Connection conn=TodoDao.connectDb();
			PreparedStatement preparedStatement =conn.prepareStatement("select * from task where taskId=?");
			preparedStatement.setInt(1,taskId);
			ResultSet reset=preparedStatement.executeQuery();
			while(reset.next()) {
				tsk.set_id(reset.getInt("taskId"));
				tsk.setTask_name(reset.getString("task_name"));
				tsk.set_status(reset.getBoolean("status"));
				tsk.set_Due(reset.getDate("due"));
			}
			closeConnection();
			return tsk;
		}
		public static List<Task> GetAllTask()throws SQLException{
			List<Task> tasks=new ArrayList<>();
			Connection conn=TodoDao.connectDb();
			PreparedStatement preparedStatement =conn.prepareStatement("select * from task");
			ResultSet reset=preparedStatement.executeQuery();
			while(reset.next()) {
				Task tsk=new Task(reset.getInt("taskId"));
				tsk.setTask_name(reset.getString("tname"));
				tsk.set_status(reset.getBoolean("status"));
				tsk.set_Due(reset.getDate("due"));
				tasks.add(tsk);
			}
			closeConnection();
			return tasks;
		}
}
