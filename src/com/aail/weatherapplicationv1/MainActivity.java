package com.aail.weatherapplicationv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.aail.weatherapplicationv1.sync.DetailedWeatherCalling;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


public class MainActivity extends Activity {

	AutoCompleteTextView autoCompleteTextView1;
	TextView textView1,textView2,description1;
	public static String location_name;
	public static int position;
	String temperature_reponse;
	ImageView dynamic_image;
	ViewFlipper viewFlipper;
	 private float lastX;
	public static String g_temp;
	
	
	
	private static final String[] Cities = new String[] {
        "Vijayawada", "Mangalagiri", "Tenali", "Guntur", "Hyderabad", "Narasaraopet", "Suryapet", "Bapatla", "ongole"
        , "Cheerala", "Nellore","Gannavaram"};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        
        FindLocation mGPS = new FindLocation(this);
        
        autoCompleteTextView1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
	    textView1=(TextView) findViewById(R.id.textView1);
	    textView2=(TextView)findViewById(R.id.textView2);
	    dynamic_image =(ImageView) findViewById(R.id.imageView1);
	    description1 =(TextView) findViewById(R.id.description1);
	   
	    
	    //Hardcoded city list for example
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, Cities);
        AutoCompleteTextView autoCompleteTextView1 = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView1);
        autoCompleteTextView1.setAdapter(adapter);//End of city list
        
          
    
	    if(mGPS.canGetLocation ){
		    mGPS.getLocation();
		    
		    Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
		    List<Address> addresses = null;
			try {
				addresses = geocoder.getFromLocation(mGPS.getLatitude(), mGPS.getLongitude(), 1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    if (addresses.size() > 0) 
		        System.out.println(addresses.get(0).getLocality());
		    autoCompleteTextView1.setText(addresses.get(0).getLocality());
		    }
	    
	    Button getData=(Button)findViewById(R.id.go);
        getData.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
               
                AutoCompleteTextView cityName = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
                location_name=cityName.getText().toString();
                new URLCalling().execute();
            /*	                       
                DetailedWeatherCalling sync = new DetailedWeatherCalling(MainActivity.this, location_name);
                sync.execute();
               */
            }
        });
        
        Button detailed_weather=(Button)findViewById(R.id.detail);
        detailed_weather.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
               position=0;
            	Intent intent = new Intent(MainActivity.this, DetailedWeather.class);
            	startActivity(intent);
                
                }
        });
        
        
        Button next_day1=(Button)findViewById(R.id.nextday1);
        next_day1.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
            	position=1;
            	Intent intent = new Intent(MainActivity.this, DetailedWeather.class);
            	startActivity(intent);
                
                }
        });
        

        Button next_day2=(Button)findViewById(R.id.nextday2);
        next_day2.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
            	position=2;
            	Intent intent = new Intent(MainActivity.this, DetailedWeather.class);
            	startActivity(intent);
                
                }
        });
        
        Button next_day3=(Button)findViewById(R.id.nextday3);
        next_day3.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
            	position=3;
            	Intent intent = new Intent(MainActivity.this, DetailedWeather.class);
            	startActivity(intent);
                
                }
        });
        
        Button next_day4=(Button)findViewById(R.id.nextday4);
        next_day4.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
            	position=4;
            	Intent intent = new Intent(MainActivity.this, DetailedWeather.class);
            	startActivity(intent);
                
                }
        });
        
        Button next_day5=(Button)findViewById(R.id.nextday5);
        next_day5.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
            	position=5;
            	Intent intent = new Intent(MainActivity.this, DetailedWeather.class);
            	startActivity(intent);
                
                }
        });
        
        Button next_day6=(Button)findViewById(R.id.nextday6);
        next_day6.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
            	position=6;
            	Intent intent = new Intent(MainActivity.this, DetailedWeather.class);
            	startActivity(intent);
                
                }
        });
        
        Button grid=(Button)findViewById(R.id.grid);
        grid.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
            	Intent intent = new Intent(MainActivity.this, RecyclermainActivity.class);
            	startActivity(intent);
                
                }
        });
        
          
        //For Date
        Calendar c = Calendar.getInstance();
        String sDate = c.get(Calendar.DAY_OF_MONTH) + "-" 
        + (c.get(Calendar.MONTH)+1)
        + "-" + c.get(Calendar.YEAR);
        textView1.setText(sDate);//Closed Date
       	    
	 }//Closed OnCreate Method
    
   
   
    
    public class URLCalling extends AsyncTask<Void, Void, Void> {

    	ProgressDialog dialog;
    	String temperature_response;
    	
    	// Runs on UIThread
    	@Override
    	protected void onPreExecute() {
    		// TODO Auto-generated method stub
    		super.onPreExecute();
    		dialog = ProgressDialog.show(MainActivity.this, "Please Wait...",
    				"Connecting to Server....");

    	
    	}

    	@Override
    	protected Void doInBackground(Void... params) {
    		// TODO Auto-generated method stub
    		// Don't write any UI operations

    		// Server hits
    		// Response handling
    		try {
    			temperature_response=sendGetRequest("http://api.openweathermap.org/data/2.5/forecast/daily?q="+location_name+"&APPID=c92075702c83996f17a5b194a340ea19&mode=json&units=metric&cnt=7");
    			
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
    				
    			JSONObject jsonObject = new JSONObject(temperature_response);
    			JSONArray json_list_object= jsonObject.getJSONArray("list");
				JSONObject jsonobject0=json_list_object.getJSONObject(0);
				JSONObject json_temp_object= jsonobject0.getJSONObject("temp");
			
				float day_temp=Float.parseFloat(json_temp_object.getString("day"));
				NumberFormat df = NumberFormat.getNumberInstance();
                df.setMaximumFractionDigits(0);  
                textView2.setText(Html.fromHtml(String.valueOf(df.format(day_temp))+"<sup>o</sup>"));
    			
                g_temp= df.format(day_temp);
                
                JSONArray json_weather_object= jsonobject0.getJSONArray("weather");
				JSONObject jsonweatherobject0=json_weather_object.getJSONObject(0);
				description1.setText(jsonweatherobject0.getString("description"));
				
				String data_des=jsonweatherobject0.getString("description");
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
    	 			
					
				Button nextday1=(Button) findViewById(R.id.nextday1);
				Button nextday2=(Button) findViewById(R.id.nextday2);
				Button nextday3=(Button) findViewById(R.id.nextday3);
				Button nextday4=(Button) findViewById(R.id.nextday4);
				Button nextday5=(Button) findViewById(R.id.nextday5);
				Button nextday6=(Button) findViewById(R.id.nextday6);
				
				JSONObject jsonobject1=json_list_object.getJSONObject(1);
				JSONObject json_temp_object1= jsonobject1.getJSONObject("temp");
				float day_temp1=Float.parseFloat(json_temp_object1.getString("day"));
				
				long batch_date = Long.parseLong(jsonobject1.getString("dt")); 
				Date dt = new Date (batch_date * 1000); 
				SimpleDateFormat sfd = new SimpleDateFormat("MMM d\n E");
				String nextday1_1=sfd.format(dt)+" \n";
				nextday1_1+=(Html.fromHtml(String.valueOf(df.format(day_temp1))+"<sup>o</sup>"));
				nextday1.setText(nextday1_1);
					 /*nextday1.setText(sfd.format(dt)+"\n"+Html.fromHtml(String.valueOf(df.format(day_temp1))+"<sup>o</sup>"));*/
					/* nextday1.setText(sfd.format(dt));*/
					 
					
					 
					JSONObject jsonobject2=json_list_object.getJSONObject(2);
					JSONObject json_temp_object2= jsonobject2.getJSONObject("temp");
					float day_temp2=Float.parseFloat(json_temp_object2.getString("day"));
					
					 long batch_date2 = Long.parseLong(jsonobject2.getString("dt")); 
					 Date dt2 = new Date (batch_date2 * 1000);
					 String nextday1_2=sfd.format(dt2)+"\n";
					 nextday1_2+=(Html.fromHtml(String.valueOf(df.format(day_temp2))+"<sup>o</sup>"));
					 nextday2.setText(nextday1_2);
					 /*nextday2.setText(Html.fromHtml(String.valueOf(df.format(day_temp2))+"<sup>o</sup>"));*/
					
					
					JSONObject jsonobject3=json_list_object.getJSONObject(3);
					JSONObject json_temp_object3= jsonobject3.getJSONObject("temp");
					float day_temp3=Float.parseFloat(json_temp_object3.getString("day"));
					
					 long batch_date3 = Long.parseLong(jsonobject3.getString("dt")); 
					 Date dt3 = new Date (batch_date3 * 1000);
					 String nextday1_3=sfd.format(dt3)+"\n";
					 nextday1_3+=(Html.fromHtml(String.valueOf(df.format(day_temp3))+"<sup>o</sup>"));
					 nextday3.setText(nextday1_3);
					/*nextday3.setText(Html.fromHtml(String.valueOf(df.format(day_temp3))+"<sup>o</sup>"));*/
					
					JSONObject jsonobject4=json_list_object.getJSONObject(4);
					JSONObject json_temp_object4= jsonobject4.getJSONObject("temp");
					float day_temp4=Float.parseFloat(json_temp_object4.getString("day"));
					/*nextday4.setText(Html.fromHtml(String.valueOf(df.format(day_temp4))+"<sup>o</sup>"));*/
					
					 long batch_date4 = Long.parseLong(jsonobject4.getString("dt")); 
					 Date dt4 = new Date (batch_date4 * 1000);
					 String nextday1_4=sfd.format(dt4)+"\n";
					 nextday1_4+=(Html.fromHtml(String.valueOf(df.format(day_temp4))+"<sup>o</sup>"));
					 nextday4.setText(nextday1_4);
					
					JSONObject jsonobject5=json_list_object.getJSONObject(5);
					JSONObject json_temp_object5= jsonobject5.getJSONObject("temp");
					float day_temp5=Float.parseFloat(json_temp_object5.getString("day"));
					nextday5.setText(Html.fromHtml(String.valueOf(df.format(day_temp5))+"<sup>o</sup>"));
					
					long batch_date5 = Long.parseLong(jsonobject5.getString("dt")); 
					 Date dt5 = new Date (batch_date5 * 1000);
					 String nextday1_5=sfd.format(dt5)+"\n";
					 nextday1_5+=(Html.fromHtml(String.valueOf(df.format(day_temp5))+"<sup>o</sup>"));
					 nextday5.setText(nextday1_5);
					
					JSONObject jsonobject6=json_list_object.getJSONObject(6);
					JSONObject json_temp_object6= jsonobject6.getJSONObject("temp");
					float day_temp6=Float.parseFloat(json_temp_object6.getString("day"));
					nextday6.setText(Html.fromHtml(String.valueOf(df.format(day_temp6))+"<sup>o</sup>"));
					
					long batch_date6 = Long.parseLong(jsonobject6.getString("dt")); 
					 Date dt6 = new Date (batch_date6 * 1000);
					 String nextday1_6=sfd.format(dt6)+"\n";
					 nextday1_6+=(Html.fromHtml(String.valueOf(df.format(day_temp6))+"<sup>o</sup>"));
					 nextday6.setText(nextday1_6);
					
    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
   		
    	}// Completed Post Method
 

    }//Closed Main Activity Class


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
public boolean onTouchEvent(MotionEvent touchevent) {
    
    switch (touchevent.getAction()) {

     

    case MotionEvent.ACTION_DOWN: 

        lastX = touchevent.getX();

        break;

   case MotionEvent.ACTION_UP: 

        float currentX = touchevent.getX();

         

        // Handling left to right screen swap.

        if (lastX < currentX) {

             

            // If there aren't any other children, just break.

            if (viewFlipper.getDisplayedChild() == 0)

                break;

             

           // Next screen comes in from left.

            viewFlipper.setInAnimation(this, R.anim.slide_in_from_left);

            // Current screen goes out from right. 

            viewFlipper.setOutAnimation(this, R.anim.slide_out_to_right);

             

            // Display next screen.

            viewFlipper.showNext();

         }

                                  

        // Handling right to left screen swap.

         if (lastX > currentX) {

              

             // If there is a child (to the left), kust break.

             if (viewFlipper.getDisplayedChild() == 1)

                 break;

 

             // Next screen comes in from right.

             viewFlipper.setInAnimation(this, R.anim.slide_in_from_right);

            // Current screen goes out from left. 

             viewFlipper.setOutAnimation(this, R.anim.slide_out_to_left);

              

            // Display previous screen.

             viewFlipper.showPrevious();

         }

         break;

     }

     return false;

}

}