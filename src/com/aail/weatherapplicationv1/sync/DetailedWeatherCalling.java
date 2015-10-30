package com.aail.weatherapplicationv1.sync;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.aail.weatherapplicationv1.utils.CommonMethods;

public class DetailedWeatherCalling extends AsyncTask<Void, Void, Void> {

	
	
	private String detailed_temperature_response;
	private Context context;
	private String location;
	private int method;
	
	
	public DetailedWeatherCalling(Context context,String location)
	{
		this.context = context;
		this.location = location;
		this.method = method;
	}
	

	// Runs on UIThread
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Void doInBackground(Void... params) {
		
		try {
			detailed_temperature_response=CommonMethods.sendGetRequest("http://api.openweathermap.org/data/2.5/weather?q="+location);
			Toast.makeText(context, "Toast:"+detailed_temperature_response, Toast.LENGTH_SHORT).show();
			parseJson();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

	}
	
	public void parseJson()
	{
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(detailed_temperature_response);
			System.out.println("responsedata:::"+detailed_temperature_response);
			
			Toast.makeText(context, "Running"+detailed_temperature_response, Toast.LENGTH_SHORT).show();

			JSONObject jsoncoordObject = new JSONObject(jsonObject.getString("coord"));
			String longitude = jsoncoordObject.getString("lon");
			String latitude = jsoncoordObject.getString("lat");
			
			if(latitude != null && longitude != null)
				new GetWeatherInfoCalling(context,latitude,longitude).execute();
			
					
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}

}