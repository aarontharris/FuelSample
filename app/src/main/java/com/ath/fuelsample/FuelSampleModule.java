package com.ath.fuelsample;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.os.Bundle;

import com.ath.fuel.FuelModule;
import com.ath.fuel.Lazy;
import com.ath.fuel.err.FuelInjectionException;
import com.ath.fuelsample.things.Box;
import com.ath.fuelsample.things.BoxColored;
import com.ath.fuelsample.things.LittleBlackBox;
import com.ath.fuelsample.things.LittleBlueBox;
import com.ath.fuelsample.things.SampleExternalPojo;
import com.ath.fuelsample.things.SillyBoxStateManager;

public class FuelSampleModule extends FuelModule {
	private SampleExternalPojo mPojo = new SampleExternalPojo();

	public FuelSampleModule( Application app ) {
		super( app );
	}

	@Override protected void onFailure( Lazy lazy, FuelInjectionException exception ) {
		Log.e( exception, "Fuel Failure while obtaining %s", lazy );
	}

	@Override protected void configure() {
		super.configure();

		// The simplest binding, a type to an object
		// effectively turning that object into an injectable app singleton
		bind( SampleExternalPojo.class, mPojo );

		// Here we map any requests for a Box to a BoxColored.
		// Then map a BoxColored to a Provider
		// The provider is only constructed once ever, but its provide() method
		// is called once for every unique Lazy<Box>'s .get() method first call so that
		// the provider can evaluate conditions and decide.  If this eval is not needed
		// you can just use the pattern above for SampleExternalPojo which always
		// maps to the single instance for any condition.
		//
		// The provider injects some things to acquire state to make a decision.
		// Here we return a new instance of the Box based on state
		// however we can just as easily return the same instance if we wanted singleton
		bind( Box.class, BoxColored.class );
		bind( BoxColored.class, new BoxProvider() );
	}

	public static class BoxProvider extends FuelProviderSimple<BoxColored> {
		public static final int BOX_FLAVOR_BLACK = 1;
		public static final int BOX_FLAVOR_BLUE = 2;

		// We're choosing to offer a black box if thats how the state dictates
		// otherwise we're defaulting to blue.
		@Override public BoxColored provide( Lazy lazy, Object parent ) {

			// best practice with providers is to inject with the context the lazy was given
			// that way you are gauranteed to not brake the scope.
			// if an application injected this Box, we wouldn't want to try to use an Activity context
			Lazy<SillyBoxStateManager> mBoxState = Lazy.attain( lazy.getContext(), SillyBoxStateManager.class );
			BoxColored box = null;

			// Optional consideration of flavor
			int flavor = lazy.getFlavor() == null ? -1 : lazy.getFlavor();

			// Here we give precedence to flavor and if no flavor then we obey state
			// assuming if the developer explicitly requested a flavor we should honor it.
			if ( BOX_FLAVOR_BLACK == flavor ) {
				box = attainBlackBox();
			} else if ( BOX_FLAVOR_BLUE == flavor ) {
				box = attainBlueBox();
			} else if ( Color.BLACK == mBoxState.get().getColor() ) {
				box = attainBlackBox();
			} else {
				box = attainBlueBox();
			}
			return box;
		}

		private BoxColored attainBlueBox() {
			return new LittleBlueBox();
		}

		private BoxColored attainBlackBox() {
			return new LittleBlackBox();
		}

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
