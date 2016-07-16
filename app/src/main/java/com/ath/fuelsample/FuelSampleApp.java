package com.ath.fuelsample;

import android.app.Application;

import com.ath.fuel.FuelInjector;

public class FuelSampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FuelInjector.setDebug( true );
        FuelInjector.initializeModule( new FuelSampleModule( this ) );
    }
}
