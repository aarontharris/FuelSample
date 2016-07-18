package com.ath.fuelsample.things;

import com.ath.fuel.AppSingleton;
import com.ath.fuel.Lazy;
import com.ath.fuelsample.Log;

@AppSingleton
public class CyclicalAppSingleton1 {
	private final Lazy<CyclicalAppSingleton2> mCyclicalSingleton2Lazy = Lazy.attain( this, CyclicalAppSingleton2.class );

	public void doStuff() {
		Log.d("doStuff 1");
		mCyclicalSingleton2Lazy.get().doStuff();
	}

	public void doCycleTermination() {
		Log.d("the end");
	}
}
