package com.utk.todolist;

import android.R.bool;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ToDoDBAdapter {
	private static final String DATABASE_NAME="ToDoList.db";
	public static final String TABLE_NAME="ToDoItems";
	// ไว้เช็คตอน อัพเดท หากมีการเปลี่ยนแปลงจะเรียกใช้การอัพเกรด
	private static final int DATABASE_VERSION = 1;
	
	// DB Field
	public static final String KEY_ID = "id";
	public static final String KEY_TASK = "task";
	
	// DB Adapter Variable
	private SQLiteDatabase db;
	private final Context context;
	private ToDoDBOpenHelper dbHelper;
	
	// Method For DB Adapter
	public ToDoDBAdapter(Context context){
		this.context = context;
		dbHelper = new ToDoDBOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION);
		
	}
	
	public void open(){
		// If not try catch ,program will be force close on fatal error
		try{
			db = dbHelper.getWritableDatabase();
					
		} catch(SQLException e){
			e.printStackTrace();
			db = dbHelper.getReadableDatabase();
		}
	}
	
	public void close(){
		// Close DB Object for save memory
		db.close();
	}
	
	public long insertTask(String task){
		// Create a new row to insert
		ContentValues newTaskValues = new ContentValues();
		// Add value for each row
		newTaskValues.put(KEY_TASK,task);
		// insert into database
		return db.insert(TABLE_NAME,null,newTaskValues);
	}
	
	public boolean deleteByID(int id){
		// db.delete method always return number of deleted record.
		return db.delete(this.TABLE_NAME, KEY_ID + "=" + String.valueOf(id), null) > 0;
	}
	
	// Get DB cursor ** retrive data 
	public Cursor getAllToDoItems(){
		// Select all todo items from the database
		return db.query(TABLE_NAME, new String[] {KEY_ID ,KEY_TASK}, null,
				null, null, null,null);
	}
	
			
	// Define Class
	// Right Click on class name and choose "Add Constructor"
	// Overlay mouse on class name and choose "Unimplement **"
	private static class ToDoDBOpenHelper extends SQLiteOpenHelper{

		public ToDoDBOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
			// Generate Create Table Script
			String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + " ( " +
					KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					KEY_TASK + " TEXT " +
					" ); ";
			
			// Execute query string
			db.execSQL(CREATE_TABLE_QUERY);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}
}

