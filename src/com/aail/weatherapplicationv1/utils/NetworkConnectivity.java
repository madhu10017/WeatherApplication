
package com.aail.weatherapplicationv1.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class NetworkConnectivity 
{
	public static int singalStenths =0;
	public static String strength = null;
	
	private static final String  MOBILE	= "Mobile";
	private static final String  WIFI	= "wifi";
	
	/**
	 * Returns boolean. 
	 * context argument is specifies context of activity or application context.
	 * 
	 * <p>
	 * This method to verify the availability of Internet on the device
	 *
	 * @param  context 	   	   context of application or activity
	 */
	
	public static boolean netWorkAvailability(final Context context) 
	{
		boolean networkAvailability			= false;	
		
		final ConnectivityManager cManager	= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo networkInfos	= cManager.getActiveNetworkInfo();
		
		if(networkInfos != null && networkInfos.isConnectedOrConnecting())
			networkAvailability	= true;
			
		return networkAvailability;
	}
	

	
	public static void networkSignalStrength(Context context)
	{
		PhoneState myListener   = new PhoneState();
	    TelephonyManager telManager  = ( TelephonyManager )context.getSystemService(Context.TELEPHONY_SERVICE);
	    telManager.listen(myListener ,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
		
	}
	
	/**
	 * Returns network type. 
	 * context argument is specifies context of activity or application context.
	 * 
	 * <p>
	 * This method to verify the availability of network type.
	 *
	 * @param  context 	   	   context of application or activity
	 */
	
	public static String netWorkType(final Context context) 
	{
		String networkType	= null;
		
		final ConnectivityManager cManager	= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo[] networkInfos	= cManager.getAllNetworkInfo();
				
		for(int i=0; i<networkInfos.length; i++)
		{
			if(networkInfos[i].getType() == ConnectivityManager.TYPE_MOBILE && networkInfos[i].isAvailable())
			{
				networkType	= MOBILE;
			}
			
			if(networkInfos[i].getType() == ConnectivityManager.TYPE_WIFI && networkInfos[i].isAvailable())
			{
				networkType	= WIFI;
			}
		}
		
		return networkType;
	}
}
