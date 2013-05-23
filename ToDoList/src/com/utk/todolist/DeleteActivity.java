package com.utk.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DeleteActivity extends Activity {
	
	public static final String EXTRA_ID = "DeleteActivity.EXTRA_ID";
	public static final String EXTRA_TASK = "DeleteActivity.EXTRA_TASK";
	
	private int id;
	private String task;
    private	Intent intentObj;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);
		
		TextView textViewTask = (TextView)findViewById(R.id.textViewTask);
		Button btnDel = (Button)findViewById(R.id.buttonDelete);
		Button btnCan = (Button)findViewById(R.id.buttonCancel);
		
		intentObj = getIntent();
		id = intentObj.getIntExtra(EXTRA_ID, 0);
		task = intentObj.getStringExtra(EXTRA_TASK);
		
		textViewTask.setText(task);
		
		btnDel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ToDoDBAdapter toDoDBAdapterObj = new ToDoDBAdapter(DeleteActivity.this);
				toDoDBAdapterObj.open();
				toDoDBAdapterObj.deleteByID(id);
				
				// Set Return Status
				intentObj.putExtra("status", task + "delete");
				
				// Set Status 
				setResult(RESULT_OK,intentObj);
				// Close after doing upper line
				finish();
				
			}
		});
		
		btnCan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// Set Return Status
				intentObj.putExtra("status", task + "Cancel");
				
				
				// Set Status
				setResult(RESULT_CANCELED,intentObj);
			}
		});

	}
	
}
