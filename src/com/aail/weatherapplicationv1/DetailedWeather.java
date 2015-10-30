package com.aail.weatherapplicationv1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailedWeather extends MainActivity{
	
	public Cursor c;
	AutoCompleteTextView autoCompleteTextView1;
	String location=location_name;
	
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_screen);
        
        new DetailedWeatherCalling().execute();
        
        
	
        
        ImageButton mapView=(ImageButton)findViewById(R.id.mapview);
        mapView.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
            	
            	Intent intent = new Intent(DetailedWeather.this, Map.class);
            	startActivity(intent);
            }
         });
        
         
 	}   
 	

	public class DetailedWeatherCalling extends AsyncTask<Void, Void, Void> {

		
		ProgressDialog dialog;
		String detailed_temperature_response;
		

		// Runs on UIThread
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			dialog = ProgressDialog.show(DetailedWeather.this, "Please Wait...",
    				"Connecting to Server....");
		}

		@Override
		protected Void doInBackground(Void... params) {
			
			try {
				detailed_temperature_response=sendGetRequest("http://api.openweathermap.org/data/2.5/forecast/daily?q="+location+"&APPID=c92075702c83996f17a5b194a340ea19&mode=json&units=metric&cnt=7");
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
		
			try {
				ImageView dynamic_image=(ImageView) findViewById(R.id.imageView1);			
				TextView temperature=(TextView)findViewById(R.id.temperature);//temp
				TextView description=(TextView)findViewById(R.id.description);//Description
				TextView max_temp=(TextView)findViewById(R.id.max_temp);//Max_Temp
				TextView min_temp=(TextView)findViewById(R.id.min_temp);//Min_Temp
				TextView humidity=(TextView)findViewById(R.id.humidity);//Humidity
				TextView wind_speed=(TextView)findViewById(R.id.wind_speed);//Wind Speed
				TextView wind_deg=(TextView)findViewById(R.id.wind_deg);//Wind deg
				TextView pressure=(TextView)findViewById(R.id.pressure);//Pressure
				TextView locationName=(TextView) findViewById(R.id.location);
				
				

				JSONObject jsonObject=new JSONObject(detailed_temperature_response);
				JSONObject json_city_object= new JSONObject(jsonObject.getString("city"));
				JSONObject json_coord_object= new JSONObject(json_city_object.getString("coord"));

				
				JSONArray json_list_object= jsonObject.getJSONArray("list");
				JSONObject jsonobject1=json_list_object.getJSONObject(position);
				  
								
				JSONObject json_temp_object= jsonobject1.getJSONObject("temp");
				JSONArray json_weather_object= jsonobject1.getJSONArray("weather");
				JSONObject jsonweatherobject1=json_weather_object.getJSONObject(0);
				
				
				float day_temp1=Float.parseFloat(json_temp_object.getString("day"));
				float min_temp1=Float.parseFloat(json_temp_object.getString("min"));
				float max_temp1=Float.parseFloat(json_temp_object.getString("max"));
				
				NumberFormat df = NumberFormat.getNumberInstance();
	            df.setMaximumFractionDigits(0); 
	          
				              
	            temperature.setText(Html.fromHtml(String.valueOf(df.format(day_temp1))+"<sup>o</sup>"));
				min_temp.setText(Html.fromHtml(String.valueOf(df.format(min_temp1))+"<sup>o</sup>"));
				max_temp.setText(Html.fromHtml(String.valueOf(df.format(max_temp1))+"<sup>o</sup>"));
				humidity.setText(jsonobject1.getString("humidity")+"%");
				pressure.setText(jsonobject1.getString("pressure"));
				wind_speed.setText(jsonobject1.getString("speed"));
				wind_deg.setText(jsonobject1.getString("deg"));
				locationName.setText(location);
				description.setText(jsonweatherobject1.getString("description"));
				
				String data_des=jsonweatherobject1.getString("description");
				
				if("light rain".equals(data_des))
				{
					dynamic_image.setImageResource(R.drawable.light_rain);
				}
				if("sky is clear".equals(data_des))
				{
					dynamic_image.setImageResource(R.drawable.sky_clear);
				}
				if("few clouds".equals(data_des))
				{
					dynamic_image.setImageResource(R.drawable.few_clouds);
				}
				if("heavy intensity rain".equals(data_des))
				{
					dynamic_image.setImageResource(R.drawable.heavy_intensity_rain);
				}
				if("moderate rain".equals(data_des))
				{
					dynamic_image.setImageResource(R.drawable.moderate_rain);
				}
				if("scattered clouds".equals(data_des))
				{
					dynamic_image.setImageResource(R.drawable.scattered_clouds);
				}
				if("overcast clouds".equals(data_des))
				{
					dynamic_image.setImageResource(R.drawable.overcast_clouds);
				}
				if("broken clouds".equals(data_des))
				{
					dynamic_image.setImageResource(R.drawable.broken_cloud);
				}
			
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// HTTP GET request
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

public View getView(int position, View convertView, ViewGroup parent) {

	View v = convertView;
	if (v == null) {
		LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = vi.inflate(R.layout.detailed_screen, null);
	}
	TextView temp=(TextView)v.findViewById(R.id.temperature);
	EditText max_temp = (EditText) v.findViewById(R.id.max_temp);
	EditText min_temp  = (EditText) v.findViewById(R.id.min_temp);
	EditText humidity = (EditText) v.findViewById(R.id.humidity);
	EditText wind_speed = (EditText) v.findViewById(R.id.wind_speed);
	EditText wind_deg = (EditText) v.findViewById(R.id.wind_deg);
	EditText pressure = (EditText) v.findViewById(R.id.pressure);
	TextView textView8=(TextView)findViewById(R.id.description);


	c.moveToPosition(position);

	max_temp.setText(c.getString(0));
	min_temp.setText(c.getString(1));
	humidity.setText(c.getString(2));
	wind_speed.setText(c.getString(3));
	wind_deg.setText(c.getString(4));
	pressure.setText(c.getString(5));

	return v;

}
}