package com.ath.fuelsample;

import android.app.Application;
import android.os.SystemClock;

import com.ath.fuel.FuelInjector;

public class FuelSampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        long timeStart = SystemClock.elapsedRealtime();
        FuelInjector.setDebug( false );
        FuelInjector.initializeModule( new FuelSampleModule( this ) );
        long timeStop = SystemClock.elapsedRealtime();
        Log.d( "Fuel Timed Test Init: %sms", timeStop - timeStart );
    }
}
