package com.ath.fuelsample.things;

import android.app.Activity;

import com.ath.fuel.Lazy;
import com.ath.fuelsample.Log;

public class SamplePojoWithActivityScope {
	//private final Lazy<SampleActivitySingleton> mSampleActivitySingleton = Lazy.attain( this, SampleActivitySingleton.class );
	private final Lazy<MemoryEater> mMemoryEater = Lazy.attain( this, MemoryEater.class );

	public SamplePojoWithActivityScope( Activity activity ) {
		Log.d("SamplePojoWithActivityScope( %s )", activity );
	}

	public String doStuff() {
		mMemoryEater.get();
		return String.format( "Hello World I am %s w/ %s", this, "null" ); //mSampleActivitySingleton.get() );
	}
	
}
