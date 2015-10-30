
package com.aail.weatherapplicationv1.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.text.format.DateFormat;
import android.widget.Toast;

public final class CommonMethods {
	

	public static void showToast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}



	public static void startIntent(Activity current, Class<?> next) {
		Intent intent = new Intent(current, next);
		current.startActivity(intent);
	}

	/**
	 * Returns an Alert Dialog that can be display on the screen. context
	 * argument is specifies context of activity. listener argument is specifies
	 * onclicklistener. title argument is specifies the Title of alert. items
	 * argument is specifies array of CharSequence
	 * 
	 * <p>
	 * This method always returns AlertDialog view.
	 * 
	 * @param context
	 *            context of application or activity
	 * @param listener
	 *            onclicklistener for buttons
	 * @param title
	 *            the tile of the alert
	 * @param itams
	 *            array of CharSequence
	 * @return AlertDialog AlertDialog view at required screens.
	 * @see Alert with list of items.
	 */

	public static AlertDialog displayDialogWithList(Context context,
			OnClickListener listener, CharSequence[] items, String title) {
		AlertDialog dialog;
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setSingleChoiceItems(items, 0, listener);
		builder.setCancelable(false);

		dialog = builder.create();
		dialog.show();

		return dialog;
	}

	/**
	 * This method using for List of string convert into CharSequence array.
	 * list argument is specifies list of strings. fitstField argument is
	 * specifies first item of converted CharSequence array.
	 * 
	 * <p>
	 * This method always returns CharSequence array.
	 * 
	 * @param list
	 *            list of strings
	 * @param fitstField
	 *            the first item of converted CharSequence list.
	 * @return CharSequence array
	 */

	public static CharSequence[] convertListToCharArray(List<String> list,
			String fitstField) {

		CharSequence[] plans = new CharSequence[list.size() + 1];

		plans[0] = fitstField;

		int count = 1;

		for (int i = 0; i < list.size(); i++) {
			plans[count] = list.get(i);

			count++;
		}

		return plans;
	}

	
	/**
	 * This method using for Handler message to update the UI thread. handler
	 * argument is handler object. message argument is message of handler. arg
	 * argument is specifies message argument.
	 * 
	 * <p>
	 * 
	 * @param handler
	 *            handler object.
	 * @param message
	 *            message of handler.
	 * @param arg
	 *            message argument.
	 */

	public static void sendMsgToHandler(Handler handler, String message, int arg) {
		Message msg = new Message();
		if (message != null && !"".equals(message))
			msg.obj = (String) message;

		msg.arg1 = arg;

		handler.sendMessage(msg);
	}

	/**
	 * Check the Device internal memory availability.
	 */

	public static long getAvailableInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();

		return availableBlocks * blockSize;
	}

	/**
	 * Check whether the SDCard is available or not.
	 */

	public static boolean externalMemoryAvailable() {
		return android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
	}

	/**
	 * Check the Device external memory availability.
	 */

	public static long getAvailableExternalMemorySize() {
		if (externalMemoryAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();

			return availableBlocks * blockSize;
		}

		return 0;
	}

	/**
	 * Get device unique id for example,the IMEI for GSM and MEID or ESN for
	 * CDMA phones.
	 */

 

	public static void openNewActivity(Context context, Class<?> next) {
		Intent intent = new Intent(context, next);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}
	

	
	public static String getRoundedVal(double value)
	{
		DecimalFormat format = new DecimalFormat("0.00");
		format.setMaximumFractionDigits(2);
		return format.format(value);
	}
	
	public static String getUUID()
	{
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	/**
	 *  this method is used to get key from hashtable using value.
	 */

	public static String getKeyFromHash(Hashtable<String, String> maleRemarksTable,String seletedId) 
	{
		for (Map.Entry<String,String> entry : maleRemarksTable.entrySet()) 
		{
			
		    if(entry.getValue().equals(seletedId))
		    	return entry.getKey();
		}
		return null;
	}
	
	public static String sendGetRequest(String url) throws Exception {

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
	
	
	public static String getDateFromTime(long time)
	{
		    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
		    cal.setTimeInMillis(time*1000);
		    String date = DateFormat.format("dd-MM-yyyy", cal).toString();
		    return date;
	}
	
	
}
