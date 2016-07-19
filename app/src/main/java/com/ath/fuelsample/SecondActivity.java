package com.ath.fuelsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ath.fuel.FuelInjector;
import com.ath.fuel.Lazy;
import com.ath.fuelsample.things.SampleActivitySingleton;

public class SecondActivity extends AppCompatActivity {

	public static final Intent newIntent( Context context ) {
		Intent intent = new Intent( context, SecondActivity.class );
		return intent;
	}

	private final Lazy<SampleActivitySingleton> mActivitySingleton = Lazy.attain( this, SampleActivitySingleton.class );

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_second );
		FuelInjector.ignite( this, this );

		Log.d("SecondActivity.onCreate");
		Log.d( mActivitySingleton.get().getHelloWorld() );
	}
}
