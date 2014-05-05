package com.koylubaevnt.praytimes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLController {
	private DBHelper dbhelper;
	private Context context;
	private SQLiteDatabase database;
	
	public SQLController(Context c) {
		context = c;
	}
	
	public SQLController open() throws SQLException {
		dbhelper = new DBHelper(context);
		database = dbhelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		dbhelper.close();
	}
	
	public void insertData(String name, String lname) {
		ContentValues cv = new ContentValues();
		cv.put(DBHelper.MEMBER_FIRSTNAME, name);
		cv.put(DBHelper.MEMBER_LASTNAME, lname);
		database.insert(DBHelper.TABLE_MEMBER, null, cv);
	}
	
	public Cursor readEntry() {
		String[] allColumns = new String[] { DBHelper.MEMBER_ID, DBHelper.MEMBER_FIRSTNAME, DBHelper.MEMBER_LASTNAME };
		Cursor c = database.query(DBHelper.TABLE_MEMBER, allColumns, null, null, null, null, null);
		
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
}
