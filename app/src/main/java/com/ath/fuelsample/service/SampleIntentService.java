package com.ath.fuelsample.service;

import android.app.IntentService;
import android.content.Intent;

import com.ath.fuel.FuelInjector;
import com.ath.fuel.Lazy;
import com.ath.fuelsample.FuelSampleApp;
import com.ath.fuelsample.Log;
import com.ath.fuelsample.things.SampleAppSingleton;

public class SampleIntentService extends IntentService {

    private final Lazy<FuelSampleApp> mApp = Lazy.attain( this, FuelSampleApp.class );
    private final Lazy<SampleAppSingleton> mSampleAppSingleton = Lazy.attain( this, SampleAppSingleton.class );

    public SampleIntentService() {
        super( "SampleIntentService" );
        FuelInjector.ignite( this, this );
    }

    @Override protected void onHandleIntent( Intent intent ) {
        Log.d( "onHandleIntent: app=%s", mApp );
        Log.d( "onHandleIntent: app.get()=%s", mApp.get() );
        Log.d( "onHandleIntent: test=%s", mSampleAppSingleton );
        Log.d( "onHandleIntent: test.get()=%s", mSampleAppSingleton.get() );
    }

}
