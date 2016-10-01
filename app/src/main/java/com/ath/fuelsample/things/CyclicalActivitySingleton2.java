package com.ath.fuelsample.things;

import com.ath.fuel.ActivitySingleton;
import com.ath.fuel.Lazy;
import com.ath.fuelsample.Log;

@ActivitySingleton
public class CyclicalActivitySingleton2 {
    private final Lazy<CyclicalActivitySingleton1> mCyclicalSingleton1Lazy = Lazy.attain( this, CyclicalActivitySingleton1.class );
    private final Lazy<MemoryEater> mMemoryEater = Lazy.attain( this, MemoryEater.class );

    public void doStuff() {
        Log.d( "doStuff 2" );
        mCyclicalSingleton1Lazy.get().doCycleTermination();
        mMemoryEater.get();
    }
}
