package com.ath.fuelsample.things;

import com.ath.fuel.FragmentSingleton;

@FragmentSingleton
public class SampleFragSingleton {

	public String doStuff() {
		return String.format( "An Activity should not be able to inject me %s", this );
	}

}
