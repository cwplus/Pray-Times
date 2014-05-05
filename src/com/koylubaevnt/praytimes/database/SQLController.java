package com.koylubaevnt.praytimes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLController {

 private DBHelper dbhelper;
 private Context ourcontext;
 private SQLiteDatabase database;

 public SQLController(Context c) {
  ourcontext = c;
 }

 public SQLController open() throws SQLException {
  dbhelper = new DBHelper(ourcontext);
  database = dbhelper.getWritableDatabase();
  return this;

 }

 public void close() {
  dbhelper.close();
 }

 public void insertData(Long date, Double imsak, Double fajr,
		 Double sunrise, Double dhuhr, Double asr,
		 Double sunset, Double maghrib, Double isha,
		 Double midnight) {
	ContentValues cv = new ContentValues();
	cv.put(DBHelper.PRAYTIMES_DATE, date);
	cv.put(DBHelper.PRAYTIMES_IMSAK, imsak);
	cv.put(DBHelper.PRAYTIMES_FAJR, fajr);
	cv.put(DBHelper.PRAYTIMES_SUNRISE, sunrise);
	cv.put(DBHelper.PRAYTIMES_DHUHR, dhuhr);
	cv.put(DBHelper.PRAYTIMES_ASR, asr);
	cv.put(DBHelper.PRAYTIMES_SUNSET, sunset);
	cv.put(DBHelper.PRAYTIMES_MAGHRIB, maghrib);
	cv.put(DBHelper.PRAYTIMES_ISHA, isha);
	cv.put(DBHelper.PRAYTIMES_MIDNIGHT, midnight);
	database.insert(DBHelper.TABLE_PRAYTIMES, null, cv);
}

 public void deleteAllData() {
	database.delete(DBHelper.TABLE_PRAYTIMES, null, null);
}
 
 public Cursor readEntry() {
  String[] allColumns = new String[] { 
		  DBHelper.PRAYTIMES_DATE, DBHelper.PRAYTIMES_IMSAK,
		  DBHelper.PRAYTIMES_FAJR, DBHelper.PRAYTIMES_SUNRISE, DBHelper.PRAYTIMES_DHUHR,
		  DBHelper.PRAYTIMES_ASR, DBHelper.PRAYTIMES_SUNSET, DBHelper.PRAYTIMES_MAGHRIB,
		  DBHelper.PRAYTIMES_ISHA, DBHelper.PRAYTIMES_MIDNIGHT};

  Cursor c = database.query(DBHelper.TABLE_PRAYTIMES, allColumns, null, null, null,
    null, null);

  if (c != null) {
   c.moveToFirst();
  }
  return c;

 }

}