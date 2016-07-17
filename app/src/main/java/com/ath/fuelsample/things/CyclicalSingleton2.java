package com.ath.fuelsample.things;

import com.ath.fuel.AppSingleton;
import com.ath.fuel.Lazy;

@AppSingleton
public class CyclicalSingleton2 {
	//private final Lazy<CyclicalSingleton1> mCyclicalSingleton1Lazy = Lazy.attain( this, CyclicalSingleton1.class );

	public void doStuff() {
		//mCyclicalSingleton1Lazy.get();
	}
}
