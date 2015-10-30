package com.aail.weatherapplicationv1.sync;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.aail.weatherapplicationv1.database.DBHandler;
import com.aail.weatherapplicationv1.database.dao.WeatherDAO;
import com.aail.weatherapplicationv1.database.dto.DTO;
import com.aail.weatherapplicationv1.database.dto.WeatherDTO;
import com.aail.weatherapplicationv1.utils.CommonMethods;

public class GetWeatherInfoCalling extends AsyncTask<Void, Void, Void> {

	private Context context;
	private String latitude, longitude;
	private String detailed_temperature_response;

	public GetWeatherInfoCalling(Context context, String latitude,
			String longitude) {
		this.context = context;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	protected void onPreExecute() {

	}

	@Override
	protected Void doInBackground(Void... params) {

		try {
			detailed_temperature_response = CommonMethods
					.sendGetRequest("http://api.openweathermap.org/data/2.5/forecast/daily?lat="
							+ latitude
							+ "&lon="
							+ longitude
							+ "&cnt=10&mode=json");
			parseJson();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);

	}

	public void parseJson() {
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(detailed_temperature_response);

			JSONArray jsonmainObject = jsonObject.getJSONArray("list");

			List<DTO> weatherList = new ArrayList<DTO>();

			for (int i = 0; i < jsonmainObject.length(); i++) {
				JSONObject jsonweatherObject = jsonmainObject.getJSONObject(i);
				String date = jsonweatherObject.getString("dt");
				String pressure = jsonweatherObject.getString("pressure");
				String humidity = jsonweatherObject.getString("humidity");
				String speed = jsonweatherObject.getString("speed");
				String degree = jsonweatherObject.getString("deg");

				JSONArray weatherObj = jsonweatherObject
						.getJSONArray("weather");
				JSONObject weatherObjJson = (JSONObject) weatherObj.get(0);
				String weather = weatherObjJson.getString("description");

				JSONObject tempObj = jsonweatherObject.getJSONObject("temp");
				// JSONObject temperatureJson = (JSONObject) tempObj.get(0);
				String temperature = tempObj.getString("min") + "-"
						+ tempObj.get("max");


				WeatherDTO dto = new WeatherDTO();
				dto.setHumidity(humidity);
				dto.setPressure(pressure);
				dto.setTemperature(temperature);
				dto.setWeather(weather);
				dto.setWindspeed(speed);
				dto.setDewpoint("");
				dto.setGroundlevel("");
				dto.setPrecipitation("");
				dto.setSealevel("");
				dto.setDate(CommonMethods.getDateFromTime(Long.parseLong(date)));

				weatherList.add(dto);

			}

			WeatherDAO.getInstance().insert(DBHandler.getDBObj(1, context),
					weatherList);
			
			Toast.makeText(context,WeatherDAO.getInstance().getRecords(DBHandler.getDBObj(0, context)).size() , 10000);
		//System.out.println("datacheck:::"+WeatherDAO.getInstance().getRecords(DBHandler.getDBObj(0, context)).size());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}