package com.ath.fuelsample.things;

import android.app.Application;

import com.ath.fuel.AppSingleton;
import com.ath.fuel.Lazy;

@AppSingleton
public class SampleAppSingleton {
    private final Lazy<Application> mApp = Lazy.attain( this, Application.class );
    private final Lazy<MemoryEater> mMemoryEater = Lazy.attain( this, MemoryEater.class );

    public String getHelloWorld() {
        mMemoryEater.get();
        return String.format( "Hello World I live in %s and I am %s", mApp.get(), this );
    }
}
