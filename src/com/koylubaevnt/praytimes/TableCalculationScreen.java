package com.koylubaevnt.praytimes;

import java.sql.Date;

import com.koylubaevnt.praytimes.core.Util;
import com.koylubaevnt.praytimes.core.PrayTimes.Time;
import com.koylubaevnt.praytimes.database.SQLController;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class TableCalculationScreen extends Activity {
	TableLayout table_layout;
	SQLController sqlcon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_calculation_activity);
		sqlcon = new SQLController(this);
		table_layout = (TableLayout) findViewById(R.id.tablePrayTimes);
		BuildTable();
	}
	
	private void BuildTable() {
		sqlcon.open();
		Cursor c = sqlcon.readEntry();

		int rows = c.getCount();
		int cols = c.getColumnCount();

		TableRow row = new TableRow(this);
		row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		for (int j = 0; j < cols; j++) {
			TextView tv = new TextView(this);
			tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			//tv.setBackgroundResource(R.drawable.ic_launcher);
			tv.setGravity(Gravity.CENTER);
			//tv.setTextSize(18);
			tv.setPadding(0, 5, 0, 5);
			
			tv.setText(c.getColumnName(j));
			row.addView(tv);
			
		}
		table_layout.addView(row);
		
		c.moveToFirst();

		for (int i = 0; i < rows; i++) {

			row = new TableRow(this);
			row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

			for (int j = 0; j < cols; j++) {

				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				//tv.setBackgroundResource(R.drawable.ic_launcher);
				tv.setGravity(Gravity.CENTER);
				//tv.setTextSize(18);
				tv.setPadding(0, 5, 0, 5);
				
				if (j == 0){ 
					tv.setText(new Date(Long.parseLong(c.getString(j))).toString());
				}else{
					tv.setText(Util.toTime24(Double.parseDouble(c.getString(j))));
				}
				

				row.addView(tv);

			}

			c.moveToNext();

			table_layout.addView(row);

		}
		sqlcon.close();
	}
}
