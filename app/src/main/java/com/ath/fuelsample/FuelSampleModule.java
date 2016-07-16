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


		// Here we map any requests for a Box to a BoxColored.
		// Then map a BoxColored to a Provider
		// The provider is only constructed once ever, but its provide() method
		// is called every time the Lazy<Box>'s .get() method is called so that
		// the provider can evaluate conditions and decide.  If this eval is not needed
		// you can just use the pattern above for SampleExternalPojo which always
		// maps to the single instance for any condition.
		//
		// The provider injects some things to acquire state to make a decision.
		// Here we return a new instance of the Box based on state
		// however we can just as easily return the same instance if we wanted singleton
		bind( Box.class, BoxColored.class );
		bind( BoxColored.class, new FuelProviderSimple<BoxColored>() {
			private final Lazy<Application> mApp = Lazy.attain( this, Application.class );

			@Override public BoxColored provide( Lazy lazy, Object parent ) {
				// mApp.get().getResources();
				boolean someState = true; // use App to get some state to make a decision
				if ( someState ) {
					return new LittleBlackBox();
				}
				return new LittleBlueBox();
			}
		} );
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
