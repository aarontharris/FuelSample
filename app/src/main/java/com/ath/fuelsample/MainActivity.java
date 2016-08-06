package com.ath.fuelsample;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.ath.fuel.FuelInjector;
import com.ath.fuel.Lazy;
import com.ath.fuelsample.FuelSampleModule.BoxProvider;
import com.ath.fuelsample.data.Box;
import com.ath.fuelsample.data.BoxColored;
import com.ath.fuelsample.things.CyclicalActivitySingleton1;
import com.ath.fuelsample.things.CyclicalAppSingleton1;
import com.ath.fuelsample.things.CyclicalObject1;
import com.ath.fuelsample.things.MyClass;
import com.ath.fuelsample.things.SampleActivitySingleton;
import com.ath.fuelsample.things.SampleAppSingleton;
import com.ath.fuelsample.things.SampleAsyncSingleton;
import com.ath.fuelsample.things.SampleExternalPojo;
import com.ath.fuelsample.things.SamplePojoWithActivityScope;
import com.ath.fuelsample.things.SillyBoxStateManager;
import com.ath.fuelsample.things.SimpleAppSingleton;

public class MainActivity extends AppCompatActivity {
	// MainActivity is the parent
	// mAppSingleton and mActivitySingleton are the dependencies
	// parents are scoped by the context they use ignite themselves
	// Activities are a context, so naturally they are Activity Scoped.
	// Activity Scope is finer than App Scope and App Scope is broader than Activity Scope.
	// Finer scope has visibility to broader scope, but broader scope does not have visibility to finer scope.
	// That means App Scoped Singletons cannot inject Activity Scoped Singletons
	// However Activity Scoped Singletons can inject App Scoped Singletons.
	//
	// In this case, MainActivity is the parent and scoped as Activity.
	// Therefore it is a finer scope than App and able to inject Activity and App Scoped Singletons.
	// FuelSampleApp will not be able to inject Activity Scoped Singletons
	// As you can imagine, it would not know which activity too choose from
	// since an App has a one-to-many relationship with Activities.

	private final Lazy<SampleAppSingleton> mAppSingleton = Lazy.attain( this, SampleAppSingleton.class );
	private final Lazy<SampleActivitySingleton> mActivitySingleton = Lazy.attain( this, SampleActivitySingleton.class );
	private final Lazy<SampleExternalPojo> mPojo = Lazy.attain( this, SampleExternalPojo.class );
	private final Lazy<SillyBoxStateManager> mBoxState = Lazy.attain( this, SillyBoxStateManager.class );
	private final Lazy<Box> mBox = Lazy.attain( this, Box.class );
	private final Lazy<BoxColored> mBoxColored = Lazy.attain( this, BoxColored.class );
	private final Lazy<Box> mAnotherBox = Lazy.attain( this, Box.class );
	private final Lazy<CyclicalAppSingleton1> mAppCyc = Lazy.attain( this, CyclicalAppSingleton1.class );
	private final Lazy<CyclicalActivitySingleton1> mActCyc = Lazy.attain( this, CyclicalActivitySingleton1.class );
	private final Lazy<CyclicalObject1> mObjCyc = Lazy.attain( this, CyclicalObject1.class );
	private final Lazy<SamplePojoWithActivityScope> mPojoWithActivity = Lazy.attain( this, SamplePojoWithActivityScope.class );
	private final Lazy<MyClass> mMyClass1 = Lazy.attain( this, MyClass.class );
	private final Lazy<MyClass> mMyClass2 = Lazy.attain( this, MyClass.class );

	// TODO: README: this should fail. For more details see Scope.canAccess( Scope )
	// private final Lazy<SampleFragSingleton> mFragSingleton = Lazy.attain( this, SampleFragSingleton.class );

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		FuelInjector.ignite( this, this );

		View helloWorldText = findViewById( R.id.helloworld_text );
		helloWorldText.setOnClickListener( new OnClickListener() {
			@Override public void onClick( View view ) {
				MainActivity.this.startActivity( SecondActivity.newIntent( MainActivity.this ) );
			}
		} );

		View dumpText = findViewById( R.id.dump_text );
		dumpText.setOnClickListener( new OnClickListener() {
			@Override public void onClick( View view ) {
				FuelInjector.debugInjectionGraph();
			}
		} );

		Log.d( mAppSingleton.get().getHelloWorld() );
		Log.d( mActivitySingleton.get().getHelloWorld() );
		Log.d( mPojo.get().doStuff() );

		// Box1 and Box2 will be the same instance because providers only evaluate on the first get, after that its cached.
		// Box1 and Box3 will be different instances but of equal value because the provider is called for each unique lazy.
		// For all three the instance should be a LittleBlackBox since thats how we have our Provider setup
		Log.d( "Box1: %s - expects a LittleBlackBox", mBox.get() );
		Log.d( "Box2: %s - expects a LittleBlackBox same instance as Box1", mBox.get() );
		Log.d( "Box3: %s - expects a LittleBlackBox equal to Box1 but different instance", mBoxColored.get() );

		// Now we'll mix things up and alter the app state in a way that we have our
		// Box provider wired to pick up and deal with.
		// After changing this state, when we try to get an already .get()'d lazy
		// such as Box1,2 or 3, we'll get the same value we got before because they're cached.
		// However now if we try to .get() a new Lazy<Box> we'll get one that observes the latest state
		// Not exactly good code, but it suits our demonstration needs.
		mBoxState.get().setColor( Color.BLUE );
		Log.d( "Box4: %s - expects a LittleBlueBox", mAnotherBox.get() );
		mBoxState.get().setColor( Color.BLACK ); // put the manager back

		// We can even adhoc obtain a Lazy<Box>
		// Just MAKE SURE that the parent "this" is
		// not accidentally a diferent object than you expected
		// such as calling "this" from within an onClickListener, you really mean MainActivity.this.
		// unless of course you bothered to ignite your onClickListener :/
		Lazy<Box> adhocBox = Lazy.attain( this, Box.class );

		// We expect Box5 to be blue like Box4
		Log.d( "Box5: %s - expects a LittleBlueBox", adhocBox.get() );


		// Our Blue/Black state example is pretty sad and there is actually an easier way of doing this
		// Our provider makes consideration for flavor (which is optional but we chose to do it for the example)
		Lazy<Box> blackFlavoredBox = Lazy.attain( this, Box.class, BoxProvider.BOX_FLAVOR_BLACK );
		Lazy<Box> blueFlavoredBox = Lazy.attain( this, Box.class, BoxProvider.BOX_FLAVOR_BLUE );

		// We expect Box6 and Box7 to be black and blue respectively.
		Log.d( "Box6: %s - expects a LittleBlackBox", blackFlavoredBox.get() );
		Log.d( "Box7: %s - expects a LittleBlueBox", blueFlavoredBox.get() );

		// Demonstrate that cyclical dependencies are not an issue.
		// Each of these injectables cause a cyclical injection
		// A -> B -> A
		// this is not an issue due to lazy instantiation.
		mAppCyc.get().doStuff();
		mActCyc.get().doStuff();
		mObjCyc.get().doStuff(); // each cycle results in a new instance because these are not singletons

		mPojoWithActivity.get().doStuff();

		Log.d( "MyClass1: %s", mMyClass1.get() );
		Log.d( "MyClass2: %s", mMyClass2.get() );

		int iterations = 100;
		timeTest1( iterations ); // Singletons
		timeTest2( iterations ); // Pojos
		timeTest3( iterations ); // Mixed test 1/2 Pojos and 1/2 Singletons
	}

	private void timeTest1( int iterations ) {
		long timeStart = SystemClock.elapsedRealtime();
		for ( int i = 0; i < iterations; i++ ) {
			Lazy<SimpleAppSingleton> injected = Lazy.attain( this, SimpleAppSingleton.class );
			injected.get();
		}
		long timeStop = SystemClock.elapsedRealtime();
		Log.d( "Fuel Timed Test1:  %sms, %sx Singletons", timeStop - timeStart, iterations );
	}

	private void timeTest2( int iterations ) {
		long timeStart = SystemClock.elapsedRealtime();
		for ( int i = 0; i < iterations; i++ ) {
			Lazy<SampleExternalPojo> injected = Lazy.attain( this, SampleExternalPojo.class );
			injected.get();
		}
		long timeStop = SystemClock.elapsedRealtime();
		Log.d( "Fuel Timed Test2:  %sms, %sx Pojos", timeStop - timeStart, iterations );
	}

	// A mixed test.  1/2 of the iterations are Pojos and 1/2 are Singletons
	private void timeTest3( int iterations ) {
		long timeStart = SystemClock.elapsedRealtime();
		for ( int i = 0; i < iterations / 2; i++ ) {
			Lazy<SimpleAppSingleton> injected = Lazy.attain( this, SimpleAppSingleton.class );
			injected.get();
		}
		for ( int i = 0; i < iterations / 2; i++ ) {
			Lazy<SampleExternalPojo> injected = Lazy.attain( this, SampleExternalPojo.class );
			injected.get();
		}
		long timeStop = SystemClock.elapsedRealtime();
		Log.d( "Fuel Timed Test3:  %sms, %sx Singletons and Pojos", timeStop - timeStart, iterations );
	}

	@Override protected void onResume() {
		super.onResume();

		new AsyncTask<Void, Void, Void>() {
			@Override protected Void doInBackground( Void... voids ) {
				FuelInjector.ignite( MainActivity.this, this );
				Log.d( "doInBackground" );
				Lazy<SampleAsyncSingleton> aSingleton = Lazy.attain( this, SampleAsyncSingleton.class );
				aSingleton.get().doStuff();
				return null;
			}

			@Override protected void onPostExecute( Void aVoid ) {
				Log.d( "onPostExecute" );
				Lazy<SampleAsyncSingleton> aSingleton = Lazy.attain( this, SampleAsyncSingleton.class );
				aSingleton.get().doStuff();
			}
		}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
	}
}
