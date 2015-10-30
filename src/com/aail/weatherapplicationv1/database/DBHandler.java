/**
 * 
 * @author 
 * Ybrant Digital
 * Copyright (C) Ybrant Digital
 * http://www.ybrantdigital.com
 *
 */
package com.aail.weatherapplicationv1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBHandler {
	
	private static Database db;
	
	
	public static SQLiteDatabase getDBObj(int value, Context context)
	{
		db	= new Database(context);
		
		if(value == 0)
		{
			return db.getReadableDatabase();
		}
		else
		{
			return db.getWritableDatabase();
		}
	}
}
