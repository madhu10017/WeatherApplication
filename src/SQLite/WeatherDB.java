package SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class WeatherDB {

	
	
	 public static final String KEY_LOCATION = "location";

	    public static final String KEY_TEMP = "temp";
	    public static final String KEY_HUMIDITY = "humidity";
	    public static final String KEY_WIND = "wind";
	    public static final String KEY_SEALEVEL = "sealevel";
	    public static final String KEY_GROUNDLEVEL="groundlevel";
	    public static final String KEY_DATE="date";
	    public static final String KEY_DAY="day";
	    
	    private static final String DATABASE_NAME = "Weathervone";
	    private static final String DATABASE_TABLE = "weatherdata";
	    private static final int DATABASE_VERSION = 1;

	    private static final String DATABASE_CREATE =
	        "create table weatherinfo(location text ,temp text primary key , "+ "humidity text not null, wind text not null, "+ "sealevel text not null," +"groundlevel text not null,"+"date text not null,"+"day text not null);";
	   
	    
	    
	    private final Context context; 
	    
	    private DatabaseHelper DBHelper;
	    private SQLiteDatabase db;

	    public WeatherDB(Context ctx) 
	    {
	        this.context = ctx;
	        DBHelper = new DatabaseHelper(context);
	    }
	        
	    private static class DatabaseHelper extends SQLiteOpenHelper 
	    {
	    	//used to create database.
	        DatabaseHelper(Context context) 
	        {
	            super(context, DATABASE_NAME, null, DATABASE_VERSION);
	        }

	        ///used to create only tables
	        @Override
	        public void onCreate(SQLiteDatabase db) 
	        {
	            db.execSQL(DATABASE_CREATE);
	            
	        }

	        @Override
	        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	        {
	            //Log.w(TAG, "Upgrading database from version " + oldVersion+ " to "+ newVersion + ", which will destroy all old data");
	          //  db.execSQL("DROP TABLE IF EXISTS weatherinfo");
	           // onCreate(db);
	        }
	    }    
	    
	   //---opens the database---
	    public WeatherDB open() throws SQLException 
	    {
	        db = DBHelper.getWritableDatabase();
	        return this;
	    }

	    //---closes the database---    
	    public void close() 
	    {
	        DBHelper.close();
	    }
	    
	   //---insert a title into the database---
	    public long insertTitle(String location,String temp,String humidity, String wind, String sealevel ,String groundlevel) 
	    {
	        ContentValues initialValues = new ContentValues();
	        
	       
	        initialValues.put(KEY_LOCATION, location);
	      
	        initialValues.put(KEY_TEMP, temp);
	        initialValues.put(KEY_HUMIDITY, humidity);
	        initialValues.put(KEY_WIND, wind);
	        initialValues.put(KEY_SEALEVEL, sealevel);
	        initialValues.put(KEY_GROUNDLEVEL, groundlevel);
	       /* initialValues.put(KEY_DATE, date);
	        initialValues.put(KEY_DAY, day);*/
	        
	        
	        return db.insert(DATABASE_TABLE, null, initialValues);
	    }
	    //---deletes a particular title---
	    public boolean deleteTitle(long rowId) 
	    {
	        return db.delete(DATABASE_TABLE, KEY_TEMP +"=" + rowId, null) > 0;
	    }

	    //---retrieves all the titles---
	    public Cursor getAllTitles() 
	    {
	        return db.query(DATABASE_TABLE, new String[] {
	        		KEY_LOCATION,KEY_TEMP,
	        		KEY_HUMIDITY,
	        		KEY_WIND,
	                KEY_SEALEVEL,KEY_GROUNDLEVEL,KEY_DATE,KEY_DAY}, 
	                null, 
	                null, 
	                null, 

	                null, 
	                null);
	    }

	    //---retrieves a particular title---
	    public Cursor getTitle(long rowId) throws SQLException 
	    {
	        Cursor mCursor =  db.query(true, DATABASE_TABLE, new String[] {
	                		KEY_LOCATION,
	                		KEY_HUMIDITY, 
	                		KEY_WIND,
	                		KEY_SEALEVEL,KEY_GROUNDLEVEL,KEY_DATE,KEY_DAY
	                		}, 
	                		KEY_LOCATION + "=" + rowId, 
	                		null,
	                		null, 
	                		null, 
	                		null, 
	                		null);
	        if (mCursor != null) {
	            mCursor.moveToFirst();
	        }
	        return mCursor;
	    }

	    //---updates a title---
	    public boolean updateTitle(String temp, String humidity,String wind, String sealevel,long groundlevel) 
	    {
	        ContentValues args = new ContentValues();
	        args.put(KEY_TEMP, temp);
	        args.put(KEY_HUMIDITY, humidity);
	        args.put(KEY_WIND, wind);
	        args.put(KEY_SEALEVEL, sealevel);
	        args.put(KEY_GROUNDLEVEL, groundlevel);
	        /*args.put(KEY_DATE, date)
	        args.put(KEY_DAY, day);*/
	        
	        return db.update(DATABASE_TABLE, args,KEY_LOCATION , null) > 0;
	    }
	}


