package com.koylubaevnt.praytimes.database;

import com.koylubaevnt.praytimes.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DatabaseActivity extends Activity {
		 TableLayout table_layout;
		 EditText firstname_et, lastname_et;
		 Button addmem_btn;

		 SQLController sqlcon;

		 ProgressDialog PD;

		 @Override
		 protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.database_activity);

		  sqlcon = new SQLController(this);

		  firstname_et = (EditText) findViewById(R.id.fistname_et_id);
		  lastname_et = (EditText) findViewById(R.id.lastname_et_id);
		  addmem_btn = (Button) findViewById(R.id.addmem_btn_id);
		  table_layout = (TableLayout) findViewById(R.id.tableLayout1);

		  BuildTable();

		  addmem_btn.setOnClickListener(new View.OnClickListener() {

		   @Override
		   public void onClick(View v) {

		    new MyAsync().execute();

		   }
		  });

		 }

		 private void BuildTable() {

		  sqlcon.open();
		  Cursor c = sqlcon.readEntry();

		  int rows = c.getCount();
		  int cols = c.getColumnCount();

		  c.moveToFirst();

		  // outer for loop
		  for (int i = 0; i < rows; i++) {

		   TableRow row = new TableRow(this);
		   row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
		     LayoutParams.WRAP_CONTENT));

		   // inner for loop
		   for (int j = 0; j < cols; j++) {

		    TextView tv = new TextView(this);
		    tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
		      LayoutParams.WRAP_CONTENT));
		    tv.setBackgroundResource(R.drawable.ic_launcher);
		    tv.setGravity(Gravity.CENTER);
		    tv.setTextSize(18);
		    tv.setPadding(0, 5, 0, 5);

		    tv.setText(c.getString(j));

		    row.addView(tv);

		   }

		   c.moveToNext();

		   table_layout.addView(row);

		  }
		  sqlcon.close();
		 }

		 private class MyAsync extends AsyncTask<Void, Void, Void> {

		  @Override
		  protected void onPreExecute() {
		   super.onPreExecute();

		   table_layout.removeAllViews();

		   PD = new ProgressDialog(DatabaseActivity.this);
		   PD.setTitle("Please Wait..");
		   PD.setMessage("Loading...");
		   PD.setCancelable(false);
		   PD.show();
		  }

		  @Override
		  protected Void doInBackground(Void... params) {

		   String firstname = firstname_et.getText().toString();
		   String lastname = lastname_et.getText().toString();

		   // inserting data
		   sqlcon.open();
		   sqlcon.insertData(firstname, lastname);
		   // BuildTable();
		   return null;
		  }

		  @Override
		  protected void onPostExecute(Void result) {
		   super.onPostExecute(result);
		   BuildTable();
		   PD.dismiss();
		  }
		 }
}
