package com.ath.fuelsample.things;

import com.ath.fuel.FragmentSingleton;
import com.ath.fuel.Lazy;

@FragmentSingleton
public class SampleFragSingleton {
	private final Lazy<MemoryEater> mMemoryEater = Lazy.attain( this, MemoryEater.class );

	public String doStuff() {
		mMemoryEater.get();
		return String.format( "An Activity should not be able to inject me %s", this );
	}

}
