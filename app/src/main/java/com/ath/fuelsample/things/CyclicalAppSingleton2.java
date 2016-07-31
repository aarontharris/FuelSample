package com.ath.fuelsample.things;

import com.ath.fuel.AppSingleton;
import com.ath.fuel.Lazy;
import com.ath.fuelsample.Log;

@AppSingleton
public class CyclicalAppSingleton2 {
	private final Lazy<CyclicalAppSingleton1> mCyclicalSingleton1Lazy = Lazy.attain( this, CyclicalAppSingleton1.class );
	private final Lazy<MemoryEater> mMemoryEater = Lazy.attain( this, MemoryEater.class );

	public void doStuff() {
		Log.d("doStuff 2");
		mCyclicalSingleton1Lazy.get().doCycleTermination();
		mMemoryEater.get();
	}
}
