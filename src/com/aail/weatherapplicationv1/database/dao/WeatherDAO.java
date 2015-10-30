/**
 * 
 */
package com.aail.weatherapplicationv1.database.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.aail.weatherapplicationv1.database.dto.DTO;
import com.aail.weatherapplicationv1.database.dto.WeatherDTO;

public class WeatherDAO implements DAO {

	private static WeatherDAO weedicideDAO;

	private WeatherDAO() {

	}

	public static WeatherDAO getInstance() {
		if (weedicideDAO == null)
			weedicideDAO = new WeatherDAO();

		return weedicideDAO;
	}

	@Override
	public boolean insert(SQLiteDatabase dbObject, List<DTO> list) {
		try {
			dbObject.beginTransaction();
			SQLiteStatement stmt = dbObject
					.compileStatement("INSERT INTO Weather(temperature,pressure,windspeed,precipitation,humidity,dewpoint,date,sealevel,groundlevel,weather)VALUES (?,?,?,?,?,?,?,?,?,?)");

			int count = 1;

			for (DTO items : list) {

				WeatherDTO dto = (WeatherDTO) items;
				stmt.bindString(count++, dto.getTemperature());
				stmt.bindString(count++, dto.getPressure());
				stmt.bindString(count++, dto.getWindspeed());
				stmt.bindString(count++, dto.getPrecipitation());
				stmt.bindString(count++, dto.getHumidity());
				stmt.bindString(count++, dto.getDewpoint());
				stmt.bindString(count++, dto.getDate());
				stmt.bindString(count++, dto.getSealevel());
				stmt.bindString(count++, dto.getGroundlevel());
				stmt.bindString(count++, dto.getWeather());

				count = 1;

				stmt.executeInsert();
			}

			dbObject.setTransactionSuccessful();
			return true;
		} catch (Exception e) {
			Log.e("WeatherDAO  -- insert", e.getMessage());
		} finally {
			dbObject.endTransaction();
			dbObject.close();
		}
		return false;
	}

	@Override
	public boolean delete(SQLiteDatabase dbObject, DTO dto) {
		WeatherDTO dtoObj = (WeatherDTO) dto;
		try {
			dbObject.compileStatement(
					"DELETE FROM Weather WHERE idWeather = '"
							+ dtoObj.getIdWeather() + "'").execute();

			return true;
		} catch (Exception e) {
			Log.e("WeatherDAO  -- delete", e.getMessage());
		}

		finally {
			dbObject.close();
		}
		return false;
	}

	@Override
	public boolean update(SQLiteDatabase dbObject, DTO dto) {
		try {
			WeatherDTO dtoObj = (WeatherDTO) dto;
			String whereCls = "idWeather = '" + dtoObj.getIdWeather() + "'";

			ContentValues cValues = new ContentValues();
			dbObject.update("Weather", cValues, whereCls, null);

			return true;
		} catch (SQLException e) {
			Log.e("WeatherDAO  -- update", e.getMessage());
		} finally {
			dbObject.close();
		}
		return false;
	}

	@Override
	public List<DTO> getRecords(SQLiteDatabase dbObject) {
		List<DTO> instList = new ArrayList<DTO>();
		Cursor cursor = null;
		
		int count	= 0;
		
		try {
			cursor = dbObject.rawQuery("SELECT * FROM Weather", null);
			if (cursor.moveToFirst()) {
				do {
					WeatherDTO dto = new WeatherDTO();
					dto.setIdWeather(cursor.getString(count++));
					dto.setTemperature(cursor.getString(count++));
					dto.setPressure(cursor.getString(count++));
					dto.setWindspeed(cursor.getString(count++));
					dto.setPrecipitation(cursor.getString(count++));
					dto.setHumidity(cursor.getString(count++));
					dto.setDewpoint(cursor.getString(count++));
					dto.setDate(cursor.getString(count++));
					dto.setSealevel(cursor.getString(count++));
					dto.setGroundlevel(cursor.getString(count++));
					dto.setWeather(cursor.getString(count++));
					
					instList.add(dto);
					
					count	= 0;

				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.e("WeatherDAO  -- getRecords", e.getMessage());
		} finally {
			if (cursor != null && !cursor.isClosed())
				cursor.close();

			dbObject.close();
		}

		return instList;
	}

	@Override
	public List<DTO> getRecordsWithValues(SQLiteDatabase dbObject,
			String columnName, String columnValue) {
		List<DTO> instList = new ArrayList<DTO>();
		Cursor cursor = null;
		
		int count	= 0;
		
		try {
			cursor = dbObject.rawQuery("SELECT * FROM Weather WHERE "
					+ columnName + " = '" + columnValue + "'", null);
			if (cursor.moveToFirst()) {
				do {
					WeatherDTO dto = new WeatherDTO();
					dto.setIdWeather(cursor.getString(count++));
					dto.setTemperature(cursor.getString(count++));
					dto.setPressure(cursor.getString(count++));
					dto.setWindspeed(cursor.getString(count++));
					dto.setPrecipitation(cursor.getString(count++));
					dto.setHumidity(cursor.getString(count++));
					dto.setDewpoint(cursor.getString(count++));
					dto.setDate(cursor.getString(count++));
					dto.setSealevel(cursor.getString(count++));
					dto.setGroundlevel(cursor.getString(count++));
					dto.setWeather(cursor.getString(count++));

					instList.add(dto);
					
					count	= 0;
					
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.e("WeatherDAO  -- getRecordsWithValues", e.getMessage());
		} finally {
			if (cursor != null && !cursor.isClosed())
				cursor.close();
			dbObject.close();
		}

		return instList;
	}
	
	public boolean insertAll(SQLiteDatabase dbObject, List<WeatherDTO> list,String tablename) {
		try {
			dbObject.beginTransaction();
			SQLiteStatement stmt = dbObject
					.compileStatement("INSERT INTO "+tablename+"(temperature,pressure,windspeed,precipitation,humidity,dewpoint,date,sealevel,groundlevel,weather)VALUES (?,?,?,?,?,?,?,?,?,?)");
			int count = 1;

			for (WeatherDTO dto : list) {
				stmt.bindString(count++, dto.getTemperature());
				stmt.bindString(count++, dto.getPressure());
				stmt.bindString(count++, dto.getWindspeed());
				stmt.bindString(count++, dto.getPrecipitation());
				stmt.bindString(count++, dto.getHumidity());
				stmt.bindString(count++, dto.getDewpoint());
				stmt.bindString(count++, dto.getDate());
				stmt.bindString(count++, dto.getSealevel());
				stmt.bindString(count++, dto.getSealevel());
				stmt.bindString(count++, dto.getGroundlevel());
				stmt.bindString(count++, dto.getWeather());

				count = 1;

				stmt.executeInsert();
			}

			dbObject.setTransactionSuccessful();
			return true;
		} catch (Exception e) {
			Log.e("WeatherDAO  -- insert", e.getMessage());
		} finally {
			dbObject.endTransaction();
			dbObject.close();
		}
		return false;
	}
	
	
	public boolean deleteAll(SQLiteDatabase dbObject) {
		try {
			String query ="DELETE FROM Weather";
			dbObject.execSQL(query);
			return true;
		} catch (Exception e) {
			Log.e("WeatherDAO  -- deleteall", e.getMessage());
		} finally {
			
			dbObject.close();
		}
		return false;
		
	}
	

}
