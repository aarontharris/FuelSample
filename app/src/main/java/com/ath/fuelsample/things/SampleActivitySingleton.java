package com.ath.fuelsample.things;

import android.app.Activity;
import android.app.Application;

import com.ath.fuel.ActivitySingleton;
import com.ath.fuel.Lazy;
import com.ath.fuel.OnFueled;
import com.ath.fuelsample.Log;

@ActivitySingleton
public class SampleActivitySingleton implements OnFueled {
    private final Lazy<Application> mApp = Lazy.attain( this, Application.class );
    private final Lazy<Activity> mActivity = Lazy.attain( this, Activity.class );
    private final Lazy<SampleAppSingleton> mSampleAppSingleton = Lazy.attain( this, SampleAppSingleton.class ).setDebug();
    private final Lazy<MemoryEater> mMemoryEater = Lazy.attain( this, MemoryEater.class );

    public String getHelloWorld() {
        mMemoryEater.get();
        return String.format( "Hello World ! I live in (%s, %s) and I am %s", mApp.get(), mActivity.get(), this );
    }

    @Override public void onFueled() {
        Log.d( "onFueled %s w/ %s", this, mActivity.get() );
        Log.d( mSampleAppSingleton.get().getHelloWorld() );
    }
}
