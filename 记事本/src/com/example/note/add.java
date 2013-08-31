package com.example.note;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add extends Activity implements OnClickListener{
	private EditText title,text;
	private Button bt1;
	sqlite sqlite1;
	int tag=0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		
		title=(EditText)this.findViewById(R.id.title);
		text=(EditText)this.findViewById(R.id.text);
		bt1=(Button)this.findViewById(R.id.bt1);
		bt1.setOnClickListener(this);
		
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String title_text=title.getText().toString();
		String text_text=text.getText().toString();
		sqlite1=new sqlite(add.this, "notepad.db", null, 1);
		SQLiteDatabase db=sqlite1.getReadableDatabase();
		ContentValues cv=new ContentValues();
		cv.put("title", title_text);
		cv.put("content", text_text);
		db.insert("notepad", null, cv);
		db.close();
		tag=1;
		Toast.makeText(add.this, "添加记录成功", 2).show();
		add.this.finish();
	}

}
