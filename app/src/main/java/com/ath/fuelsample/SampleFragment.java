package com.ath.fuelsample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ath.fuel.FuelInjector;
import com.ath.fuel.Lazy;
import com.ath.fuel.OnFueled;
import com.ath.fuelsample.things.SampleActivitySingleton;
import com.ath.fuelsample.things.SampleFragSingleton;

public class SampleFragment extends Fragment implements OnFueled {
	private final Lazy<SampleActivitySingleton> mSampleActivitySingleton = Lazy.attain( this, SampleActivitySingleton.class );
	private final Lazy<SampleFragSingleton> mSampleFragSingleton1 = Lazy.attain( this, SampleFragSingleton.class );
	private final Lazy<SampleFragSingleton> mSampleFragSingleton2 = Lazy.attain( this, SampleFragSingleton.class );

	private TextView mLayout;

	// Now that we are attached, we can call FuelInjector.ignite and dequeue our injectables and use them
	@Override
	public void onAttach( Context context ) {
		super.onAttach( context );
		FuelInjector.ignite( context, this ); // de-queue our injectables
	}

	@Override
	@Nullable public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
		mLayout = new TextView( getContext() );
		mLayout.setText( "Hello Fragment" );
		Log.d( "onCreateView - %s", mSampleActivitySingleton.get().getHelloWorld() );
		Log.d( "onCreateView 1 - %s => %s", this, mSampleFragSingleton1.get().doStuff() );
		Log.d( "onCreateView 2 - %s => %s", this, mSampleFragSingleton2.get().doStuff() );
		return mLayout;
	}

	@Override public void onFueled() {
		Log.d( "onFueled" );
	}
}
