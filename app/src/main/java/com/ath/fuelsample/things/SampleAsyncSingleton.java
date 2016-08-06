package com.ath.fuelsample.things;

import android.app.Activity;

import com.ath.fuel.ActivitySingleton;
import com.ath.fuel.Lazy;
import com.ath.fuelsample.Log;

@ActivitySingleton
public class SampleAsyncSingleton {
	private final Lazy<Activity> mActivity = Lazy.attain( this, Activity.class );
	private final Lazy<SampleActivitySingleton> mSampleActivitySingleton = Lazy.attain( this, SampleActivitySingleton.class );
	private final Lazy<MemoryEater> mMemoryEater = Lazy.attain( this, MemoryEater.class );

	public void doStuff() {
		mMemoryEater.get();
		Log.d( "doStuff: on %s w/ %s & %s", Thread.currentThread().getId(), mActivity.get(), mSampleActivitySingleton.get() );
	}
}
