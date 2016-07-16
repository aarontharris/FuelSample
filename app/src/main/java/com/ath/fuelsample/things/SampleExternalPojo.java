package com.ath.fuelsample.things;

import android.app.Activity;
import android.app.Application;

import com.ath.fuel.Lazy;
import com.ath.fuelsample.Log;

// Pretend as though this class is external blackbox code.
// This serves as an example on how you can bind it to Fuel
// Note how there is no Annotation here
public class SampleExternalPojo {

	// Lets pretend this external class requires some lifecycle awareness

	public void init( Activity activity ) {
		Log.d( "SampleExternalPojo.init %s", activity );
	}

	public void start( Activity activity ) {
		Log.d( "SampleExternalPojo.start %s", activity );
	}

	public void stop( Activity activity ) {
		Log.d( "SampleExternalPojo.stop %s", activity );
	}

	public String doStuff() {
		return String.format( "Hello World I am %s", this );
	}

}
