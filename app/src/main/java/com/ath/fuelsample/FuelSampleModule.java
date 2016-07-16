package com.ath.fuelsample;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.ath.fuel.FuelModule;
import com.ath.fuel.Lazy;

public class FuelSampleModule extends FuelModule {
	private SampleExternalPojo mPojo = new SampleExternalPojo();

	public FuelSampleModule( Application app ) {
		super( app );
	}

	@Override protected void configure() {
		super.configure();

		bind( SampleExternalPojo.class, mPojo );
	}

	@Override protected void onActivityCreated( Activity activity, Bundle savedInstanceState ) {
		super.onActivityCreated( activity, savedInstanceState );
		mPojo.init( activity );
	}

	@Override protected void onActivityResumed( Activity activity ) {
		super.onActivityResumed( activity );
		mPojo.start( activity );
	}

	@Override protected void onActivityStopped( Activity activity ) {
		mPojo.stop( activity );
		super.onActivityStopped( activity );
	}
}
