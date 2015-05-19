package com.example.colcviu;

import com.example.colcviu.threaduri.ClientThread;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public Button addB = null;
	public Button mulB = null;
	public EditText op1 = null;
	public EditText op2 = null;
	public TextView port = null;
	public EditText rez1 = null;
	public EditText rez2 = null;

	public ButtonAddClick bclick = new ButtonAddClick();
	public class ButtonAddClick implements Button.OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String operand1 = op1.getText().toString();
			String operand2 = op2.getText().toString();
			ClientThread cl = new ClientThread(2020, "add", operand1, operand2, rez1, rez2);
		}
		
	}
	
	public ButtonMulClick mclick = new ButtonMulClick();
	public class ButtonMulClick implements Button.OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String operand1 = op1.getText().toString();
			String operand2 = op2.getText().toString();
			ClientThread cl = new ClientThread(2020, "mul", operand1, operand2, rez1, rez2);
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
	
		op1 = (EditText)findViewById(R.id.op1);
		op2 = (EditText)findViewById(R.id.op2);
		rez1 = (EditText)findViewById(R.id.rez1);
		rez2 = (EditText)findViewById(R.id.rez2);
		port = (TextView)findViewById(R.id.port);
		
		addB = (Button)findViewById(R.id.add);
		addB.setOnClickListener(bclick);
		
		mulB = (Button)findViewById(R.id.mul);
		mulB.setOnClickListener(mclick);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
