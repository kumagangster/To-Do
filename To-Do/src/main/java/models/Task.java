package models;

import java.sql.Date;

public class Task {
    private static int serial = 1; // This will keep track of the unique ID for each task
    private String tname;
    private int taskId; // Non-static to ensure unique task IDs
    private Date due;
    private boolean status=false;
    
    public Task() {
//    	this.taskId=serial++;
    }
    public Task(int taskId) {
			this.taskId = taskId;
    }
    
    public int getId() {
        return taskId; // Returns the unique task ID
    }
    public void set_id(int id) {
    	this.taskId=id;
    }
    // Setter methods
    public void setTask_name(String tname) {
        this.tname = tname;
    }

    public void set_Due(Date due) {
        this.due = due;
    }
    
    public void set_status(boolean status) {
    	this.status=status;
    }
    // Getter methods
    public String getTask_name() {
        return tname;
    }

    public java.sql.Date get_Due() {
        return due;
    }
    public boolean get_status() {
    	return status;
    }
}

