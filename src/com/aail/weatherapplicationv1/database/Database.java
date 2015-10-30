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
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
	
	
	private final static String DATABASE_NAME	= "Weather.db";
	private final static int DATABASE_VERSION	= 1;
	
	private final String WEATHER		= "CREATE TABLE Weather (idWeather TEXT primary key,temperature TEXT,pressure TEXT, windspeed TEXT, precipitation TEXT, humidity TEXT, dewpoint TEXT, date TEXT, sealevel TEXT, groundlevel TEXT, weather TEXT)";
	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase dbObj) 
	{
		dbObj.execSQL(WEATHER);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}

