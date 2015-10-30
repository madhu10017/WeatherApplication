package com.aail.weatherapplicationv1;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


@SuppressLint("NewApi") public class Map extends FragmentActivity  {

	 protected static final Marker polylineFinal = null;
	GoogleMap googleMap;
	    MarkerOptions markerOptions;
	    boolean markerClicked;
	    LatLng latLng;
	    double latitude;
	    double longitude;
	    String location,map_temperature; 
	    Polyline polyline;
	    ArrayList<LatLng> points;
	    int k=1;
	    PolylineOptions polylineOptions;
	    MainActivity main_object= new MainActivity();
       

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.googlemap);
	        
	        points = new ArrayList<LatLng>();
	        
	        
	        
	        final Button normal = (Button) findViewById(R.id.normal);
	        normal.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                // Perform action on click
	            	
	            	  googleMap .setMapType(GoogleMap.MAP_TYPE_NORMAL);
	            }
	        });
	        final Button terrain = (Button) findViewById(R.id.terrain);
	        terrain.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	  googleMap .setMapType(GoogleMap.MAP_TYPE_TERRAIN);
	                // Perform action on click
	            }
	        });
	        final Button hybrid = (Button) findViewById(R.id.hybrid);
	        hybrid.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                // Perform action on click

	     	       googleMap .setMapType(GoogleMap.MAP_TYPE_HYBRID);
	            }
	        });
	        final Button satellite = (Button) findViewById(R.id.satellite);
	        satellite.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                // Perform action on click

	      	      googleMap .setMapType(GoogleMap.MAP_TYPE_SATELLITE);
	            }
	        });

	 

	        
	        String location =main_object.location_name;
	        map_temperature=main_object.g_temp;
	        		
	        if(location!=null && !location.equals("")){
                new GeocoderTask().execute(location);
            }

	        
	        TextView map_loc=(TextView)findViewById(R.id.map_loc);
	        
	   

	       TextView map_temp=(TextView)findViewById(R.id.map_temp);
	        map_loc.setText(location);
	        map_temp.setText(Html.fromHtml(String.valueOf(map_temperature)+"<sup>o</sup>"));
	        
	        
	        
	        
	        SupportMapFragment supportMapFragment = (SupportMapFragment)
	                getSupportFragmentManager().findFragmentById(R.id.fragment1);

	        // Getting a reference to the map
	        googleMap = supportMapFragment.getMap();
	        
	        googleMap.setMyLocationEnabled(true);
	        
	
	      
	     
	        
	        // Setting OnClick event listener for the Google Map
	        googleMap.setOnMapClickListener(new OnMapClickListener() {
	 
	            @Override
	            public void onMapClick(LatLng point) {
	            	points.add(latLng);
	            	
	                // Instantiating the class MarkerOptions to plot marker on the map
	                MarkerOptions markerOptions = new MarkerOptions();
	 
	                // Setting latitude and longitude of the marker position
	                markerOptions.position(point);

	             
	                // Setting title of the info window of the marker
	                markerOptions.title("Position");
	                
	 
	                // Setting the content of the info window of the marker
	                markerOptions.snippet("Latitude:"+point.latitude+","+"Longitude:"+point.longitude);
	 
	                getlocation(point.latitude,point.longitude);
	                
	               
	                
	              // Toast.makeText(getApplicationContext(), point.latitude+""+point.longitude, Toast.LENGTH_LONG).show(); 
	               
	                // Instantiating the class PolylineOptions to plot polyline in the map
	               PolylineOptions polylineOptions = new PolylineOptions();
	                
	               // Polyline pp= googleMap.addPolyline(new PolylineOptions());
	 
	                // Setting the color of the polyline
	                polylineOptions.color(Color.RED);
	 
	                // Setting the width of the polyline
	                polylineOptions.width(5);
	 
	                // Adding the taped point to the ArrayList
	                points.add(point);
	                
	            	
	 
	                // Setting points of polyline
	                polylineOptions.addAll(points);
	 
	                // Adding the polyline to the map
	               googleMap.addPolyline(polylineOptions);
	            
	               
	                polyline= googleMap.addPolyline(polylineOptions);
	                
	                

	 	             // polyline.setColor(Color.GREEN);
	 
	                // Adding the marker to the map
	                googleMap.addMarker(markerOptions);
	                
	           
	     
	             
	               // Toast.makeText(getApplicationContext(), latLng+"To:"+points.get(1), Toast.LENGTH_LONG).show();
	               
	            	                

	                CalculationByDistance(latLng,points.get(k));
	                k=k+2;
	            	
	            }
	        });
	 
	        
	    	
	       
	   
	        
	        googleMap.setOnMapLongClickListener(new OnMapLongClickListener() {
   	       	 
 	            @Override
 	            public void onMapLongClick(LatLng point) {
 	                // Clearing the markers and polylines in the google map

 	            	
 	            
 	            
 	            	 
 	           
	             googleMap.clear();
	             
	             points.clear();
	            
	             points.add(latLng);//dont modify these line
 	             k=2;//dont modify these line
 	             
 	             
 	              markerOptions = new MarkerOptions();
	               markerOptions.position(latLng);
	             
	               markerOptions.title("Latitude:"+latitude+","+"Longitude:"+longitude);
	               	        
	                googleMap.addMarker(markerOptions);
	                
 	            
	             
 	            }
 	        });		
	        
	    
	        
	    
	        
	  	/*  googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {
				 
	          
	  	  	@Override
	  	  	public boolean onMarkerClick(Marker marker) {
	  	  	
	  	  		
	  	  		 if(markerClicked){
	  	  			   
	  	  			   if(polyline != null){
	  	  			    polyline.remove();
	  	  			    polyline = null;
	  	  			   }
	  	  			   
	  	  			   polylineOptions.add(marker.getPosition());
	  	  			   polylineOptions.color(Color.RED);
	  	  			   polyline = googleMap.addPolyline(polylineOptions);
	  	  			  }else{
	  	  			   if(polyline != null){
	  	  			    polyline.remove();
	  	  			    polyline = null;
	  	  			   }
	  	  			   
	  	  			   polylineOptions = new PolylineOptions().add(marker.getPosition());
	  	  			   markerClicked = true;
	  	  			  }
	  	  			  
	  	  		// TODO Auto-generated method stub
	  	  		return true;
	  	      
	            }
	        });		
		
	  	  	  
	  		    
	  		    
*/

	    	 
	    }
	    
	    
	  
	    

		public double CalculationByDistance(LatLng StartP, LatLng EndP) {
	        int Radius = 6371;// radius of earth in Km
	       
	        double lat1 = StartP.latitude;
	        double lat2 = EndP.latitude;
	        double lon1 = StartP.longitude;
	        double lon2 = EndP.longitude;
	      
	        double dLat = Math.toRadians(lat2 - lat1);
	        double dLon = Math.toRadians(lon2 - lon1);
	        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
	                + Math.cos(Math.toRadians(lat1))
	                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
	                * Math.sin(dLon / 2);
	        double c = 2 * Math.asin(Math.sqrt(a));
	        double valueResult = Radius * c;
	        double km = valueResult / 1;
	        DecimalFormat newFormat = new DecimalFormat("####");
	        int kmInDec = Integer.valueOf(newFormat.format(km));
	        double meter = valueResult % 1000;
	        int meterInDec = Integer.valueOf(newFormat.format(meter));
	        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
	                + " Meter   " + meterInDec);
	        
	        
	        
	        Double distance_double = valueResult;
	        Integer distance_int = distance_double.intValue();
	        
	      //  int distance=Double.parseDouble(valueResult);
	       Toast.makeText(getApplicationContext(),"Distance: "+distance_int+" km", Toast.LENGTH_LONG).show();

	        return Radius * c;
	        
	
	       
		}  
	        

	       
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }

	    // An AsyncTask class for accessing the GeoCoding Web Service
	    private class GeocoderTask extends AsyncTask<String, Void, List<Address>>{

	        @Override
	        protected List<Address> doInBackground(String... locationName) {
	            // Creating an instance of Geocoder class
	            Geocoder geocoder = new Geocoder(getBaseContext());
	            List<Address> addresses = null;

	            try {
	                // Getting a maximum of 3 Address that matches the input text
	                addresses = geocoder.getFromLocationName(locationName[0], 3);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            return addresses;
	        }

	        @Override
	        protected void onPostExecute(List<Address> addresses) {

	            if(addresses==null || addresses.size()==0){
	                Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
	            }

	    

	            // Adding Markers on Google Map for each matching address
	            for(int i=0;i<addresses.size();i++){

	                Address address = (Address) addresses.get(i);

	                // Creating an instance of GeoPoint, to display in Google Map
	                latLng = new LatLng(address.getLatitude(), address.getLongitude());
	                latitude=address.getLatitude();
	                longitude=address.getLongitude();
	                
	               
	                
	                String addressText = String.format("%s, %s",
	                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
	                        address.getCountryName());

	               markerOptions = new MarkerOptions();
	               markerOptions.position(latLng);
	                
	              
	                              
	               markerOptions.title("Temperature:"+map_temperature+" F");

	              
	        
	             //  markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map1));
	               
	                
	                googleMap.addMarker(markerOptions);
	                
	          
	             
	                // Locate the first location
	                if(i==0)
	                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
	                
	                
	                CameraPosition cameraPosition = new CameraPosition.Builder().target(
	    	        		new LatLng(latitude, longitude)).zoom(8).build();
	    	        		googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
	    	        		
	    	        	  

	            }
	        }
	    }
	    
	    
	  public void getlocation(double x,double y)
	  {
		  Geocoder geocoder = new Geocoder(Map.this, Locale.getDefault());
		 
		    List<Address> addresses = null;
		    
		    try {
				addresses = geocoder.getFromLocation(x,y, 1);
				String map_location_clicked=addresses.get(0).getLocality();
				//Toast.makeText(getApplicationContext(),"Clicked Location:"+ map_location_clicked, Toast.LENGTH_LONG).show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }






}

		

	