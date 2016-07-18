package com.ath.fuelsample.things;

import android.app.Activity;

import com.ath.fuel.ActivitySingleton;
import com.ath.fuel.FuelInjector;
import com.ath.fuel.Lazy;
import com.ath.fuelsample.Log;

@ActivitySingleton
public class SampleAsyncSingleton {
	private final Lazy<Activity> mActivity = Lazy.attain( this, Activity.class );
	private final Lazy<SampleActivitySingleton> mSampleActivitySingleton = Lazy.attain( this, SampleActivitySingleton.class );

	public void doStuff() {
		Log.d( "doStuff: on %s w/ %s & %s", FuelInjector.getPid(), mActivity.get(), mSampleActivitySingleton.get() );
	}

}
