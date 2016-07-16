package com.ath.fuelsample;

import android.app.Activity;
import android.app.Application;

import com.ath.fuel.ActivitySingleton;
import com.ath.fuel.AppSingleton;
import com.ath.fuel.Lazy;

@ActivitySingleton
public class SampleActivitySingleton {
    private final Lazy<Application> mApp = Lazy.attain( this, Application.class );
    private final Lazy<Activity> mActivity = Lazy.attain( this, Activity.class );

    public String getHelloWorld() {
        return String.format( "Hello World I live in (%s, %s) and I am %s", mApp, mActivity, this );
    }
}
