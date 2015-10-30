
package com.aail.weatherapplicationv1.utils;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;

public class PhoneState extends PhoneStateListener
{
	public int signalStenths = 0;
	
	public static String signalType = null;

	@Override
	public void onSignalStrengthsChanged(SignalStrength signalStrength)
	{
		super.onSignalStrengthsChanged(signalStrength);
		signalStenths = signalStrength.getGsmSignalStrength();
		
		if (signalStenths > 30) 
		{
			signalType = "Good";
		} 
		else if (signalStenths > 20 && signalStenths < 30) 
		{
			signalType = "Average";
		}
		else if (signalStenths < 20) 
		{
			signalType ="Weak";
		}
	}

};