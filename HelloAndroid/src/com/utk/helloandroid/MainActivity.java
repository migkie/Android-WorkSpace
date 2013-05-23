package com.utk.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void sendMessage(View view) {
    	EditText editMessage = (EditText) findViewById(R.id.edit_message);
    	TextView textMessage = (TextView) findViewById(R.id.text_message);
    	String editMsg = editMessage.getText().toString();
    	
    	textMessage.setText(editMsg);
    	Toast.makeText(this, editMsg, Toast.LENGTH_LONG).show();
    	
    	Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
    	intent.putExtra(DisplayMessageActivity.EXTRA_MESSAGE, editMsg);
    	startActivity(intent);
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
