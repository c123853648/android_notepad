package com.example.note;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private Button bt1,bt2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt1=(Button)this.findViewById(R.id.add);
		bt2=(Button)this.findViewById(R.id.find);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.add:
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, add.class);
			startActivity(intent);
			
			break;

		case R.id.find:
			Intent intent2=new Intent();
			intent2.setClass(MainActivity.this, find.class);
			startActivity(intent2);
			break;
		}
		
	}

}
