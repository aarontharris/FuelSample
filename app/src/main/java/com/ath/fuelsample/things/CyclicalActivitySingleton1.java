package com.ath.fuelsample.things;

import com.ath.fuel.ActivitySingleton;
import com.ath.fuel.Lazy;
import com.ath.fuelsample.Log;

@ActivitySingleton
public class CyclicalActivitySingleton1 {
    private final Lazy<CyclicalActivitySingleton2> mCyclicalSingleton2Lazy = Lazy.attain( this, CyclicalActivitySingleton2.class );
    private final Lazy<MemoryEater> mMemoryEater = Lazy.attain( this, MemoryEater.class );

    public void doStuff() {
        Log.d( "doStuff 1" );
        mCyclicalSingleton2Lazy.get().doStuff();
        mMemoryEater.get();
    }

    public void doCycleTermination() {
        Log.d( "the end" );
    }
}
