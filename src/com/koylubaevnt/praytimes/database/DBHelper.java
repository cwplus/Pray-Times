package com.koylubaevnt.praytimes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	// TABLE INFORMATTION
	 public static final String TABLE_MEMBER = "member";
	 public static final String MEMBER_ID = "_id";
	 public static final String MEMBER_FIRSTNAME = "firstname";
	 public static final String MEMBER_LASTNAME = "lastname";

	 // DATABASE INFORMATION
	 static final String DB_NAME = "MEMBER.DB";
	 static final int DB_VERSION = 1;

	 // TABLE CREATION STATEMENT
	 private static final String CREATE_TABLE = "create table " + TABLE_MEMBER
	   + "(" + MEMBER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	   + MEMBER_FIRSTNAME + " TEXT NOT NULL ," + MEMBER_LASTNAME
	   + " TEXT NOT NULL);";

	 public DBHelper(Context context) {
	  super(context, DB_NAME, null, DB_VERSION);

	 }

	 @Override
	 public void onCreate(SQLiteDatabase db) {

	  db.execSQL(CREATE_TABLE);
	 }

	 @Override
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	  db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
	  onCreate(db);

	 }
}
/*
	private static final String DB_NAME = "my_db";
	private static final int DB_VERSION = 1;	
	public static final String MYTABLE = "mytable"; 
    public static final String KEY_ID = "id"; 
    public static final String TAX = "tax"; 
    public static final String PREV = "prev"; 
    public static final String CURR = "curr"; 
 
    public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Здесь важно чтобы строка была форматированная; 
        String query = String.format ( "CREATE TABLE %s ( %s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT);", MYTABLE, KEY_ID, TAX, PREV, CURR);
  
        db.execSQL(query);//Создание заданной таблицы в базе;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MYTABLE); 
        onCreate(db);
    }
	
    // Your local name for the schema
	private static final String DB_NAME = "my_db";
	// Current version (you need to handle upgrades to this version in onUpgrade)
	private static final int DB_VERSION = 1;	

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	**
	 * Creates any of our tables.
	 * This function is only run once or after every Clear Data
	 *
	@Override
	public void onCreate(SQLiteDatabase database) {
		try {			
			database.execSQL("" +
				"create table auth (id integer primary key autoincrement,"+
				"username text," +
				"code text"+
			");");	

		} catch(Exception e){}
	}

	**
	 * On open we want to make sure we get rid of the stupid setLocale error
	 *
	@Override
	public void onOpen(SQLiteDatabase database) {
		if(!database.isOpen()) {
			SQLiteDatabase.openDatabase(database.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS |
				 SQLiteDatabase.CREATE_IF_NECESSARY);
		}
	}

	**
	 * Handle all database version upgrades
	 *	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		*
		database.beginTransaction();
		boolean okay = true;

		// Add in date table for queue
		if(oldVersion < 2 || newVersion == 2) {
			boolean exists = checkTable(database, QueueDB.DB_TABLE);

			// Add the column
			if(exists) {
				database.execSQL("ALTER TABLE users ADD COLUMN used integer");
			} else {
				okay = false;
			}
		} 

		if(okay) { 
			database.setTransactionSuccessful();
		}

		database.endTransaction();
		*
	}

	**
	 * Check if a table exists. Useful for new table creation in onUpgrade
	 *	
	private boolean checkTable(SQLiteDatabase pDatabase, String pTable) {
		try {
			Cursor c = pDatabase.query(pTable, null, null, null, null, null, null);
			if(c == null) {
				return false;
			}
			c.close();
		} catch(Exception e) {
			return false;
		}
		return true;
	}

	**
	 * Return current date as a string
	 *	
	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		Date date = new Date();
		return sdf.format(date);
	}
*/

