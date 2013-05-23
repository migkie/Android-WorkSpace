package com.utk.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class DisplayMessageActivity extends Activity {
	public static final String EXTRA_MESSAGE = "DisplayMessage.EXTRA_MESSAGE";  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_message);
		
		TextView textMessage = (TextView) findViewById(R.id.text_message);
		
		Intent intent = getIntent();
		
		String message = intent.getStringExtra(EXTRA_MESSAGE);
		textMessage.setText(message);
		textMessage.setTextSize(40);
//		textMessage.setTextColor(Color.RED);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
		return true;
	}

}
