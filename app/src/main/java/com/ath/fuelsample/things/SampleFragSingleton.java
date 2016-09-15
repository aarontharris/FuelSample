package com.ath.fuelsample.things;

import com.ath.fuel.FragmentSingleton;
import com.ath.fuel.Lazy;

@FragmentSingleton
public class SampleFragSingleton {
	private final Lazy<MemoryEater> mMemoryEater = Lazy.attain( this, MemoryEater.class );
	private final Lazy<SampleFragSingleton2> mSampleFragSingleton2 = Lazy.attain( this, SampleFragSingleton2.class );

	public String doStuff() {
		mMemoryEater.get();
		mSampleFragSingleton2.get().helloWorld();
		return String.format( "An Activity should not be able to inject me %s", this );
	}

}
