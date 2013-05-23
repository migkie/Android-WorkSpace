package com.utk.todolist;

public class ToDo {
	private String date;
	private String task;
	
	public ToDo(String date,String task){
		this.date = date;
		this.task = task;
	}
	public String getDate(){
		return this.date;
	}
	public void setDate(String date){
		this.date = date;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
}
