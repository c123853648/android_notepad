package com.example.note;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class find extends Activity {
	private sqlite mySQLiteHelper;
	private SQLiteDatabase db;
	private ListView lv;
	private TextView tv;
	private List<Map<String, Object>> list;
	private Cursor cursor;
	private int mywhich;
	private int myid;
	private String title;
	private String content;
	private EditText et01;
	private EditText et02;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find);

		mySQLiteHelper = new sqlite(find.this, "notepad.db", null, 1);
		tv = (TextView) findViewById(R.id.TextView01);
		lv = (ListView) findViewById(R.id.ListView01);
		// 数据库
		db = mySQLiteHelper.getReadableDatabase();
		cursor = db.query("notepad",
				new String[] { "_id", "title", "content" }, null, null, null,
				null, null);
		if (cursor.getCount() > 0) {
			tv.setVisibility(View.GONE);
		}
		SimpleCursorAdapter sca = new SimpleCursorAdapter(find.this,R.layout.item, cursor,new String[] { "_id", "title", "content" }, new int[] {R.id.TextView01, R.id.TextView02, R.id.TextView03 });
		lv.setAdapter(sca);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Builder builder=new Builder(find.this);
				builder.setSingleChoiceItems(new String[]{"查看","修改","删除"}, 0, new OnClickListener(){

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						switch (arg1) {
						case 0:
							int myIndex=cursor.getColumnIndex("_id");
							myid=cursor.getInt(myIndex);
							int titleIndex=cursor.getColumnIndex("title");
							title=cursor.getString(titleIndex);
							content=cursor.getString(2);
							Toast.makeText(find.this, title+""+content, 2).show();
							break;
						case 1:
							Builder builder1=new Builder(find.this);
							builder1.setTitle("编辑");
							
							LayoutInflater inflater=LayoutInflater.from(find.this);
							View view=inflater.inflate(R.layout.updatedialogeview, null);
							et01=(EditText)view.findViewById(R.id.EditText01);
							et02=(EditText)view.findViewById(R.id.EditText02);
							builder1.setView(view);
							
							builder1.setPositiveButton("确定", new OnClickListener() {
								
								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									// TODO Auto-generated method stub
									String title_edit=et01.getText().toString();
									String content_edit=et02.getText().toString();
									int myid=cursor.getInt(0);
									ContentValues contentValues=new ContentValues();
									contentValues.put("title", title_edit);
									contentValues.put("content", content_edit);
									db.update("notepad", contentValues, "_id="+myid, null);
									Cursor cursor=db.query("notepad", new String[]{"_id","title","content"}, null, null, null, null, null);
									SimpleCursorAdapter sca=new SimpleCursorAdapter(find.this, R.layout.item, cursor, new String[]{"_id","title","content"}, new int[]{R.id.TextView01,R.id.TextView02,R.id.TextView03});
									lv.setAdapter(sca);
								}
							});
							builder1.setNegativeButton("取消", new DialogInterface.OnClickListener(){

								public void onClick(DialogInterface dialog, int which) {

									
								}
								
							});
							builder1.show();
							break;
						case 2:
							int myid=cursor.getInt(0);
							db.delete("notepad", "_id="+myid, null);
							Cursor cursor01=db.query("notepad", new String[]{"_id","title","content"}, null, null, null, null, null);
							SimpleCursorAdapter adapter01=new SimpleCursorAdapter(find.this, R.layout.item, cursor01, new String[]{"_id","title","content"}, new int[]{R.id.TextView01,R.id.TextView02,R.id.TextView03});
							lv.setAdapter(adapter01);
							break;

						
						}
						
					}});
				builder.show();
			}
		});

	}
}
