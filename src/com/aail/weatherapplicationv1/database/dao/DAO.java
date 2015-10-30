/**
 * 
 * @author 
 * Ybrant Digital
 * Copyright (C) Ybrant Digital
 * http://www.ybrantdigital.com
 *
 */

package com.aail.weatherapplicationv1.database.dao;


import java.util.List;

import android.database.sqlite.SQLiteDatabase;

import com.aail.weatherapplicationv1.database.dto.DTO;

public interface DAO {
	
	public boolean insert(SQLiteDatabase dbObj, List<DTO> list);
	public boolean update(SQLiteDatabase dbObj, DTO dtoObj);
	public boolean delete(SQLiteDatabase dbObj, DTO dtoObj);
	
	public List<DTO> getRecords(SQLiteDatabase dbObj);
	public List<DTO> getRecordsWithValues(SQLiteDatabase dbObj, String columnName, String columnValue);
}
