package com.aail.weatherapplicationv1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class RecyclermainActivity extends MainActivity {

	List<Data> lists = new ArrayList<Data>();
	 RecyclerView recyclerview;
	 GridLayoutManager layout;
	AutoCompleteTextView ac;
	Button add;
	MyRecyclerAdapter adapter;
	LinearLayoutManager linear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.recycler_activity_main);

		// Initialize Recycler view
		recyclerview = (RecyclerView) findViewById(R.id.recycler);
		recyclerview.setHasFixedSize(true);

		// recyclerview.setLayoutManager(new LinearLayoutManager(this));
		//Initialize Button and AutoCompleteTextView
		add = (Button) findViewById(R.id.button);
		ac = (AutoCompleteTextView) findViewById(R.id.autoComplete);

		// Setting Layout and Number of Columns
		layout = new GridLayoutManager(this, 2);
		//linear = new LinearLayoutManager(this);
		recyclerview.setLayoutManager(layout);
		
		//Connecting Adapter
		adapter = new MyRecyclerAdapter(MainActivity.this, lists);
		recyclerview.setAdapter(adapter);
		
		//OnClick Listener
		add.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

				String city = ac.getText().toString();
				ac.setText("");

				// downloading data
				final String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q="
						+ city
						+ "&APPID=c92075702c83996f17a5b194a340ea19&mode=json&units=metric&cnt=1";
				new AsyncHttpTask().execute(url);
				
				/*String imgurl =
				 "https://en.wikipedia.org/w/api.php?action=query&titles="+city+"&prop=images&format=json";
				 
				new AsyncHttpTask().execute(imgurl);*/

				
				
			
				
				
			}
		});

	}

	public class AsyncHttpTask extends AsyncTask<String, Void , Integer> {

		// final String TAG = null;

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected Integer doInBackground(String... params) {
			InputStream inputStream = null;
			Integer result = 0;
			HttpURLConnection urlConnection = null;
			try {
				URL url = new URL(params[0]);
				urlConnection = (HttpURLConnection) url.openConnection();

				urlConnection.setRequestMethod("GET");
				int statusCode = urlConnection.getResponseCode();

				// 200 represents HTTP OK
				if (statusCode == 200) {
					BufferedReader r = new BufferedReader(
							new InputStreamReader(
									urlConnection.getInputStream()));
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = r.readLine()) != null) {
						response.append(line);
					}
					parseResult(response.toString());
					//parseResponse(response.toString());
					result = 1; // Successful
				} else {
					result = 0; // "Failed to fetch data!";
				}
			} catch (Exception e) {
				// Log.d(TAG, e.getLocalizedMessage());
			}
			return result; // "Failed to fetch data!";
		}

		/*
		 * public byte[] getImage(String code) { HttpURLConnection con = null ;
		 * InputStream is = null; try { URL url = new URL("imgurl"); con =
		 * (HttpURLConnection) url.openConnection();
		 * con.setRequestMethod("GET"); con.setDoInput(true);
		 * con.setDoOutput(true); con.connect();
		 * 
		 * // Let's read the response is = con.getInputStream(); byte[] buffer =
		 * new byte[1024]; ByteArrayOutputStream baos = new
		 * ByteArrayOutputStream();
		 * 
		 * while ( is.read(buffer) != -1) baos.write(buffer);
		 * 
		 * return baos.toByteArray(); } catch(Throwable t) {
		 * t.printStackTrace(); } finally { try { is.close(); } catch(Throwable
		 * t) {} try { con.disconnect(); } catch(Throwable t) {} }
		 * 
		 * return null;
		 * 
		 * }
		 */

		@Override
		protected void onPostExecute(Integer result) {
			// Download complete. Let us update UI
			// progressBar.setVisibility(View.GONE);

			if (result == 1) {
				adapter = new MyRecyclerAdapter(MainActivity.this, lists);
				recyclerview.setAdapter(adapter);
			} else {
				Toast.makeText(MainActivity.this, "Failed to fetch data!",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void parseResult(String result) {
		
		try {
			JSONObject response = new JSONObject(result);

			// Getting Current Temperature
			JSONArray list = response.optJSONArray("list");
			JSONObject object0 = list.optJSONObject(0);
			JSONObject temp = object0.optJSONObject("temp");
			
			//Constructor
			Data object = new Data();
			
			object.setTemperature(temp.optInt("day"));

			// Getting Maximum and Minimum Temperature
			object.setMax(temp.optInt("max"));
			object.setMin(temp.optInt("min"));

			// Getting current Description
			JSONArray weather = object0.optJSONArray("weather");
			JSONObject weather0 = weather.optJSONObject(0);
			object.setDescription(weather0.optString("description"));

			// Getting User Entered Location Name
			JSONObject location = response.optJSONObject("city");
			object.setLocation(location.optString("name"));
			
			
			/*Data object = new Data();
			JSONObject query = response.optJSONObject("query");
			JSONObject page = query.optJSONObject("pages");
			JSONObject num = page.optJSONObject("");
			JSONArray image = num.optJSONArray("images");
			JSONObject image_7 = image.optJSONObject(7);
			object.setThumb(image_7.optInt("title"));
			*/
			
			
			
			
			

			if (null == lists) {
				lists = new ArrayList<Data>();
			}
			//Adding Parsed objects to the list
			lists.add(object);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	/*public void parseResponse(String result){
		
		try{
			
			JSONObject response = new JSONObject(result);
			
			JSONObject query = response.optJSONObject("query");
			JSONObject page = query.optJSONObject("pages");
			JSONObject num = page.getJSONObject("");
			JSONArray image = num.getJSONArray("images");
			JSONObject image_7 = image.getJSONObject(7);
			Data object = new Data();
			object.setThumb(image_7.optInt("title"));
			
			lists.add(object);
			
		}catch (JSONException e){
			e.printStackTrace();
		}
		
	}*/
	
	
	RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
		@Override
		public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
			super.onScrollStateChanged(recyclerView, newState);
			Log.e("ListView", "onScrollStateChanged");
		}

		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);
			// Could hide open views here if you wanted. //
		}
	};


	/*
	 * public class syncHttpTask extends AsyncTask<String, Void, Integer>{
	 * 
	 * 
	 * 
	 * //final String TAG = null;
	 * 
	 * @Override protected void onPreExecute() {
	 * 
	 * }
	 * 
	 * @Override protected Integer doInBackground(String... params) {
	 * InputStream inputStream = null; Integer result = 0; HttpURLConnection
	 * urlConnection = null; try { URL url = new URL(params[0]); urlConnection =
	 * (HttpURLConnection) url.openConnection();
	 * 
	 * urlConnection.setRequestMethod("GET"); int statusCode =
	 * urlConnection.getResponseCode();
	 * 
	 * // 200 represents HTTP OK if (statusCode == 200) { BufferedReader r = new
	 * BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	 * StringBuilder response = new StringBuilder(); String line; while ((line =
	 * r.readLine()) != null) { response.append(line); }
	 * parseResult(response.toString()); result = 1; // Successful } else {
	 * result = 0; //"Failed to fetch data!"; } } catch (Exception e) { //
	 * Log.d(TAG, e.getLocalizedMessage()); } return result;
	 * //"Failed to fetch data!"; }
	 * 
	 * 
	 * public byte[] getImage(String code) { HttpURLConnection con = null ;
	 * InputStream is = null; try { URL url = new URL("imgurl"); con =
	 * (HttpURLConnection) url.openConnection(); con.setRequestMethod("GET");
	 * con.setDoInput(true); con.setDoOutput(true); con.connect();
	 * 
	 * // Let's read the response is = con.getInputStream(); byte[] buffer = new
	 * byte[1024]; ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 * 
	 * while ( is.read(buffer) != -1) baos.write(buffer);
	 * 
	 * return baos.toByteArray(); } catch(Throwable t) { t.printStackTrace(); }
	 * finally { try { is.close(); } catch(Throwable t) {} try {
	 * con.disconnect(); } catch(Throwable t) {} }
	 * 
	 * return null;
	 * 
	 * }
	 * 
	 * @Override protected void onPostExecute(Integer result) { // Download
	 * complete. Let us update UI //progressBar.setVisibility(View.GONE);
	 * 
	 * if (result == 1) { adapter = new MyRecyclerAdapter(MainActivity.this,
	 * lists); recyclerview.setAdapter(adapter); } else {
	 * Toast.makeText(MainActivity.this, "Failed to fetch data!",
	 * Toast.LENGTH_SHORT).show(); } }
	 * 
	 * 
	 * private void parseResult(String result) {
	 * 
	 * try { JSONObject response = new JSONObject(result);
	 * 
	 * 
	 * JSONObject query = response.optJSONObject("query"); JSONObject page =
	 * query.optJSONObject("pages");
	 * 
	 * if(null == lists ){ lists = new ArrayList<Data>(); }
	 * 
	 * for(int i = 0; i < page.length(); i++){
	 * 
	 * JSONArray image = page.optJSONArray("images"); JSONObject num7 =
	 * image.optJSONObject(7); Data item = new Data();
	 * item.setThumb(num7.optInt("title")); lists.add(item);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * } catch (JSONException e) { e.printStackTrace(); } }
	 * 
	 * }
	 */
}
