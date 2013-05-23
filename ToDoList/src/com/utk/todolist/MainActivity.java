package com.utk.todolist;

import java.util.ArrayList;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText editTodo;
	private ListView listView;
	
	private ArrayList<String> todoItems = new ArrayList<String>();
	private ArrayList<Integer> todoIds = new ArrayList<Integer>();
	
	private ArrayAdapter<String> aa;
	
	private ToDoDBAdapter toDoDBAdapter;
	private Cursor toDoListCursor;
	protected ArrayList<String> asas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Define Variable for Element
		editTodo = (EditText) findViewById(R.id.editTodo);
		listView = (ListView) findViewById(R.id.listView);
		
		aa = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, todoItems);
		toDoDBAdapter = new ToDoDBAdapter(this);
		toDoDBAdapter.open();
		
		populateToDoList();
		
		// Test add to ArrayList
//		todoItems.add("Test 1");
		todoItems.add("Test 2");
		
		// Test Class with Constructor
		ToDo item = new ToDo("23/05/2013","Task");
		
		// Set Adapter to listView control in layout
		listView.setAdapter(aa);
		
		// Handle Event on enter on keyboard
		editTodo.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// Check Event
				if ((keyCode == KeyEvent.KEYCODE_ENTER)
						&& (event.getAction() == KeyEvent.ACTION_DOWN)
						&& !editTodo.getText().toString().equals("")) {
					// Add item to todo list
					String toDo = editTodo.getText().toString();
				//	todoItems.add(toDo);
				//	todoItems.add(1, toDo);
				
					toDoDBAdapter.insertTask(toDo);
					updateArray();
					
					editTodo.setText("");
					aa.notifyDataSetChanged();
					return true;
				
				}
				return false;
			}
			
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				int task_id = todoIds.get(position);
				String task = todoItems.get(position);
			
				Intent intObj = new Intent(MainActivity.this,DeleteActivity.class);
				intObj.putExtra(DeleteActivity.EXTRA_ID, task_id);
				
				intObj.putExtra(DeleteActivity.EXTRA_TASK, task);
				startActivityForResult(intObj, 0);
							
			}
		});
		
	}

	private void populateToDoList() {
		// TODO Auto-generated method stub
		// Get All data from the database
		this.toDoListCursor = toDoDBAdapter.getAllToDoItems();
	
		startManagingCursor(toDoListCursor);
		// update the array list
		updateArray();
		
	}
	 @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		String status;
		if(requestCode == 0){
			switch (resultCode) {
			case RESULT_OK:
				status = data.getStringExtra("status");
				Toast.makeText(this, "OK, " + status,Toast.LENGTH_LONG);
				updateArray();
				break;

			case RESULT_CANCELED:
				status = data.getStringExtra("status");
				Toast.makeText(this, "OK, " + status,Toast.LENGTH_LONG);
				break;
			default:
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	private void updateArray() {
		// TODO Auto-generated method stub
		toDoListCursor.requery();
		// Clear item in array list
		this.todoItems.clear();
		this.todoIds.clear();
		
		// Check is cursor having value by move to first
		if(toDoListCursor.moveToFirst()){
				do{
					int task_id = toDoListCursor.getInt(
							toDoListCursor.getColumnIndex(ToDoDBAdapter.KEY_ID)
							);
					String task = toDoListCursor.getString(
							toDoListCursor.getColumnIndex(ToDoDBAdapter.KEY_TASK)
							);
					todoIds.add(0,task_id);
					todoItems.add(0,task);
					
				}while(toDoListCursor.moveToNext());
				aa.notifyDataSetChanged();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		toDoDBAdapter.close();
	}
	
}
