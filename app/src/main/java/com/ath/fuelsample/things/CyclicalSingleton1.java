package com.ath.fuelsample.things;

import com.ath.fuel.AppSingleton;
import com.ath.fuel.Lazy;

@AppSingleton
public class CyclicalSingleton1 {
	//private final Lazy<CyclicalSingleton2> mCyclicalSingleton2Lazy = Lazy.attain( this, CyclicalSingleton2.class );

	public void doStuff() {
		//mCyclicalSingleton2Lazy.get();
	}
}
