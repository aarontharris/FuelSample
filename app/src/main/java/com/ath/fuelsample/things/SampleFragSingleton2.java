package com.ath.fuelsample.things;

import com.ath.fuel.FragmentSingleton;
import com.ath.fuel.Lazy;
import com.ath.fuelsample.Log;

@FragmentSingleton
public class SampleFragSingleton2 {
    private final Lazy<MemoryEater> mMemoryEater = Lazy.attain( this, MemoryEater.class );

    public void helloWorld() {
        mMemoryEater.get();
        Log.d( "Hello World: %s", this );
    }

}
