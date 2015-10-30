/*package com.aail.weatherapplicationv1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class MapTemperature extends AsyncTask<Void, Void, Void> {
	
			  String MapWeatherCallingTemperature;
			  ProgressDialog dialog;
			// Runs on UIThread
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				dialog = ProgressDialog.show(null, "Wait it is progress", "wait");
			}

			@Override
			protected Void doInBackground(Void... params) {
				
				try {
					MapWeatherCallingTemperature=sendGetRequest("http://api.openweathermap.org/data/2.5/forecast/daily?"
							+ "lat="+16.300
							+"&lon="+80.000
							+"&APPID=c92075702c83996f17a5b194a340ea19&cnt=10&mode=json");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return null;
			}

			// Runs on UI Thread
			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				dialog.dismiss();
				
			}

	
	public  String sendGetRequest(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		if (response != null)
			return response.toString();
		else
			return null;

	}

	public void execute(double latitude, double longitude) {
		// TODO Auto-generated method stub
		super.execute();
		Toast.makeText(null,"Execute Method"+MapWeatherCallingTemperature, Toast.LENGTH_LONG).show();
		
	}

}
*/